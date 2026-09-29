package cz.roman.spanek.anv.y2026.pr02.creational.i.after;

public class Main {
    public static void main(String[] args) {
        ExporterFactory factory = new ExporterFactory();
        Report report = new Report("Q3 Sales", 42);

        byte[] pdf = factory.create("PDF").export(report);
        byte[] csv = factory.create("CSV").export(report);
        byte[] xml = factory.create("XML").export(report);
        byte[] json = factory.create("JSON").export(report);

        System.out.println(new String(pdf));
        System.out.println(new String(csv));
        System.out.println(new String(xml));
        System.out.println(new String(json));
    }
}
