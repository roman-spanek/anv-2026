package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.after;

/**
 * Popis toho, co se změnilo. Pozorovatel dostane všechno potřebné
 * a nemusí se Produktu na nic dalšího ptát (tzv. "push" model).
 */
public record ZmenaProduktu(Produkt produkt, Vlastnost vlastnost, int staraHodnota, int novaHodnota) {
}
