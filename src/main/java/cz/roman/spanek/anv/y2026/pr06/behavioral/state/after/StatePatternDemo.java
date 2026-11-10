package cz.roman.spanek.anv.y2026.pr06.behavioral.state.after;

public class StatePatternDemo {
    public static void main(String[] args) {
        Document doc1 = new Document("user");
        Document doc2 = new Document("admin");

        System.out.println("=== Dokument 1 (role: user) ===");
        doc1.edit();       // upravit dokument
        doc1.publish();    // přesun do moderace
        doc1.edit();       // stále možné upravit
        doc1.publish();    // uživatel nemá práva k publikaci

        System.out.println("\n=== Dokument 2 (role: admin) ===");
        doc2.edit();
        doc2.publish();    // přesun do moderace
        doc2.publish();    // admin publikuje dokument
        doc2.edit();       // nelze upravovat publikovaný dokument
        doc2.publish();    // dokument již publikován
    }
}


/*
Výhody s použitím State vzoru:
----------------------------------------
Každý stav je reprezentován svou vlastní třídou (SRP dodržen).
Přidání nového stavu nevyžaduje upravovat stávající stavové logiky (OCP).
Eliminace složitých if/switch bloků (KISS, DRY).
Snadná údržba a čitelnost kódu.

Co tento příklad ukazuje:
-----------------------------
Každý stav (DraftState, ModerationState, PublishedState) má svou vlastní logiku.
Přechody mezi stavy jsou řízeny objekty stavů.
Kód v Document je čistý, žádné složité if/switch.
Přidání nového stavu nevyžaduje změny v existujících třídách.
 */