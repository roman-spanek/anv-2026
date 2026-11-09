package cz.roman.spanek.anv.y2026.pr06.behavioral.command.after;

import cz.roman.spanek.anv.y2026.pr06.behavioral.command.before.Dokument;

public class NaVelkaPismenaPrikaz implements Prikaz {

    private final Dokument dokument;
    private String puvodniText = "";   // převod na velká písmena nelze obrátit výpočtem -> pamatujeme si text

    public NaVelkaPismenaPrikaz(Dokument dokument) {
        this.dokument = dokument;
    }

    @Override
    public void proved() {
        puvodniText = dokument.getText();
        dokument.naVelkaPismena();
    }

    @Override
    public void vratZpet() {
        dokument.nastavText(puvodniText);
    }

    @Override
    public String popis() {
        return "Velká písmena";
    }
}
