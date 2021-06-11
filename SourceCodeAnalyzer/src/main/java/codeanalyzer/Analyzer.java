package codeanalyzer;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Analyzer {
    protected String filepath;
    protected SourceFileReader fileReader;

    public Analyzer(String filepath, String fileReaderType) {
        this.filepath = filepath;
        SourceFileReaderFactory sfrfactory = new SourceFileReaderFactory();
        this.fileReader = sfrfactory.createSourceFileReader(fileReaderType);
    }

    public abstract int calculateLOC() throws IOException;

    public abstract int calculateNOM() throws IOException;

    public abstract int calculateNOC() throws IOException;
}

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
	        int loc =  sourceFileLength - nonCodeLinesCounter;
	        
			return loc;
        } else {
            return -1;
        }
    }

    @Override
    public int calculateNOM() throws IOException {
        String sourceCode = fileReader.readFileIntoString(filepath);
		if (sourceCode != null) {
            Pattern pattern = Pattern.compile(".*(public |protected |private |static )?[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;]).*"); 
	        Matcher methodSignatures = pattern.matcher(sourceCode);

	        int methodCounter = 0;
	        while (methodSignatures.find()) {
	        	methodCounter++;
	        }
			return methodCounter;
        } else {
            return -1;
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
            return -1;
        }
    }
}

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
				line = line.trim(); //clear all leading and trailing white spaces
				if (line.startsWith("//") || line.startsWith("/*") || line.startsWith("*") || line.equals("{") || line.equals("}") || line.equals(""))
					nonCodeLinesCounter++;
			}
			int loc = sourceCodeList.size() - nonCodeLinesCounter;
			return loc;
        } else {
            return -1;
        }
    }

    @Override
    public int calculateNOM() throws IOException {
        List<String> sourceCodeList = fileReader.readFileIntoList(filepath);
        if (sourceCodeList != null) {
            int methodCounter = 0;
            for (String line : sourceCodeList) {
                line = line.trim(); //clear all leading and trailing white spaces
                if ( ((line.contains("public") || line.contains("private") || line.contains("protected"))
                        || line.contains("void") || line.contains("int") || line.contains("String"))
                    && line.contains("(") && line.contains(")") && line.contains("{") )
                    methodCounter++;
            }
            return methodCounter;
        } else {
            return -1;
        }
    }

    @Override
    public int calculateNOC() throws IOException {
        List<String> sourceCodeList = fileReader.readFileIntoList(filepath);
        if (sourceCodeList != null) {
            int classCounter = 0;
            for (String line : sourceCodeList) {
                line = line.trim(); //remove leading and trailing white spaces
                if ((line.startsWith("class ") || line.contains(" class ")) && line.contains("{")) {
                    classCounter++;
                }
            }
            return classCounter;
        } else {
            return -1;
        }
    }

}

class NullAnalyzer extends Analyzer {

    public NullAnalyzer(String filepath, String fileReaderType) {
        super(filepath, fileReaderType);
    }

    @Override
    public int calculateLOC() throws IOException {
       return -2;
    }

    @Override
    public int calculateNOM() throws IOException {
        return -2;
    }

    @Override
    public int calculateNOC() throws IOException {
       return -2;
    }
}