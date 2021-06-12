package core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import metricsexporter.*;
import codeanalyzer.*;

public class SourceCodeAnalyzerFacade {
    String analyzerType;
    String filepath;
    String fileReaderType;
    String outputFileType;
    String outputFilePath;

    public SourceCodeAnalyzerFacade(String analyzerType, String filepath, String fileReaderType, String outputFileType, String outputFilePath) {
        this.analyzerType = analyzerType;
        this.filepath = filepath;
        this.fileReaderType = fileReaderType;
        this.outputFileType = outputFileType;
        this.outputFilePath = outputFilePath;
    }

    public void executeAnalysis() throws IOException  {
        AnalyzerFactory afactory = new AnalyzerFactory();
        Analyzer a = afactory.createAnalyzer(analyzerType, filepath, fileReaderType);
        
        Map<String, Integer> metrics = new HashMap<>();
        
        metrics.put("loc", a.calculateLOC());
		metrics.put("nom", a.calculateNOM());
		metrics.put("noc", a.calculateNOC());

        if(metrics.get("loc") == -1 || metrics.get("nom") == -1 || metrics.get("noc") == -1) {
            System.err.println("Operation Aborted due to unknown file reader type");
        } else if(metrics.get("loc") == -2 || metrics.get("nom") == -2 || metrics.get("noc") == -2) {
            System.err.println("Operation Aborted due to unknown analyzer type");
        } else {
            MetricsExporterFactory mefactory = new MetricsExporterFactory();
            MetricsExporter exporter = mefactory.createMetricsExporter(outputFileType);

            exporter.writeFile(metrics, outputFilePath);
        }
    }
}