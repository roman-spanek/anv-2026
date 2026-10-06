package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.before;

public class WebovaStranka {

    public void obnov(Produkt produkt) {
        System.out.println("[WEB] Obnovuji stránku: " + produkt.getNazev() + " – "
                + produkt.getCena() + " Kč, skladem " + produkt.getPocetKusu() + " ks");
    }
}
