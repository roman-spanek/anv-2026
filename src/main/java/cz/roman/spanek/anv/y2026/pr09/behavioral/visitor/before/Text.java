package cz.roman.spanek.anv.y2026.pr09.behavioral.visitor.before;

class Text	 {
private String content;

public Text(String content) {
    this.content = content;
}

public String getContent() {
    return content;
}

// Export methods directly in the class
public void exportAsPlainText() {
    System.out.println("Text: " + content);
}

public void exportAsXML() {
    System.out.println("<text>" + content + "</text>");
}
}
