package cz.roman.spanek.anv.y2026.pr09.behavioral.visitor.after;

class Text implements DocumentElement {
    private String content;

    public Text(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
