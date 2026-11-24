package cz.roman.spanek.anv.y2026.pr09.behavioral.mediator.before;

class Form {
    TextBox nameTextBox = new TextBox();
    CheckBox subscribeCheckBox = new CheckBox();
    Button submitButton = new Button();

    public Form() {
        // Tightly coupled: submit button needs to know about all components
        submitButton.setOnClick(() -> {
            if (!nameTextBox.getText().isEmpty()) {
                System.out.println("Form submitted with name: " + nameTextBox.getText());
            }
            if (subscribeCheckBox.isChecked()) {
                System.out.println("User subscribed to newsletter.");
            }
        });
    }

    void simulateUserAction() {
        nameTextBox.setText("Alice");
        subscribeCheckBox.setChecked(true);
        submitButton.click();
    }
}
