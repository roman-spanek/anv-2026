package cz.roman.spanek.anv.y2026.pr06.behavioral.command.after;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Volající (Invoker). Provádí příkazy a udržuje historii pro "zpět" a "znovu".
 * Nezná cz.roman.spanek.anv.y2026.pr06.behavioral.command.before.Dokument ani žádný konkrétní příkaz - pracuje jen s rozhraním Prikaz.
 */
public class SpravcePrikazu {

    private final Deque<Prikaz> zasobnikZpet = new ArrayDeque<>();
    private final Deque<Prikaz> zasobnikZnovu = new ArrayDeque<>();

    public void proved(Prikaz prikaz) {
        prikaz.proved();                // do historie se dostane jen úspěšně provedený příkaz
        zasobnikZpet.push(prikaz);
        zasobnikZnovu.clear();          // nový příkaz ruší možnost "znovu"
    }

    public boolean vratZpet() {
        if (zasobnikZpet.isEmpty()) {
            return false;
        }
        Prikaz prikaz = zasobnikZpet.pop();
        prikaz.vratZpet();
        zasobnikZnovu.push(prikaz);
        return true;
    }

    public boolean znovu() {
        if (zasobnikZnovu.isEmpty()) {
            return false;
        }
        Prikaz prikaz = zasobnikZnovu.pop();
        prikaz.proved();
        zasobnikZpet.push(prikaz);
        return true;
    }

    public void vypisHistorii() {
        System.out.println("    Historie provedených příkazů:");
        int cislo = 1;
        Iterator<Prikaz> it = zasobnikZpet.descendingIterator();   // od nejstaršího
        while (it.hasNext()) {
            System.out.println("      " + cislo++ + ". " + it.next().popis());
        }
    }
}
