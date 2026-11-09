package cz.roman.spanek.anv.y2026.pr06.behavioral.command.before;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * cz.roman.spanek.anv.y2026.pr06.behavioral.command.before.Editor zná všechny operace dokumentu a sám řeší i návrat zpět.
 */
public class Editor {

    private final Dokument dokument = new Dokument();
    private final Deque<Zaznam> historie = new ArrayDeque<>();

    // PROBLÉM: parametr je řetězec - typová bezpečnost nulová, čísla se musí parsovat
    public void provedAkci(Akce akce, String parametr) {
        switch (akce) {
            case PRIDEJ_TEXT:
                dokument.pridej(parametr);
                historie.push(new Zaznam(akce, parametr));
                break;

            case SMAZ_ZNAKY:
                String smazano = dokument.smazPosledni(Integer.parseInt(parametr));
                historie.push(new Zaznam(akce, smazano));
                break;

            default:
                throw new IllegalStateException("Neznámá akce: " + akce);
        }
    }

    // PROBLÉM: druhý switch, který zrcadlí první - při přidání akce ho snadno zapomeneme
    public boolean vratZpet() {
        if (historie.isEmpty()) {
            return false;
        }
        Zaznam zaznam = historie.pop();

        switch (zaznam.akce()) {
            case PRIDEJ_TEXT:
                dokument.smazPosledni(zaznam.data().length());
                break;

            case SMAZ_ZNAKY:
                dokument.pridej(zaznam.data());
                break;

            case VELKA_PISMENA:
                dokument.nastavText(zaznam.data());
                break;

            default:
                throw new IllegalStateException("Neznámá akce: " + zaznam.akce());
        }
        return true;
    }

    public String getText() {
        return dokument.getText();
    }
}
