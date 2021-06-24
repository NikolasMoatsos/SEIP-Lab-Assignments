package metricsexporter;

/**
 * The MetricsExporterFactory class, which is responsible to return the correct
 * MetricsExporter Type.
 *
 * @author NikolasMoatsos
 * @version 1.0
 * @since 2021-06-13
 */
public class MetricsExporterFactory {

    /**
     * The method which returns the correct Analyzer Type.
     * 
     * @param outputFileType the output file type
     */
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