# Návrhový vzor Builder – ukázka v Javě

Doména: **e-mailová zpráva** (třída `EmailZprava`). Java 17+.
Obě verze dělají totéž a vypisují stejný výstup – liší se jen tím, jak se objekt vytváří.

---

## Část 1 – Zadání bez použití vzoru (`01-bez-vzoru/`)

### Zadání pro studenty

Pro školní informační systém je potřeba třída `EmailZprava`, která reprezentuje odesílanou zprávu.

**Povinné údaje:** odesílatel, alespoň jeden příjemce, předmět.
**Volitelné údaje:** kopie, skrytá kopie, text zprávy, formát HTML, potvrzení o přečtení,
priorita (`NIZKA`, `NORMALNI`, `VYSOKA`; výchozí `NORMALNI`), přílohy.

**Pravidla:**
- všechny e-mailové adresy musí obsahovat `@`,
- předmět nesmí být prázdný,
- celková velikost příloh nesmí překročit 25 MB,
- zpráva je po vytvoření neměnná (immutable).

Připravená implementace používá **teleskopické konstruktory** (5 přetížených konstruktorů).

**Úkoly:**
1. Prostudujte třídy `EmailZprava` a `Main`, spusťte program.
2. V `Main` jsou čtyři zprávy. Popište, co je na vytváření objektů nepříjemné nebo nebezpečné.
3. Navrhněte, co by se muselo změnit, kdyby přibyl další volitelný údaj (např. `odpovedetNa`).
4. Napadá vás jiné řešení než konstruktory? Jaké má nevýhody? (nápověda: settery)

### Poznámky pro přednášejícího – co je v kódu špatně

| # | Problém | Kde je vidět |
|---|---------|--------------|
| 1 | **Nečitelná volání** – `null`, `true`, `false` nemají jméno, bez nahlédnutí do konstruktoru nevíte, co znamenají | `Main`, případ 2 |
| 2 | **Nutnost vyplnit vše před požadovaným parametrem** (`null, null, false, false, Priorita.VYSOKA, null`) | `Main`, případ 3 |
| 3 | **Záměna parametrů stejného typu** (`predmet` × `text`) – překladač ji nezachytí, program běží dál se špatnými daty | `Main`, případ 4 |
| 4 | **Explozivní růst konstruktorů** – nový volitelný údaj = další přetížení nebo úprava všech volání | `EmailZprava` |
| 5 | **Zbytečné `null`** v API – volající musí vědět, že `null` znamená „nic“ | `Main`, případy 2 a 3 |

Častá „oprava“ pomocí setterů (`new EmailZprava()` + `setPredmet(...)` …) problémy 1–3 sice odstraní,
ale přinese jiné: objekt je **mutable**, existuje v **nekonzistentním (rozpracovaném) stavu**
a nejde zaručit, že někdo nezapomene nastavit povinný údaj. Validace nemá jasné místo.

---

## Část 2 – Opravené zadání s použitím vzoru Builder (`02-s-builderem/`)

### Zadání pro studenty

Upravte třídu `EmailZprava` z části 1 tak, aby se vytvářela pomocí návrhového vzoru **Builder**.
Chování a pravidla zůstávají stejná, změní se jen způsob vytváření objektu.

**Požadavky na řešení:**
- konstruktor třídy `EmailZprava` je **soukromý**,
- uvnitř třídy je **vnořená statická třída `Builder`**,
- každý volitelný údaj má vlastní metodu builderu, která vrací `this` (**fluent API**),
- výchozí hodnoty volitelných údajů jsou definovány v builderu,
- **validace probíhá v metodě `build()`** – nikdy nevznikne neplatný objekt,
- výsledný objekt zůstává **neměnný**,
- `Main` upravte tak, aby vytvářel stejné zprávy jako dříve.

### Co vzor řeší (porovnání)

```java
// PŘED – co je co?
new EmailZprava("novak@uni.cz", List.of("a@uni.cz", "b@uni.cz"), null,
        List.of("vedouci@uni.cz"), "Změna termínu", "<h1>...</h1>",
        true, false, Priorita.VYSOKA, List.of(new Priloha("harmonogram.pdf", 340)));

// PO – čte se jako věta
EmailZprava.builder()
        .odesilatel("novak@uni.cz")
        .komu("a@uni.cz", "b@uni.cz")
        .skrytaKopie("vedouci@uni.cz")
        .predmet("Změna termínu")
        .text("<h1>...</h1>")
        .html()
        .priorita(Priorita.VYSOKA)
        .priloha("harmonogram.pdf", 340)
        .build();
```

| Problém z části 1 | Jak ho řeší Builder |
|-------------------|---------------------|
| Nečitelná volání | Každá hodnota má jméno (`.html()`, `.priorita(...)`) |
| Vyplňování nepotřebných parametrů | Nepotřebné údaje se prostě vynechají |
| Záměna `predmet` × `text` | Nejde to – jsou to dvě různé metody |
| Růst počtu konstruktorů | Nový údaj = jedna nová metoda v builderu, stávající volání se nemění |
| `null` v API | Žádné `null` – výchozí hodnoty jsou v builderu |
| Nekonzistentní stav / validace | Objekt vzniká až v `build()` po validaci a je neměnný |

### Otázky k diskusi
1. Proč je konstruktor `EmailZprava` soukromý? Co by se stalo, kdyby byl veřejný?
2. Proč je `Builder` **vnořená statická** třída a ne samostatná?
3. Kdy by použití Builderu nebylo vhodné? (třída se 2–3 parametry, např. `Bod(x, y)`)
4. Čím se Builder liší od továrny (Factory)? Kdy použít které?
5. Jak by šlo Builder použít pro zprávy, které se liší jen v adresátovi (šablona zprávy)?

---

## Spuštění

```bash
cd 01-bez-vzoru      # nebo 02-s-builderem
javac *.java
java Main
```

Pokud se v konzoli (zejména na Windows) zobrazují místo diakritiky otazníky:
`java -Dfile.encoding=UTF-8 Main` (případně `chcp 65001`).

Gettery jsou v obou verzích kvůli stručnosti vynechány, zpráva se vypisuje přes `toString()`.
