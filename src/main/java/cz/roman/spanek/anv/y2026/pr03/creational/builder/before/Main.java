package cz.roman.spanek.anv.y2026.pr03.creational.builder.before;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1) Jednoduchá zpráva – zatím je všechno v pořádku
        EmailZprava jednoducha = new EmailZprava(
                "novak@uni.cz",
                List.of("student@uni.cz"),
                "Zápočet",
                "Dobrý den, zápočet bude v pátek.");
        System.out.println(jednoducha);
        System.out.println("--------------------------------------------");

        // 2) PROBLÉM: nečitelné volání.
        //    Co znamená null? Které z booleanů je HTML a které potvrzení o přečtení?
        EmailZprava slozita = new EmailZprava(
                "novak@uni.cz",
                List.of("student1@uni.cz", "student2@uni.cz"),
                null,
                List.of("vedouci@uni.cz"),
                "Změna termínu zkoušky",
                "<h1>Nový termín</h1><p>Zkouška se přesouvá na 20. 1.</p>",
                true,
                false,
                Priorita.VYSOKA,
                List.of(new Priloha("harmonogram.pdf", 340)));
        System.out.println(slozita);
        System.out.println("--------------------------------------------");

        // 3) PROBLÉM: chci jen nastavit prioritu, ale musím vyplnit
        //    všechny parametry, které jí předcházejí (null, null, false, false ...).
        EmailZprava jenPriorita = new EmailZprava(
                "novak@uni.cz",
                List.of("student@uni.cz"),
                null,
                null,
                "Upozornění",
                "Dnešní cvičení odpadá.",
                false,
                false,
                Priorita.VYSOKA,
                null);
        System.out.println(jenPriorita);
        System.out.println("--------------------------------------------");

        // 4) PROBLÉM: záměna předmětu a textu. Oba parametry jsou String,
        //    takže překladač chybu nezachytí a program "bez problémů" poběží.
        EmailZprava zamena = new EmailZprava(
                "novak@uni.cz",
                List.of("student@uni.cz"),
                "Dobrý den, v příloze posílám podklady ke cvičení.",   // měl být text
                "Podklady ke cvičení");                                // měl být předmět
        System.out.println(zamena);
        System.out.println("--------------------------------------------");

        // 5) Nevalidní zpráva (žádný příjemce) skončí výjimkou až za běhu
        try {
            new EmailZprava("novak@uni.cz", List.of(), "Bez příjemce", "Text");
        } catch (IllegalArgumentException e) {
            System.out.println("Chyba: " + e.getMessage());
        }
    }
}
