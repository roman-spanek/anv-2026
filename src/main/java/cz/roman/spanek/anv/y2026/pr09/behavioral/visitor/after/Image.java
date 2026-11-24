package cz.roman.spanek.anv.y2026.pr09.behavioral.visitor.after;

class Image implements DocumentElement {
    private String imagePath;

    public Image(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
