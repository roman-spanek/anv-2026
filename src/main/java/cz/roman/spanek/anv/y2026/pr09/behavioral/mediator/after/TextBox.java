package cz.roman.spanek.anv.y2026.pr09.behavioral.mediator.after;

class TextBox extends Component {
    private String text = "";
    public void setText(String text) {
        this.text = text;
        mediator.notify(this, "textChanged");
    }
    public String getText() { return text; }
}

