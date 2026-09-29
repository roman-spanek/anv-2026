package cz.roman.spanek.anv.y2026.pr02.creational.ii.before;

public class TicketIdGenerator {
    private int counter = 0;

    public int nextId() {
        counter++;
        return counter;
    }
}