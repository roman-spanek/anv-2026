package cz.roman.spanek.anv.y2026.pr06.behavioral.command.before;

public class Main {

    public static void main(String[] args) {

        Editor editor = new Editor();

        System.out.println("\n>>> Přidat text \"Ahoj světe\"");
        editor.provedAkci(Akce.PRIDEJ_TEXT, "Ahoj světe");
        vypis(editor);

        System.out.println("\n>>> Přidat text \"!!!\"");
        editor.provedAkci(Akce.PRIDEJ_TEXT, "!!!");
        vypis(editor);

        System.out.println("\n>>> Smazat posledních 2 znaků");
        editor.provedAkci(Akce.SMAZ_ZNAKY, "2");
        vypis(editor);

        System.out.println("\n>>> Velká písmena");
        editor.provedAkci(Akce.VELKA_PISMENA, null);
        vypis(editor);

        System.out.println("\n>>> Zpět");
        editor.vratZpet();
        vypis(editor);

        System.out.println("\n>>> Zpět");
        editor.vratZpet();
        vypis(editor);

        System.out.println("\n>>> Zpět");
        editor.vratZpet();
        vypis(editor);

        // PROBLÉM A: Chceme novou operaci (např. "Nahradit slovo").
        //            Upravit enum cz.roman.spanek.anv.y2026.pr06.behavioral.command.before.Akce + switch v provedAkci() + switch ve vratZpet().
        // PROBLÉM B: Chceme "Znovu" (redo). Potřebujeme druhou historii a třetí switch.
        // PROBLÉM C: Chceme makro (více operací jako jeden krok zpět), výpis historie
        //            s popisem operací nebo odložené / naplánované spuštění operace.
        //            Operace nejsou objekty, nelze je ukládat, předávat ani skládat.
        // PROBLÉM D: cz.roman.spanek.anv.y2026.pr06.behavioral.command.before.Editor zná API Dokumentu pro každou operaci - nelze ho použít
        //            pro jiný objekt než cz.roman.spanek.anv.y2026.pr06.behavioral.command.before.Dokument.
    }

    private static void vypis(Editor editor) {
        System.out.println("    text: \"" + editor.getText() + "\"");
    }
}
