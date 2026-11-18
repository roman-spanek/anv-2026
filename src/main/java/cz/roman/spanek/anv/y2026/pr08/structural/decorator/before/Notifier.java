package cz.roman.spanek.anv.y2026.pr08.structural.decorator.before;

class Notifier {
    private final String email;

    public Notifier(String email) {
        this.email = email;
    }

    public void send(String message) {
        System.out.println("Sending Email to " + email + ": " + message);
    }

    // Přidání nových kanálů přímo do třídy => porušení OCP
    public void sendSMS(String phone, String message) {
        System.out.println("Sending SMS to " + phone + ": " + message);
    }

    public void sendSlack(String user, String message) {
        System.out.println("Sending Slack message to " + user + ": " + message);
    }
}
