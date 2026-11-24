package cz.roman.spanek.anv.y2026.pr09.behavioral.visitor.after;

import java.util.Arrays;
import java.util.List;

public class VisitorDemo {
    public static void main(String[] args) {
        List<DocumentElement> elements = Arrays.asList(
                new Text("Hello World"),
                new Image("/images/picture.png"),
                new Table(3, 4)
        );

        System.out.println("Exporting as plain text:");
        DocumentVisitor plainTextExporter = new PlainTextExporter();
        elements.forEach(element -> element.accept(plainTextExporter));

        System.out.println("\nExporting as XML:");
        DocumentVisitor xmlExporter = new XMLExporter();
        elements.forEach(element -> element.accept(xmlExporter));
    }
}