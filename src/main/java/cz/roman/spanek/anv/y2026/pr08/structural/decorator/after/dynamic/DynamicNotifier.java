package cz.roman.spanek.anv.y2026.pr08.structural.decorator.after.dynamic;

import cz.roman.spanek.anv.y2026.pr08.structural.decorator.after.Notifier;
import cz.roman.spanek.anv.y2026.pr08.structural.decorator.after.NotifierDecorator;

import java.util.ArrayList;
import java.util.List;

class DynamicNotifier implements Notifier {
    private final Notifier base;
    private final List<NotifierDecorator> decorators = new ArrayList<>();

    public DynamicNotifier(Notifier base) {
        this.base = base;
    }

    public void addDecorator(NotifierDecorator decorator) {
        decorators.add(decorator);
    }

    @Override
    public void send(String message) {
        Notifier current = base;
        // Procházíme všechny dekorátory a "obalujeme" je dynamicky
        for (NotifierDecorator decorator : decorators) {
            decorator.setWrappee(current); // Přepojení wrappee
            current = decorator;
        }
        current.send(message); // Posíláme zprávu přes všechny vrstvy
    }
}