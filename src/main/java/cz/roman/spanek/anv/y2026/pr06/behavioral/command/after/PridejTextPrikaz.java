package cz.roman.spanek.anv.y2026.pr06.behavioral.command.after;

import cz.roman.spanek.anv.y2026.pr06.behavioral.command.before.Dokument;

public class PridejTextPrikaz implements Prikaz {

    private final Dokument dokument;
    private final String text;

    public PridejTextPrikaz(Dokument dokument, String text) {
        this.dokument = dokument;
        this.text = text;
    }

    @Override
    public void proved() {
        dokument.pridej(text);
    }

    @Override
    public void vratZpet() {
        dokument.smazPosledni(text.length());
    }

    @Override
    public String popis() {
        return "Přidat text „" + text + "“";
    }
}
