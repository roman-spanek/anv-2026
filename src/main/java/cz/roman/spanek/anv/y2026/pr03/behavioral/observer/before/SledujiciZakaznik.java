package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.before;

public class SledujiciZakaznik {

    private final String jmeno;
    private final String email;
    private final int zadanaCena;

    public SledujiciZakaznik(String jmeno, String email, int zadanaCena) {
        this.jmeno = jmeno;
        this.email = email;
        this.zadanaCena = zadanaCena;
    }

    public int getZadanaCena() {
        return zadanaCena;
    }

    public void upozorniNaSnizeniCeny(Produkt produkt) {
        System.out.println("[E-MAIL pro " + email + "] Dobrý den, " + jmeno + ", cena produktu "
                + produkt.getNazev() + " klesla na " + produkt.getCena() + " Kč.");
    }
}
