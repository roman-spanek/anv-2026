package cz.roman.spanek.anv.y2026.pr05.behavioral.strategy.before;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Objednavka> objednavky = List.of(
                new Objednavka("Eva Nováková", 800, 1.2, TypDopravy.OSOBNI_ODBER),
                new Objednavka("Petr Svoboda", 2400, 7.5, TypDopravy.OSOBNI_ODBER),
                new Objednavka("Jana Dvořáková", 15000, 23.0, TypDopravy.OSOBNI_ODBER));

        // Pro každou objednávku vypíšeme cenu při všech způsobech dopravy
        for (Objednavka objednavka : objednavky) {
            System.out.println("\nObjednávka: " + objednavka.getZakaznik()
                    + " (zboží " + objednavka.getHodnotaZbozi() + " Kč, "
                    + objednavka.getHmotnostKg() + " kg)");

            for (TypDopravy typ : TypDopravy.values()) {
                objednavka.setDoprava(typ);
                vypis(objednavka);
            }
        }

        // PROBLÉM A: Chceme přidat nový způsob dopravy (např. "Balík na poštu").
        //            Musíme upravit enum TypDopravy a OBA switche v Objednavka.
        // PROBLÉM B: Chceme zavést akci "Černý pátek - doprava zdarma".
        //            Kam ji napsat? Další větev v switchi, nebo příznak v každé větvi?
        // PROBLÉM C: Partner nám chce dodat vlastní tarif. Bez zásahu do
        //            zdrojového kódu Objednavka to nejde.
    }

    private static void vypis(Objednavka objednavka) {
        try {
            int cenaDopravy = objednavka.cenaDopravy();
            System.out.printf("  %-26s %5d Kč  (celkem %d Kč)%n",
                    objednavka.nazevDopravy(), cenaDopravy, objednavka.celkovaCena());
        } catch (IllegalArgumentException e) {
            System.out.printf("  %-26s nelze: %s%n", objednavka.nazevDopravy(), e.getMessage());
        }
    }
}
