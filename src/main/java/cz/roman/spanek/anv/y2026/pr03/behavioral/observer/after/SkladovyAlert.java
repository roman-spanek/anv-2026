package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.after;

public class SkladovyAlert implements Pozorovatel {

    private final int minimum;

    public SkladovyAlert(int minimum) {
        this.minimum = minimum;
    }

    @Override
    public void aktualizuj(ZmenaProduktu zmena) {
        // Každý pozorovatel sám rozhodne, co ho zajímá
        if (zmena.vlastnost() == Vlastnost.POCET_KUSU && zmena.novaHodnota() <= minimum) {
            System.out.println("[SKLAD] POZOR: u produktu " + zmena.produkt().getNazev()
                    + " zbývá jen " + zmena.novaHodnota() + " ks (minimum " + minimum + ")");
        }
    }
}
