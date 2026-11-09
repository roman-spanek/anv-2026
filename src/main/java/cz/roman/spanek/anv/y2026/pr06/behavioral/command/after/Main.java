package cz.roman.spanek.anv.y2026.pr06.behavioral.command.after;

import cz.roman.spanek.anv.y2026.pr06.behavioral.command.before.Dokument;

public class Main {

    public static void main(String[] args) {

        Dokument dokument = new Dokument();                // příjemce (Receiver)
        SpravcePrikazu spravce = new SpravcePrikazu();     // volající (Invoker)

        System.out.println("\n>>> Přidat text \"Ahoj světe\"");
        spravce.proved(new PridejTextPrikaz(dokument, "Ahoj světe"));
        vypis(dokument);

        System.out.println("\n>>> Přidat text \"!!!\"");
        spravce.proved(new PridejTextPrikaz(dokument, "!!!"));
        vypis(dokument);

        System.out.println("\n>>> Smazat posledních 2 znaků");
        spravce.proved(new SmazZnakyPrikaz(dokument, 2));
        vypis(dokument);

        System.out.println("\n>>> Velká písmena");
        spravce.proved(new NaVelkaPismenaPrikaz(dokument));
        vypis(dokument);

        System.out.println("\n>>> Zpět");
        spravce.vratZpet();
        vypis(dokument);

        System.out.println("\n>>> Zpět");
        spravce.vratZpet();
        vypis(dokument);

        System.out.println("\n>>> Zpět");
        spravce.vratZpet();
        vypis(dokument);

        // ===== Co bez vzoru nešlo =====

        System.out.println("\n===== Znovu (redo) =====");

        System.out.println("\n>>> Znovu");
        spravce.znovu();
        vypis(dokument);

        System.out.println("\n>>> Znovu");
        spravce.znovu();
        vypis(dokument);

        System.out.println("\n===== Makro: více příkazů jako jeden krok =====");

        System.out.println("\n>>> Provést makro \"Zvýraznění\"");
        spravce.proved(new MakroPrikaz("Zvýraznění",
                new NaVelkaPismenaPrikaz(dokument),
                new PridejTextPrikaz(dokument, " <<<")));
        vypis(dokument);

        boolean provedeno = spravce.znovu();
        System.out.println("    znovu provedeno: " + provedeno + " (nový příkaz zrušil možnost \"znovu\")");

        spravce.vypisHistorii();

        System.out.println("\n>>> Zpět (jediným krokem se vrátí celé makro)");
        spravce.vratZpet();
        vypis(dokument);
    }

    private static void vypis(Dokument dokument) {
        System.out.println("    text: \"" + dokument.getText() + "\"");
    }
}
