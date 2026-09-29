package cz.roman.spanek.anv.y2026.pr02.creational.ii.before;

public class EmailImportService {
    // Jiný modul, který také zakládá tikety — z příchozích e-mailů.
    private final TicketIdGenerator idGenerator = new TicketIdGenerator();

    public int createTicketFromEmail(String emailSubject) {
        int id = idGenerator.nextId();
        System.out.println("Tiket z e-mailu #" + id + ": " + emailSubject);
        return id;
    }
}
