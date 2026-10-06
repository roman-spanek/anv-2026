package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.after;

/**
 * Rozhraní pozorovatele (Observer). Subjekt (Produkt) zná jen toto rozhraní,
 * nikoli konkrétní třídy, které ho implementují.
 */
@FunctionalInterface
public interface Pozorovatel {

    void aktualizuj(ZmenaProduktu zmena);
}
