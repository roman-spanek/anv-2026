package cz.roman.spanek.anv.y2026.pr02.creational.i.after;

public class XmlExporter implements DocumentExporter {
    @Override
    public byte[] export(Report report) {
        String xml = "<report><title>" + report.getTitle()
                + "</title><rows>" + report.getRowCount() + "</rows></report>";
        return xml.getBytes();
    }
}
