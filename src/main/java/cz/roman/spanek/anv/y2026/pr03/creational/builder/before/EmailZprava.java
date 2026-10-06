package cz.roman.spanek.anv.y2026.pr03.creational.builder.before;

import java.util.List;

/**
 * Neměnná e-mailová zpráva.
 * Objekt lze vytvořit pouze přes konstruktory – a těch je potřeba hodně.
 * (Gettery jsou kvůli stručnosti vynechány, zpráva se vypisuje přes toString().)
 */
public class EmailZprava {

    private static final int MAX_VELIKOST_PRILOH_KB = 25 * 1024;

    private final String odesilatel;
    private final List<String> prijemci;
    private final List<String> kopie;
    private final List<String> skrytaKopie;
    private final String predmet;
    private final String text;
    private final boolean html;
    private final boolean potvrzeniOPrectani;
    private final Priorita priorita;
    private final List<Priloha> prilohy;

    // ---- Teleskopické konstruktory: každý přidává další parametr ----

    public EmailZprava(String odesilatel, List<String> prijemci,
                       String predmet, String text) {
        this(odesilatel, prijemci, null, predmet, text);
    }

    public EmailZprava(String odesilatel, List<String> prijemci, List<String> kopie,
                       String predmet, String text) {
        this(odesilatel, prijemci, kopie, null, predmet, text);
    }

    public EmailZprava(String odesilatel, List<String> prijemci, List<String> kopie,
                       List<String> skrytaKopie, String predmet, String text) {
        this(odesilatel, prijemci, kopie, skrytaKopie, predmet, text, false, false);
    }

    public EmailZprava(String odesilatel, List<String> prijemci, List<String> kopie,
                       List<String> skrytaKopie, String predmet, String text,
                       boolean html, boolean potvrzeniOPrectani) {
        this(odesilatel, prijemci, kopie, skrytaKopie, predmet, text,
                html, potvrzeniOPrectani, Priorita.NORMALNI, null);
    }

    public EmailZprava(String odesilatel, List<String> prijemci, List<String> kopie,
                       List<String> skrytaKopie, String predmet, String text,
                       boolean html, boolean potvrzeniOPrectani,
                       Priorita priorita, List<Priloha> prilohy) {

        // ---- Validace ----
        if (odesilatel == null || !odesilatel.contains("@")) {
            throw new IllegalArgumentException("Odesílatel musí mít platnou e-mailovou adresu.");
        }
        if (prijemci == null || prijemci.isEmpty()) {
            throw new IllegalArgumentException("Zpráva musí mít alespoň jednoho příjemce.");
        }
        overAdresy("příjemci", prijemci);
        overAdresy("kopie", kopie);
        overAdresy("skrytá kopie", skrytaKopie);
        if (predmet == null || predmet.isBlank()) {
            throw new IllegalArgumentException("Předmět nesmí být prázdný.");
        }
        int velikostPriloh = (prilohy == null) ? 0
                : prilohy.stream().mapToInt(Priloha::velikostKb).sum();
        if (velikostPriloh > MAX_VELIKOST_PRILOH_KB) {
            throw new IllegalArgumentException("Přílohy přesahují limit 25 MB.");
        }

        // ---- Přiřazení (null se převádí na prázdný seznam / výchozí hodnotu) ----
        this.odesilatel = odesilatel;
        this.prijemci = List.copyOf(prijemci);
        this.kopie = (kopie == null) ? List.of() : List.copyOf(kopie);
        this.skrytaKopie = (skrytaKopie == null) ? List.of() : List.copyOf(skrytaKopie);
        this.predmet = predmet;
        this.text = (text == null) ? "" : text;
        this.html = html;
        this.potvrzeniOPrectani = potvrzeniOPrectani;
        this.priorita = (priorita == null) ? Priorita.NORMALNI : priorita;
        this.prilohy = (prilohy == null) ? List.of() : List.copyOf(prilohy);
    }

    private static void overAdresy(String pojmenovani, List<String> adresy) {
        if (adresy == null) {
            return;
        }
        for (String adresa : adresy) {
            if (adresa == null || !adresa.contains("@")) {
                throw new IllegalArgumentException(
                        "Neplatná adresa v poli '" + pojmenovani + "': " + adresa);
            }
        }
    }

    @Override
    public String toString() {
        return "Od: " + odesilatel
                + "\nKomu: " + prijemci
                + "\nKopie: " + kopie
                + "\nSkrytá kopie: " + skrytaKopie
                + "\nPředmět: " + predmet
                + "\nFormát: " + (html ? "HTML" : "prostý text")
                + ", potvrzení o přečtení: " + (potvrzeniOPrectani ? "ano" : "ne")
                + ", priorita: " + priorita
                + "\nPřílohy: " + prilohy
                + "\nText: " + text;
    }
}
