package core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import metricsexporter.*;
import codeanalyzer.*;

public class SourceCodeAnalyzerFacade {

    public void executeAnalysis(String analyzerType, String filepath, String fileReaderType, String outputFileType, String outputFilePath) throws IOException  { 
        Map<String, Integer> metrics = createMetrics(analyzerType, filepath, fileReaderType);
        exportMetrics(metrics, outputFileType, outputFilePath);
    }

    public Map<String, Integer> createMetrics(String analyzerType, String filepath, String fileReaderType) throws IOException {
        AnalyzerFactory afactory = new AnalyzerFactory();
        Analyzer a = afactory.createAnalyzer(analyzerType, filepath, fileReaderType);
        
        Map<String, Integer> metrics = new HashMap<>();
        
        metrics.put("loc", a.calculateLOC());
		metrics.put("nom", a.calculateNOM());
		metrics.put("noc", a.calculateNOC());

        if(metrics.get("loc") == -1 || metrics.get("nom") == -1 || metrics.get("noc") == -1) {
            throw new IllegalArgumentException("Operation aborted due to unknown analyzer type");
        }

        return metrics;
    }

    public void exportMetrics(Map<String, Integer> metrics, String outputFileType, String outputFilePath) {
        MetricsExporterFactory mefactory = new MetricsExporterFactory();
        MetricsExporter exporter = mefactory.createMetricsExporter(outputFileType);

        exporter.writeFile(metrics, outputFilePath);
    }
}