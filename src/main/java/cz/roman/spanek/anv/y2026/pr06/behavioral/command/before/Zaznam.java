package cz.roman.spanek.anv.y2026.pr06.behavioral.command.before;

/**
 * Záznam v historii pro návrat zpět.
 * PROBLÉM: pole 'data' znamená u každé akce něco jiného
 * (přidaný text / smazaný text / původní text celého dokumentu).
 */
public record Zaznam(Akce akce, String data) {
}
