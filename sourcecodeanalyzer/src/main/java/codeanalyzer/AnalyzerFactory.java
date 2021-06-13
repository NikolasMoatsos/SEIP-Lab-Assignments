package codeanalyzer;

/**
 * The AnalyzerFactory class, which is responsible to return the correct
 * Analyzer Type.
 *
 * @author NikolasMoatsos
 * @version 1.0
 * @since 2021-06-13
 */
public class AnalyzerFactory {

    /**
     * The method which returns the correct Analyzer Type.
     * 
     * @param analyzerType   the type of the analyzer to be used
     * @param filepath       the file path of the file to be measured
     * @param fileReaderType the location of the file
     */
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