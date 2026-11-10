package cz.roman.spanek.anv.y2026.pr06.behavioral.memento;

public class  TextEditorMemento {

    private final String text;

    public TextEditorMemento(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
