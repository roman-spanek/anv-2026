package cz.roman.spanek.anv.y2026.pr02.creational.i.before;

public class ReportExporter {

    public byte[] export(String format, Report report) {
        if (format.equals("PDF")) {
            System.out.println("Otevírám PDF knihovnu...");
            System.out.println("Renderuji hlavičku, tabulky, patičku do PDF");
            return ("PDF:" + report.getTitle()).getBytes();

        } else if (format.equals("CSV")) {
            String sb = "title,rows\n" + report.getTitle() + "," + report.getRowCount();
            return sb.getBytes();

        } else if (format.equals("HTML")) {
            String html = "<html><body><h1>" + report.getTitle()
                    + "</h1><p>Rows: " + report.getRowCount() + "</p></body></html>";
            return html.getBytes();

        } else {
            throw new IllegalArgumentException("Neznámý formát: " + format);
        }
    }
}