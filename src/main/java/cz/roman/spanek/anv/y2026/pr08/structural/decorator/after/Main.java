package cz.roman.spanek.anv.y2026.pr08.structural.decorator.after;

public class Main {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier("user@example.com");

        // Přidání SMS a Slack dekorátorů
        notifier = new SMSDecorator(notifier, "123456789");
        notifier = new SlackDecorator(notifier, "@user");

        // Jediný volání - všechny kanály se pošlou automaticky
        notifier.send("Important update!");

        Notifier notifier2 =  new SMSDecorator(new SlackDecorator(new EmailNotifier("user2@example.com"), "@user"), "2-123456789");
        notifier2.send("Important update2!");
    }
}