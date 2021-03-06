package codeanalyzer;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import filereader.*;

/**
 * The Analyzer abstract class and the classes that extends it. It contains all
 * the methods to calculate the file metrics.
 *
 * @author NikolasMoatsos
 * @version 1.0
 * @since 2021-06-13
 */
public abstract class Analyzer {
    protected String filepath;
    protected SourceFileReader fileReader;

    public Analyzer(String filepath, String fileReaderType) {
        this.filepath = filepath;
        SourceFileReaderFactory sfrfactory = new SourceFileReaderFactory();
        this.fileReader = sfrfactory.createSourceFileReader(fileReaderType);
    }

    /**
     * Calculates the LOC metric.
     * 
     * @exception IOException when an IO interruption occurs
     */
    public abstract int calculateLOC() throws IOException;

    /**
     * Calculates the NOM metric.
     * 
     * @exception IOException when an IO interruption occurs
     */
    public abstract int calculateNOM() throws IOException;

    /**
     * Calculates the NOC metric.
     * 
     * @exception IOException when an IO interruption occurs
     */
    public abstract int calculateNOC() throws IOException;
}

/**
 * The Regex class that implements the metrics methods with the regex analysis
 * type.
 */
class Regex extends Analyzer {

    public Regex(String filepath, String fileReaderType) {
        super(filepath, fileReaderType);
    }

    @Override
    public int calculateLOC() throws IOException {
        String sourceCode = fileReader.readFileIntoString(filepath);
        if (sourceCode != null) {
            Pattern pattern = Pattern.compile("((//.*)|(/\\*.*)|(\\*+.*))");
            Matcher nonCodeLinesMatcher = pattern.matcher(sourceCode);

            int nonCodeLinesCounter = 0;
            while (nonCodeLinesMatcher.find()) {
                nonCodeLinesCounter++;
            }

            int sourceFileLength = sourceCode.split("\n").length;
            int loc = sourceFileLength - nonCodeLinesCounter;

            return loc;
        } else {
            throw new IllegalArgumentException("Operation aborted due to unknown file reader");
        }
    }

    @Override
    public int calculateNOM() throws IOException {
        String sourceCode = fileReader.readFileIntoString(filepath);
        if (sourceCode != null) {
            Pattern pattern = Pattern.compile(
                    ".*(public |protected |private |static )?[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;]).*");
            Matcher methodSignatures = pattern.matcher(sourceCode);

            int methodCounter = 0;
            while (methodSignatures.find()) {
                methodCounter++;
            }
            return methodCounter;
        } else {
            throw new IllegalArgumentException("Operation aborted due to unknown file reader");
        }
    }

    @Override
    public int calculateNOC() throws IOException {
        String sourceCode = fileReader.readFileIntoString(filepath);
        if (sourceCode != null) {
            Pattern pattern = Pattern.compile(".*\\s*class\\s+.*");
            Matcher classSignatures = pattern.matcher(sourceCode);

            int classCounter = 0;
            while (classSignatures.find()) {
                classCounter++;
            }
            return classCounter;
        } else {
            throw new IllegalArgumentException("Operation aborted due to unknown file reader");
        }
    }
}

/**
 * The Strcomp class that implements the metrics methods with the strcomp
 * analysis type.
 */
class Strcomp extends Analyzer {

    public Strcomp(String filepath, String fileReaderType) {
        super(filepath, fileReaderType);
    }

    @Override
    public int calculateLOC() throws IOException {
        List<String> sourceCodeList = fileReader.readFileIntoList(filepath);
        if (sourceCodeList != null) {
            int nonCodeLinesCounter = 0;
            for (String line : sourceCodeList) {
                line = line.trim();
                if (line.startsWith("//") || line.startsWith("/*") || line.startsWith("*") || line.equals("{")
                        || line.equals("}") || line.equals(""))
                    nonCodeLinesCounter++;
            }
            int loc = sourceCodeList.size() - nonCodeLinesCounter;
            return loc;
        } else {
            throw new IllegalArgumentException("Operation aborted due to unknown file reader");
        }
    }

    @Override
    public int calculateNOM() throws IOException {
        List<String> sourceCodeList = fileReader.readFileIntoList(filepath);
        if (sourceCodeList != null) {
            int methodCounter = 0;
            for (String line : sourceCodeList) {
                line = line.trim();
                if (((line.contains("public") || line.contains("private") || line.contains("protected"))
                        || line.contains("void") || line.contains("int") || line.contains("String"))
                        && line.contains("(") && line.contains(")") && line.contains("{"))
                    methodCounter++;
            }
            return methodCounter;
        } else {
            throw new IllegalArgumentException("Operation aborted due to unknown file reader");
        }
    }

    @Override
    public int calculateNOC() throws IOException {
        List<String> sourceCodeList = fileReader.readFileIntoList(filepath);
        if (sourceCodeList != null) {
            int classCounter = 0;
            for (String line : sourceCodeList) {
                line = line.trim();
                if ((line.startsWith("class ") || line.contains(" class ")) && line.contains("{")) {
                    classCounter++;
                }
            }
            return classCounter;
        } else {
            throw new IllegalArgumentException("Operation aborted due to unknown file reader");
        }
    }

}

/**
 * The NullAnalyzer class that implements the metrics methods for the unknown
 * analysis types.
 */
class NullAnalyzer extends Analyzer {

    public NullAnalyzer(String filepath, String fileReaderType) {
        super(filepath, fileReaderType);
    }

    @Override
    public int calculateLOC() throws IOException {
        return -1;
    }

    @Override
    public int calculateNOM() throws IOException {
        return -1;
    }

    @Override
    public int calculateNOC() throws IOException {
        return -1;
    }
}