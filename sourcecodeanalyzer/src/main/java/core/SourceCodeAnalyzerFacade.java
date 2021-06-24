package core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import metricsexporter.*;
import codeanalyzer.*;

/**
 * The facade class that calls all the necessary methods, to create and export
 * the metrics.
 *
 * @author NikolasMoatsos
 * @version 1.0
 * @since 2021-06-13
 */
public class SourceCodeAnalyzerFacade {

    /**
     * Executes the two main functions to create and export the metrics.
     * 
     * @param analyzerType   the type of the analyzer to be used
     * @param filepath       the file path of the file to be measured
     * @param fileReaderType the location of the file
     * @param outputFileType the output file type
     * @param outputFilePath the path of the output file
     * @exception IOException when an IO interruption occurs
     */
    public void executeAnalysis(String analyzerType, String filepath, String fileReaderType, String outputFileType,
            String outputFilePath) throws IOException {
        Map<String, Integer> metrics = createMetrics(analyzerType, filepath, fileReaderType);
        exportMetrics(metrics, outputFileType, outputFilePath);
    }

    /**
     * Executes the functions to create the metrcis.
     * 
     * @param analyzerType   the type of the analyzer to be used
     * @param filepath       the file path of the file to be measured
     * @param fileReaderType the location of the file
     * @exception IOException when an IO interruption occurs
     */
    public Map<String, Integer> createMetrics(String analyzerType, String filepath, String fileReaderType)
            throws IOException {
        AnalyzerFactory afactory = new AnalyzerFactory();
        Analyzer a = afactory.createAnalyzer(analyzerType, filepath, fileReaderType);

        Map<String, Integer> metrics = new HashMap<>();

        metrics.put("loc", a.calculateLOC());
        metrics.put("nom", a.calculateNOM());
        metrics.put("noc", a.calculateNOC());

        if (metrics.get("loc") == -1 || metrics.get("nom") == -1 || metrics.get("noc") == -1) {
            throw new IllegalArgumentException("Operation aborted due to unknown analyzer type");
        }

        return metrics;
    }

    /**
     * Executes the functions to export the metrcis.
     * 
     * @param metrics        the file's metrcis
     * @param outputFileType the output file type
     * @param outputFilePath the path of the output file
     * @exception IOException when an IO interruption occurs
     */
    public void exportMetrics(Map<String, Integer> metrics, String outputFileType, String outputFilePath) {
        MetricsExporterFactory mefactory = new MetricsExporterFactory();
        MetricsExporter exporter = mefactory.createMetricsExporter(outputFileType);

        exporter.writeFile(metrics, outputFilePath);
    }
}