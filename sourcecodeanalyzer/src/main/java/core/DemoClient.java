package core;

import java.io.IOException;

/**
 * The client class that interprets the program's arguments and call the main
 * facade method.
 *
 * @author NikolasMoatsos
 * @version 1.0
 * @since 2021-06-13
 */
public class DemoClient {

	public static void main(String[] args) throws IOException {
		String filepath = "sourcecodeanalyzer/src/main/resources/TestClass.java";
		String sourceCodeAnalyzerType = "regex";
		String sourceFileLocation = "local";
		String outputFilePath = "output_metrics_file";
		String outputFileType = "csv";

		if (args.length == 5) {
			filepath = args[0];
			sourceCodeAnalyzerType = args[1];
			sourceFileLocation = args[2];
			outputFilePath = args[3];
			outputFileType = args[4];
		} else if (args.length != 0) {
			System.out.println("Incorrect number of arguments.");
			System.exit(1);
		}

		SourceCodeAnalyzerFacade scafacade = new SourceCodeAnalyzerFacade();
		scafacade.executeAnalysis(sourceCodeAnalyzerType, filepath, sourceFileLocation, outputFileType, outputFilePath);
	}

}
