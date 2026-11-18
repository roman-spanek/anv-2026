package cz.roman.spanek.anv.y2026.pr08.structural.decorator.before;


public class Main {
    public static void main(String[] args) {
        Notifier notifier = new Notifier("user@example.com");
        notifier.send("Important update!");

        // Klient musí volat všechny kanály ručně
        notifier.sendSMS("123456789", "Important update!");
        notifier.sendSlack("@user", "Important update!");
    }
}