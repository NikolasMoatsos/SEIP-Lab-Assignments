package codeanalyzer;

public class AnalyzerFactory {
    
    public Analyzer createAnalyzer(String analyzerType, String filepath, String fileReaderType) {
        Analyzer analyzer;
        if (analyzerType.equals("regex")) {
           analyzer = new Regex(filepath, fileReaderType);
        } else if (analyzerType.equals("strcomp")) {
            analyzer = new Strcomp(filepath, fileReaderType);
        } else {
            analyzer = new NullAnalyzer(filepath, fileReaderType);
        }

        return analyzer;
    }
    
}