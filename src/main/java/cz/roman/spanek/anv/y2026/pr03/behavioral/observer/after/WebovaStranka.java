package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.after;

public class WebovaStranka implements Pozorovatel {

    @Override
    public void aktualizuj(ZmenaProduktu zmena) {
        Produkt produkt = zmena.produkt();
        System.out.println("[WEB] Obnovuji stránku: " + produkt.getNazev() + " – "
                + produkt.getCena() + " Kč, skladem " + produkt.getPocetKusu() + " ks");
    }
}
