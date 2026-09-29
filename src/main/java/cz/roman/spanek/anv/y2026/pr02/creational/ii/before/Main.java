package cz.roman.spanek.anv.y2026.pr02.creational.ii.before;

public class Main {
    public static void main(String[] args) {
        TicketService web = new TicketService();
        EmailImportService email = new EmailImportService();

        System.out.println(web.createTicket("Nefunguje tiskárna"));
        System.out.println(email.createTicketFromEmail("Reklamace objednávky"));
        System.out.println(web.createTicket("Zapomenuté heslo"));
        System.out.println(email.createTicketFromEmail("Dotaz na fakturu"));
    }
}