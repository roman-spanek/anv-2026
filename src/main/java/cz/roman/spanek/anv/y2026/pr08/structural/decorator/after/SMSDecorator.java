package cz.roman.spanek.anv.y2026.pr08.structural.decorator.after;

public class SMSDecorator extends NotifierDecorator {
    private final String phone;

    public SMSDecorator(Notifier wrappee, String phone) {
        super(wrappee);
        this.phone = phone;
    }

    @Override
    public void send(String message) {
        super.send(message); // Nechat původní notifikaci
        System.out.println("Sending SMS to " + phone + ": " + message);
    }
}