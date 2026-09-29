package cz.roman.spanek.anv.y2026.pr02.creational.i;

import cz.roman.spanek.anv.y2026.pr02.creational.i.before.Report;
import cz.roman.spanek.anv.y2026.pr02.creational.i.before.ReportExporter;

public class Main {
    public static void main(String[] args) {
        ReportExporter exporter = new ReportExporter();
        Report report = new Report("Q3 Sales", 42);

        byte[] pdf = exporter.export("PDF", report);
        byte[] csv = exporter.export("CSV", report);

        System.out.println(new String(pdf));
        System.out.println(new String(csv));
    }
}