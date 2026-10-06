package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.after;

public class SledujiciZakaznik implements Pozorovatel {

    private final String jmeno;
    private final String email;
    private final int zadanaCena;

    public SledujiciZakaznik(String jmeno, String email, int zadanaCena) {
        this.jmeno = jmeno;
        this.email = email;
        this.zadanaCena = zadanaCena;
    }

    @Override
    public void aktualizuj(ZmenaProduktu zmena) {
        // Pravidlo "kdy chce zákazník být upozorněn" je teď tam, kam patří
        if (zmena.vlastnost() == Vlastnost.CENA
                && zmena.novaHodnota() < zmena.staraHodnota()
                && zmena.novaHodnota() <= zadanaCena) {
            System.out.println("[E-MAIL pro " + email + "] Dobrý den, " + jmeno + ", cena produktu "
                    + zmena.produkt().getNazev() + " klesla na " + zmena.novaHodnota() + " Kč.");
        }
    }
}
