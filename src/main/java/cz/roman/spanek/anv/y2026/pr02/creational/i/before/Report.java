package cz.roman.spanek.anv.y2026.pr02.creational.i.before;

public class Report {
    private final String title;
    private final int rowCount;

    public Report(String title, int rowCount) {
        this.title = title;
        this.rowCount = rowCount;
    }

    public String getTitle() { return title; }
    public int getRowCount() { return rowCount; }
}
