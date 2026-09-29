package cz.roman.spanek.anv.y2026.pr02.creational.ii.before;

public class TicketService {
    // Každá část aplikace, která potřebuje přidělit ID, si generátor
    // jednoduše vytvoří sama — vypadá to jako neškodné rozhodnutí.
    private final TicketIdGenerator idGenerator = new TicketIdGenerator();

    public int createTicket(String subject) {
        int id = idGenerator.nextId();
        System.out.println("Založen tiket #" + id + ": " + subject);
        return id;
    }
}