package cz.roman.spanek.anv.y2026.pr09.behavioral.visitor.before;

class Image {
    private String imagePath;

    public Image(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void exportAsPlainText() {
        System.out.println("Image: [image at " + imagePath + "]");
    }

    public void exportAsXML() {
        System.out.println("<image src=\"" + imagePath + "\"/>");
    }
}