package codeanalyzer;

public class SourceFileReaderFactory {
    
    public SourceFileReader createSourceFileReader(String fileReaderType) {
        SourceFileReader fileReader;
        if (fileReaderType.equals("local")) {
           fileReader = new LocalFile();
        } else if (fileReaderType.equals("web")) {
            fileReader = new WebFile();
        } else {
            fileReader = new NullFile();
        }

        return fileReader;
    }

}