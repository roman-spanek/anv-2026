package cz.roman.spanek.anv.y2026.pr08.structural.decorator.after;

public class SlackDecorator extends NotifierDecorator {
    private final String user;

    public SlackDecorator(Notifier wrappee, String user) {
        super(wrappee);
        this.user = user;
    }

    @Override
    public void send(String message) {
        super.send(message); // Nechat původní notifikaci
        System.out.println("Sending Slack message to " + user + ": " + message);
    }
}
