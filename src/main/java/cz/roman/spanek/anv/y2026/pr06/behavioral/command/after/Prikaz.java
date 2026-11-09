package cz.roman.spanek.anv.y2026.pr06.behavioral.command.after;

/**
 * Příkaz (rozhraní Command). Každá operace nad dokumentem je objekt,
 * který umí sám sebe provést i vrátit zpět.
 */
public interface Prikaz {

    void proved();

    void vratZpet();

    String popis();
}
