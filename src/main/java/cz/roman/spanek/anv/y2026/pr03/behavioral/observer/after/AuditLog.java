package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.after;

public class AuditLog implements Pozorovatel {

    @Override
    public void aktualizuj(ZmenaProduktu zmena) {
        System.out.println("[AUDIT] " + zmena.produkt().getNazev() + ": "
                + zmena.vlastnost().getPopis() + " "
                + zmena.staraHodnota() + " -> " + zmena.novaHodnota());
    }
}
