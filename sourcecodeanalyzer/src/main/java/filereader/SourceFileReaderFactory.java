package filereader;

/**
 * The SourceFileReaderFactory class, which is responsible to return the correct
 * SourceFileReader Type.
 *
 * @author NikolasMoatsos
 * @version 1.0
 * @since 2021-06-13
 */
public class SourceFileReaderFactory {

    /**
     * The method which returns the correct SourceFileReader Type.
     * 
     * @param fileReaderType the location of the file
     */
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