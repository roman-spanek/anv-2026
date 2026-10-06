package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.before;

public class AuditLog {

    public void zapis(String produkt, String vlastnost, int staraHodnota, int novaHodnota) {
        System.out.println("[AUDIT] " + produkt + ": " + vlastnost + " "
                + staraHodnota + " -> " + novaHodnota);
    }
}
