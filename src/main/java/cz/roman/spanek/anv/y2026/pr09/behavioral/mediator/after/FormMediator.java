package cz.roman.spanek.anv.y2026.pr09.behavioral.mediator.after;

// Concrete mediator
class FormMediator implements Mediator {
    private TextBox nameTextBox;
    private CheckBox subscribeCheckBox;
    private Button submitButton;

    public FormMediator(TextBox textBox, CheckBox checkBox, Button button) {
        this.nameTextBox = textBox;
        this.subscribeCheckBox = checkBox;
        this.submitButton = button;

        // set mediator for all components
        textBox.setMediator(this);
        checkBox.setMediator(this);
        button.setMediator(this);
    }

    @Override
    public void notify(Component sender, String event) {
        if (sender == submitButton && event.equals("click")) {
            if (!nameTextBox.getText().isEmpty()) {
                System.out.println("Form submitted with name: " + nameTextBox.getText());
            }
            if (subscribeCheckBox.isChecked()) {
                System.out.println("User subscribed to newsletter.");
            }
        }
    }
}
