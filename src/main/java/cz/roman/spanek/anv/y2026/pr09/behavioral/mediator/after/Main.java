package cz.roman.spanek.anv.y2026.pr09.behavioral.mediator.after;
public class Main {
    public static void main(String[] args) {
        TextBox name = new TextBox();
        CheckBox subscribe = new CheckBox();
        Button submit = new Button();

        FormMediator mediator = new FormMediator(name, subscribe, submit);

        name.setText("Alice");
        subscribe.setChecked(true);
        submit.click();
    }
}
