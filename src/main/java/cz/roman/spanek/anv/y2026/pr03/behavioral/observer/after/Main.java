package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.after;

public class Main {

    public static void main(String[] args) {

        Produkt notebook = new Produkt("Notebook Lenovo", 24990, 5);

        Pozorovatel auditLog = new AuditLog();
        Pozorovatel web = new WebovaStranka();
        Pozorovatel skladnik = new SkladovyAlert(2);
        Pozorovatel eva = new SledujiciZakaznik("Eva", "eva@example.com", 22000);
        Pozorovatel petr = new SledujiciZakaznik("Petr", "petr@example.com", 20000);

        notebook.pridejPozorovatele(auditLog);
        notebook.pridejPozorovatele(web);
        notebook.pridejPozorovatele(skladnik);
        notebook.pridejPozorovatele(eva);
        notebook.pridejPozorovatele(petr);

        System.out.println("\n>>> Změna ceny na 23990 Kč");
        notebook.setCena(23990);

        System.out.println("\n>>> Změna ceny na 21500 Kč");
        notebook.setCena(21500);

        System.out.println("\n>>> Prodej – zbývají 2 ks");
        notebook.setPocetKusu(2);

        System.out.println("\n>>> Změna ceny na 19990 Kč");
        notebook.setCena(19990);

        // ===== Úpravy za běhu – bez zásahu do třídy Produkt =====

        System.out.println("\n===== Za běhu: přidána push notifikace, odhlášena Eva a audit =====");

        // Nová reakce = nový pozorovatel (zde jako lambda výraz, Pozorovatel má jednu metodu)
        notebook.pridejPozorovatele(zmena -> System.out.println("[PUSH] "
                + zmena.produkt().getNazev() + ": " + zmena.vlastnost().getPopis()
                + " je nyní " + zmena.novaHodnota()));

        notebook.odeberPozorovatele(eva);
        notebook.odeberPozorovatele(auditLog);

        System.out.println("\n>>> Změna ceny na 18990 Kč");
        notebook.setCena(18990);

        System.out.println("\n>>> Prodej – zbývá 1 ks");
        notebook.setPocetKusu(1);
    }
}
