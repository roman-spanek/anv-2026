package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.after;

import java.util.ArrayList;
import java.util.List;

/**
 * Produkt v e-shopu – v roli subjektu (Subject / Observable).
 * Nezná žádnou konkrétní třídu, která na jeho změny reaguje.
 */
public class Produkt {

    private final String nazev;
    private int cena;
    private int pocetKusu;

    private final List<Pozorovatel> pozorovatele = new ArrayList<>();

    public Produkt(String nazev, int cena, int pocetKusu) {
        this.nazev = nazev;
        this.cena = cena;
        this.pocetKusu = pocetKusu;
    }

    // ---- Správa pozorovatelů ----

    public void pridejPozorovatele(Pozorovatel pozorovatel) {
        pozorovatele.add(pozorovatel);
    }

    public void odeberPozorovatele(Pozorovatel pozorovatel) {
        pozorovatele.remove(pozorovatel);
    }

    private void upozorni(ZmenaProduktu zmena) {
        // Iterujeme nad kopií – pozorovatel se smí během notifikace odhlásit
        for (Pozorovatel pozorovatel : List.copyOf(pozorovatele)) {
            pozorovatel.aktualizuj(zmena);
        }
    }

    // ---- Změny stavu ----

    public void setCena(int novaCena) {
        if (novaCena == cena) {
            return;
        }
        int staraCena = cena;
        cena = novaCena;
        upozorni(new ZmenaProduktu(this, Vlastnost.CENA, staraCena, novaCena));
    }

    public void setPocetKusu(int novyPocet) {
        if (novyPocet == pocetKusu) {
            return;
        }
        int staryPocet = pocetKusu;
        pocetKusu = novyPocet;
        upozorni(new ZmenaProduktu(this, Vlastnost.POCET_KUSU, staryPocet, novyPocet));
    }

    public String getNazev() {
        return nazev;
    }

    public int getCena() {
        return cena;
    }

    public int getPocetKusu() {
        return pocetKusu;
    }
}
