package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.before;

public class Main {

    public static void main(String[] args) {

        Produkt notebook = new Produkt("Notebook Lenovo", 24990, 5);

        notebook.pridejSledujiciho(new SledujiciZakaznik("Eva", "eva@example.com", 22000));
        notebook.pridejSledujiciho(new SledujiciZakaznik("Petr", "petr@example.com", 20000));

        System.out.println("\n>>> Změna ceny na 23990 Kč");
        notebook.setCena(23990);

        System.out.println("\n>>> Změna ceny na 21500 Kč");
        notebook.setCena(21500);

        System.out.println("\n>>> Prodej – zbývají 2 ks");
        notebook.setPocetKusu(2);

        System.out.println("\n>>> Změna ceny na 19990 Kč");
        notebook.setCena(19990);

    }
}
