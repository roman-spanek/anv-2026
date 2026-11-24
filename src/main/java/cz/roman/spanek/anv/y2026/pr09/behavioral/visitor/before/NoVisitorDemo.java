package cz.roman.spanek.anv.y2026.pr09.behavioral.visitor.before;

import java.util.Arrays;
import java.util.List;

public class NoVisitorDemo {
    public static void main(String[] args) {
        List<Object> elements = Arrays.asList(
                new Text("Hello World"),
                new Image("/images/picture.png"),
                new Table(3, 4)
        );

        System.out.println("Exporting as plain text:");
        for (Object elem : elements) {
            if (elem instanceof Text) {
                ((Text) elem).exportAsPlainText();
            } else if (elem instanceof Image) {
                ((Image) elem).exportAsPlainText();
            } else if (elem instanceof Table) {
                ((Table) elem).exportAsPlainText();
            }
        }

        System.out.println("\nExporting as XML:");
        for (Object elem : elements) {
            if (elem instanceof Text) {
                ((Text) elem).exportAsXML();
            } else if (elem instanceof Image) {
                ((Image) elem).exportAsXML();
            } else if (elem instanceof Table) {
                ((Table) elem).exportAsXML();
            }
        }
    }
}
