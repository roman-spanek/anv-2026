# Návrhový vzor Observer – ukázka v Javě

Doména: **produkt v e-shopu** (třída `Produkt`, jejíž změny ceny a skladové zásoby sleduje několik různých částí systému).
Java 17+. Obě verze vypisují **stejný výstup** pro první čtyři kroky v `Main`;
verze s Observerem navíc ukazuje změny za běhu, které bez vzoru nejsou možné.

---

## Část 1 – Zadání bez použití vzoru (`01-bez-vzoru/`)

### Zadání pro studenty

E-shop potřebuje třídu `Produkt` s názvem, cenou a počtem kusů skladem.
Při každé změně ceny nebo počtu kusů musí systém reagovat:

- **WebovaStranka** – obnoví zobrazení produktu (cena i sklad),
- **AuditLog** – zapíše změnu (původní a nová hodnota),
- **SkladovyAlert** – upozorní skladníka, pokud počet kusů klesne na minimum (2) nebo níže,
- **SledujiciZakaznik** – pošle e-mail zákazníkovi, pokud cena **klesla** a je rovna nebo nižší než jeho
  požadovaná cena.

Připravená implementace volá všechny reakce přímo z metod `setCena()` a `setPocetKusu()`.

**Úkoly:**
1. Prostudujte třídy, spusťte program a vysvětlete, co se při jednotlivých krocích děje.
2. Najděte, co je na návrhu problematické (zaměřte se na třídu `Produkt` a na to, koho všeho zná).
3. Představte si, že přibude push notifikace do mobilní aplikace. Co všechno musíte upravit?
4. Lze za běhu vypnout audit nebo odhlásit zákazníka ze sledování? Proč ne?
5. Napadá vás jiné řešení než přímé volání? (nápověda: cyklické dotazování, tzv. *polling*)

### Poznámky pro přednášejícího – co je v kódu špatně

| # | Problém | Kde je vidět |
|---|---------|--------------|
| 1 | **Těsná vazba** – `Produkt` si vytváří a zná konkrétní třídy; ty zároveň znají `Produkt` (vzájemná závislost) | pole v `Produkt`, `WebovaStranka.obnov(Produkt)` |
| 2 | **Porušení Open/Closed** – každá nová reakce (push, SMS, cache…) vyžaduje zásah do `Produkt` | `Main`, komentář A |
| 3 | **Žádná flexibilita za běhu** – reakce jsou `final` pole, odhlášení neexistuje | `Main`, komentář B |
| 4 | **Porušení SRP** – pravidlo „kdy chce zákazník být upozorněn“ je uvnitř `Produktu` | `setCena()`, cyklus přes zákazníky |
| 5 | **Duplicitní kód** – volání reakcí je zkopírované v každém setteru; nová vlastnost = další kopie (a snadné zapomenutí) | `setCena()` × `setPocetKusu()` |
| 6 | **Pevně daná konfigurace** – skladové minimum 2 platí pro všechny produkty | `new SkladovyAlert(2)` |
| 7 | **Špatná testovatelnost** – `Produkt` nelze vyzkoušet bez webu, auditu a skladu | celá třída |

Alternativa „pozorovatelé se v cyklu sami ptají produktu“ (polling) vazbu odstraní, ale je neefektivní
a reakce přichází se zpožděním – a stejně nevíme, *co* se změnilo.

---

## Část 2 – Opravené zadání s použitím vzoru Observer (`02-s-observerem/`)

### Zadání pro studenty

Upravte řešení z části 1 pomocí návrhového vzoru **Observer**. Chování zůstává stejné.

**Požadavky na řešení:**
- vytvořte rozhraní **`Pozorovatel`** s metodou `aktualizuj(ZmenaProduktu zmena)`,
- `Produkt` je **subjekt**: udržuje seznam pozorovatelů a nabízí `pridejPozorovatele()` a `odeberPozorovatele()`,
- `Produkt` **nezná žádnou konkrétní třídu** pozorovatele, pouze rozhraní `Pozorovatel`,
- informace o změně předávejte jako objekt `ZmenaProduktu` (co se změnilo, stará a nová hodnota),
- `WebovaStranka`, `AuditLog`, `SkladovyAlert` a `SledujiciZakaznik` implementují `Pozorovatel`
  a **každý sám rozhoduje, co ho zajímá** (např. skladník ignoruje změnu ceny),
- notifikace je na jednom místě (`upozorni()`), setter jen změní stav a vyvolá ji,
- v `Main` za běhu **přidejte** push notifikaci (lambda výraz) a **odhlaste** jednoho zákazníka a audit.

### Co vzor řeší

| Problém z části 1 | Jak ho řeší Observer |
|-------------------|----------------------|
| Těsná vazba | `Produkt` zná jen rozhraní `Pozorovatel` |
| Nová reakce = zásah do `Produktu` | Stačí nový pozorovatel, `Produkt` se nemění (viz `[PUSH]` v `Main`) |
| Nelze měnit za běhu | `pridejPozorovatele()` / `odeberPozorovatele()` |
| Logika zákazníka v `Produktu` | Přesunuta do `SledujiciZakaznik` |
| Duplicitní kód | Jedna metoda `upozorni()` |
| Konfigurace natvrdo | `new SkladovyAlert(2)` si nastaví kdokoli podle potřeby |
| Špatná testovatelnost | `Produkt` lze testovat s libovolným testovacím pozorovatelem |

### Otázky k diskusi
1. Proč `upozorni()` prochází **kopii** seznamu? Co by se stalo při odhlášení pozorovatele během notifikace?
2. Co když jeden pozorovatel vyhodí výjimku? Dostanou zprávu ti, kdo jsou v seznamu za ním?
3. Na pořadí notifikací by pozorovatelé neměli záviset – proč? Jak by to šlo zaručit?
4. **Push** model (předáváme `ZmenaProduktu`) × **pull** model (pozorovatel dostane jen `Produkt` a přečte si, co potřebuje). Výhody a nevýhody?
5. Co se stane, když se pozorovatel zapomene odhlásit? (tzv. *lapsed listener* – paměťové úniky)
6. Pozorovatelé se volají synchronně ve vlákně, které změnu vyvolalo. Co to znamená pro výkon? Jak by šlo notifikace zrychlit?
7. Kde se vzor používá v praxi? (GUI listenery, `PropertyChangeListener`, událostní sběrnice, reaktivní streamy)
   Třída `java.util.Observable` je od Javy 9 označená jako zastaralá – proč je lepší vlastní rozhraní?

---

## Spuštění

```bash
cd 01-bez-vzoru      # nebo 02-s-observerem
javac *.java
java Main
```

Pokud se v konzoli (zejména na Windows) zobrazují místo diakritiky otazníky:
`java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 Main` (případně `chcp 65001`).
