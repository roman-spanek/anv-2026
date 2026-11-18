package cz.roman.spanek.anv.y2026.pr08.structural.decorator.after;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class NotifierDecorator implements Notifier {
    private Notifier wrappee;

    public NotifierDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void send(String message) {
        wrappee.send(message); // Delegace na obalený objekt
    }
}
