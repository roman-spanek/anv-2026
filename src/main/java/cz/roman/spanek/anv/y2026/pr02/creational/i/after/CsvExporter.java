package cz.roman.spanek.anv.y2026.pr02.creational.i.after;

public class CsvExporter implements DocumentExporter {
    @Override
    public byte[] export(Report report) {
        String sb = "title,rows\n" +
                report.getTitle() + "," + report.getRowCount();
        return sb.getBytes();
    }
}
