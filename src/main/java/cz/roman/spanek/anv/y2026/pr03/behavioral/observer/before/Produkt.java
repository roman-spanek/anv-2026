package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.before;

import java.util.ArrayList;
import java.util.List;

/**
 * Produkt v e-shopu. Při změně ceny nebo skladové zásoby musí "dát vědět"
 * ostatním částem systému.
 */
public class Produkt {

    private final String nazev;
    private int cena;
    private int pocetKusu;

    private final AuditLog auditLog = new AuditLog();
    private final WebovaStranka webovaStranka = new WebovaStranka();
    private final SkladovyAlert skladovyAlert = new SkladovyAlert(2);
    private final List<SledujiciZakaznik> sledujiciZakaznici = new ArrayList<>();

    public Produkt(String nazev, int cena, int pocetKusu) {
        this.nazev = nazev;
        this.cena = cena;
        this.pocetKusu = pocetKusu;
    }

    public void pridejSledujiciho(SledujiciZakaznik zakaznik) {
        sledujiciZakaznici.add(zakaznik);
    }

    public void setCena(int novaCena) {
        if (novaCena == cena) {
            return;
        }
        int staraCena = cena;
        cena = novaCena;


        auditLog.zapis(nazev, "cena", staraCena, novaCena);
        webovaStranka.obnov(this);
        for (SledujiciZakaznik zakaznik : sledujiciZakaznici) {

            if (novaCena < staraCena && novaCena <= zakaznik.getZadanaCena()) {
                zakaznik.upozorniNaSnizeniCeny(this);
            }
        }
    }

    public void setPocetKusu(int novyPocet) {
        if (novyPocet == pocetKusu) {
            return;
        }
        int staryPocet = pocetKusu;
        pocetKusu = novyPocet;

        auditLog.zapis(nazev, "počet kusů", staryPocet, novyPocet);
        webovaStranka.obnov(this);
        skladovyAlert.zkontroluj(this);
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
