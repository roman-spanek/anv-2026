package cz.roman.spanek.anv.y2026.pr02.creational.i.after;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ExporterFactory {

    private final Map<String, Supplier<DocumentExporter>> registry = new HashMap<>();

    public ExporterFactory() {
        register("PDF", PdfExporter::new);
        register("CSV", CsvExporter::new);
        register("HTML", HtmlExporter::new);
        register("XML", XmlExporter::new);
        register("JSON", JsonExporter::new);
    }

    public void register(String format, Supplier<DocumentExporter> supplier) {
        registry.put(format, supplier);
    }

    public DocumentExporter create(String format) {
        Supplier<DocumentExporter> supplier = registry.get(format);
        if (supplier == null) {
            throw new IllegalArgumentException("Neznámý formát: " + format);
        }
        return supplier.get();
    }
}
