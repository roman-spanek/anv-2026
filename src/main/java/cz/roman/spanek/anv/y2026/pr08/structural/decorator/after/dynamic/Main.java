package cz.roman.spanek.anv.y2026.pr08.structural.decorator.after.dynamic;

import cz.roman.spanek.anv.y2026.pr08.structural.decorator.after.EmailNotifier;
import cz.roman.spanek.anv.y2026.pr08.structural.decorator.after.Notifier;
import cz.roman.spanek.anv.y2026.pr08.structural.decorator.after.SMSDecorator;
import cz.roman.spanek.anv.y2026.pr08.structural.decorator.after.SlackDecorator;

public class Main {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier("user@example.com");

        DynamicNotifier dynamicNotifier = new DynamicNotifier(emailNotifier);

        // Dynamicky přidáváme kanály
        dynamicNotifier.addDecorator(new SMSDecorator(null, "123456789"));
        dynamicNotifier.addDecorator(new SlackDecorator(null, "@user"));

        // Jediný volání pošle zprávu všemi kanály
        dynamicNotifier.send("Critical system alert!");
    }
}
