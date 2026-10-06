package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.before;

public class SkladovyAlert {

    private final int minimum;

    public SkladovyAlert(int minimum) {
        this.minimum = minimum;
    }

    public void zkontroluj(Produkt produkt) {
        if (produkt.getPocetKusu() <= minimum) {
            System.out.println("[SKLAD] POZOR: u produktu " + produkt.getNazev()
                    + " zbývá jen " + produkt.getPocetKusu() + " ks (minimum " + minimum + ")");
        }
    }
}
