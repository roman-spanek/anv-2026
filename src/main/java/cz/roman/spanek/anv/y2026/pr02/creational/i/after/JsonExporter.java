package cz.roman.spanek.anv.y2026.pr02.creational.i.after;

public class JsonExporter implements DocumentExporter {
    @Override
    public byte[] export(Report report) {
        String json = "{\"title\":\"" + report.getTitle()
                + "\",\"rows\":" + report.getRowCount() + "}";
        return json.getBytes();
    }
}
