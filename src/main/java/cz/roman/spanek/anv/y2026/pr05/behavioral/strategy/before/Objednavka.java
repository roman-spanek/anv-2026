package cz.roman.spanek.anv.y2026.pr05.behavioral.strategy.before;

import lombok.Getter;
import lombok.Setter;

/**
 * Objednávka v e-shopu. Sama počítá cenu dopravy podle zvoleného typu dopravy.
 */
public class Objednavka {

    @Getter
    private final String zakaznik;
    @Getter
    private final int hodnotaZbozi;     // v Kč
    @Getter
    private final double hmotnostKg;
    @Setter
    private TypDopravy doprava;

    public Objednavka(String zakaznik, int hodnotaZbozi, double hmotnostKg, TypDopravy doprava) {
        this.zakaznik = zakaznik;
        this.hodnotaZbozi = hodnotaZbozi;
        this.hmotnostKg = hmotnostKg;
        this.doprava = doprava;
    }

    // PROBLÉM: všechny algoritmy výpočtu ceny jsou v jedné metodě
    public int cenaDopravy() {
        int zapocateKg = (int) Math.ceil(hmotnostKg);

        switch (doprava) {
            case OSOBNI_ODBER:
                return 0;

            case VYDEJNI_MISTO:
                if (hmotnostKg > 20) {
                    throw new IllegalArgumentException("Výdejní místo přepravuje zásilky do 20 kg.");
                }
                int zaklad = (hodnotaZbozi >= 1500) ? 0 : 49;       // zdarma od 1500 Kč zboží
                return zaklad + 10 * Math.max(0, zapocateKg - 5);   // příplatek za každý kg nad 5 kg

            case KURYR:
                return 89 + 12 * Math.max(0, zapocateKg - 2);       // příplatek za každý kg nad 2 kg

            case EXPRES:
                return 199 + 20 * zapocateKg;

            default:
                throw new IllegalStateException("Neznámý typ dopravy: " + doprava);
        }
    }

    // PROBLÉM: druhý switch nad stejným enumem - při přidání dopravy ho snadno zapomeneme upravit
    public String nazevDopravy() {
        return switch (doprava) {
            case OSOBNI_ODBER -> "Osobní odběr";
            case VYDEJNI_MISTO -> "Výdejní místo";
            case KURYR -> "Kurýr";
            case EXPRES -> "Expresní kurýr (do 24 h)";
            default -> throw new IllegalStateException("Neznámý typ dopravy: " + doprava);
        };
    }

    public int celkovaCena() {
        return hodnotaZbozi + cenaDopravy();
    }

}
