package cz.roman.spanek.anv.y2026.pr03.creational.builder.after;

import java.util.ArrayList;
import java.util.List;

/**
 * Neměnná e-mailová zpráva vytvářená pomocí návrhového vzoru Builder.
 * Konstruktor je soukromý – zprávu lze vytvořit jen přes EmailZprava.builder().
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

    // Jediný konstruktor je soukromý a přebírá hodnoty z (už zvalidovaného) builderu
    private EmailZprava(Builder b) {
        this.odesilatel = b.odesilatel;
        this.prijemci = List.copyOf(b.prijemci);
        this.kopie = List.copyOf(b.kopie);
        this.skrytaKopie = List.copyOf(b.skrytaKopie);
        this.predmet = b.predmet;
        this.text = b.text;
        this.html = b.html;
        this.potvrzeniOPrectani = b.potvrzeniOPrectani;
        this.priorita = b.priorita;
        this.prilohy = List.copyOf(b.prilohy);
    }

    public static Builder builder() {
        return new Builder();
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

    // =====================================================================

    public static class Builder {

        // Výchozí hodnoty volitelných parametrů jsou na jednom místě
        private String odesilatel;
        private final List<String> prijemci = new ArrayList<>();
        private final List<String> kopie = new ArrayList<>();
        private final List<String> skrytaKopie = new ArrayList<>();
        private String predmet;
        private String text = "";
        private boolean html = false;
        private boolean potvrzeniOPrectani = false;
        private Priorita priorita = Priorita.NORMALNI;
        private final List<Priloha> prilohy = new ArrayList<>();

        private Builder() {
        }

        // Každá metoda nastaví jednu hodnotu a vrátí this => řetězení volání (fluent API)

        public Builder odesilatel(String odesilatel) {
            this.odesilatel = odesilatel;
            return this;
        }

        public Builder komu(String... adresy) {
            prijemci.addAll(List.of(adresy));
            return this;
        }

        public Builder kopie(String... adresy) {
            kopie.addAll(List.of(adresy));
            return this;
        }

        public Builder skrytaKopie(String... adresy) {
            skrytaKopie.addAll(List.of(adresy));
            return this;
        }

        public Builder predmet(String predmet) {
            this.predmet = predmet;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder html() {
            this.html = true;
            return this;
        }

        public Builder potvrzeniOPrectani() {
            this.potvrzeniOPrectani = true;
            return this;
        }

        public Builder priorita(Priorita priorita) {
            this.priorita = priorita;
            return this;
        }

        public Builder priloha(String nazev, int velikostKb) {
            prilohy.add(new Priloha(nazev, velikostKb));
            return this;
        }

        // Validace je na jednom místě – hotový objekt je vždy platný
        public EmailZprava build() {
            if (odesilatel == null || !odesilatel.contains("@")) {
                throw new IllegalArgumentException("Odesílatel musí mít platnou e-mailovou adresu.");
            }
            if (prijemci.isEmpty()) {
                throw new IllegalArgumentException("Zpráva musí mít alespoň jednoho příjemce.");
            }
            overAdresy("příjemci", prijemci);
            overAdresy("kopie", kopie);
            overAdresy("skrytá kopie", skrytaKopie);
            if (predmet == null || predmet.isBlank()) {
                throw new IllegalArgumentException("Předmět nesmí být prázdný.");
            }
            int velikostPriloh = prilohy.stream().mapToInt(Priloha::velikostKb).sum();
            if (velikostPriloh > MAX_VELIKOST_PRILOH_KB) {
                throw new IllegalArgumentException("Přílohy přesahují limit 25 MB.");
            }
            return new EmailZprava(this);
        }

        private static void overAdresy(String pojmenovani, List<String> adresy) {
            for (String adresa : adresy) {
                if (adresa == null || !adresa.contains("@")) {
                    throw new IllegalArgumentException(
                            "Neplatná adresa v poli '" + pojmenovani + "': " + adresa);
                }
            }
        }
    }
}
