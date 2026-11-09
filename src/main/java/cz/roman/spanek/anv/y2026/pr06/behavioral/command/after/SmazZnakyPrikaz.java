package cz.roman.spanek.anv.y2026.pr06.behavioral.command.after;

import cz.roman.spanek.anv.y2026.pr06.behavioral.command.before.Dokument;

public class SmazZnakyPrikaz implements Prikaz {

    private final Dokument dokument;
    private final int pocet;
    private String smazano = "";   // stav potřebný pro návrat zpět; zná ho jen tento příkaz

    public SmazZnakyPrikaz(Dokument dokument, int pocet) {
        this.dokument = dokument;
        this.pocet = pocet;
    }

    @Override
    public void proved() {
        smazano = dokument.smazPosledni(pocet);
    }

    @Override
    public void vratZpet() {
        dokument.pridej(smazano);
    }

    @Override
    public String popis() {
        return "Smazat posledních " + pocet + " znaků";
    }
}
