package cz.roman.spanek.anv.y2026.pr06.behavioral.command.after;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Makro: skupina příkazů, která se chová jako jediný příkaz.
 * Provede je popořadě, zpět je vrací v opačném pořadí.
 */
public class MakroPrikaz implements Prikaz {

    private final String nazev;
    private final List<Prikaz> prikazy;

    public MakroPrikaz(String nazev, Prikaz... prikazy) {
        this.nazev = nazev;
        this.prikazy = List.of(prikazy);
    }

    @Override
    public void proved() {
        for (Prikaz prikaz : prikazy) {
            prikaz.proved();
        }
    }

    @Override
    public void vratZpet() {
        for (int i = prikazy.size() - 1; i >= 0; i--) {
            prikazy.get(i).vratZpet();
        }
    }

    @Override
    public String popis() {
        return "Makro „" + nazev + "“ [" + prikazy.stream()
                .map(Prikaz::popis)
                .collect(Collectors.joining("; ")) + "]";
    }
}
