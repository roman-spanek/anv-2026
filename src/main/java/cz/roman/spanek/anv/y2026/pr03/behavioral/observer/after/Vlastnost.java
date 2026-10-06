package cz.roman.spanek.anv.y2026.pr03.behavioral.observer.after;

public enum Vlastnost {

    CENA("cena"),
    POCET_KUSU("počet kusů");

    private final String popis;

    Vlastnost(String popis) {
        this.popis = popis;
    }

    public String getPopis() {
        return popis;
    }
}
