package cz.roman.spanek.anv.y2026.pr02.creational.i.after;

public class PdfExporter implements DocumentExporter {
    @Override
    public byte[] export(Report report) {
        System.out.println("Otevírám PDF knihovnu...");
        System.out.println("Renderuji hlavičku, tabulky, patičku do PDF");
        return ("PDF:" + report.getTitle()).getBytes();
    }
}
