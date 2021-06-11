package codeanalyzer;

public class MetricsExporterFactory {
    
    public MetricsExporter createMetricExporter(String fileType) {
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