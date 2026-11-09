package cz.roman.spanek.anv.y2026.pr06.behavioral.command.before;

import java.util.Locale;

/**
 * Textový dokument - nabízí jednotlivé operace nad textem.
 * V terminologii vzoru Command je to příjemce (Receiver).
 */
public class Dokument {

    private final StringBuilder text = new StringBuilder();

    public void pridej(String pridavany) {
        text.append(pridavany);
    }

    /** Smaže posledních 'pocet' znaků (nejvýše tolik, kolik text obsahuje) a vrátí smazaný řetězec. */
    public String smazPosledni(int pocet) {
        if (pocet < 0) {
            throw new IllegalArgumentException("Počet znaků nesmí být záporný: " + pocet);
        }
        int zacatek = Math.max(0, text.length() - pocet);
        String smazano = text.substring(zacatek);
        text.setLength(zacatek);
        return smazano;
    }

    public void naVelkaPismena() {
        String velka = text.toString().toUpperCase(Locale.ROOT);
        text.setLength(0);
        text.append(velka);
    }

    public String getText() {
        return text.toString();
    }

    public void nastavText(String novyText) {
        text.setLength(0);
        text.append(novyText);
    }
}
