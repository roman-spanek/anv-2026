package cz.roman.spanek.anv.y2026.pr02.creational.i.after;

public class HtmlExporter implements DocumentExporter {
    @Override
    public byte[] export(Report report) {
        String html = "<html><body><h1>" + report.getTitle()
                + "</h1><p>Rows: " + report.getRowCount() + "</p></body></html>";
        return html.getBytes();
    }
}
