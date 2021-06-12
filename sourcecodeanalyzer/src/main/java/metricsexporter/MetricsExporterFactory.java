package metricsexporter;

public class MetricsExporterFactory {
    
    public MetricsExporter createMetricsExporter(String fileType) {
        MetricsExporter exporter;
        if (fileType.equals("csv")) {
            exporter = new CsvWriter();
        } else if (fileType.equals("json")) {
            exporter = new JsonWriter();
        } else {
            exporter = new NullWriter();
        }
        
        return exporter;
    }
}