package metricsexporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * The MetricsExporter interface and the classes that implements it. It contains
 * the method that exports the metrics into a file.
 *
 * @author NikolasMoatsos
 * @version 1.0
 * @since 2021-06-13
 */
public interface MetricsExporter {
	/**
	 * Writes the metrics into a file.
	 * 
	 * @exception IOException when an IO interruption occurs
	 */
	public void writeFile(Map<String, Integer> metrics, String filepath);
}

/**
 * The CsvWriter class that implements the writeFile method for the csv files.
 */
class CsvWriter implements MetricsExporter {

	@Override
	public void writeFile(Map<String, Integer> metrics, String filepath) {
		File outputFile = new File(filepath + ".csv");
		StringBuilder metricsNames = new StringBuilder();
		StringBuilder metricsValues = new StringBuilder();

		for (Map.Entry<String, Integer> entry : metrics.entrySet()) {
			metricsNames.append(entry.getKey() + ",");
			metricsValues.append(entry.getValue() + ",");
		}

		try {
			FileWriter writer = new FileWriter(outputFile);
			writer.append(metricsNames + "\n");
			writer.append(metricsValues + "\n");
			writer.close();
			System.out.println("Metrics saved in " + outputFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * The JsonWriter class that implements the writeFile method for the json files.
 */
class JsonWriter implements MetricsExporter {

	@Override
	public void writeFile(Map<String, Integer> metrics, String filepath) {
		// Functionality not implemented yet
		// No need to implement it for the assignment
	}
}

/**
 * The NullWriter class that implements the writeFile method for the unknown
 * file types.
 */
class NullWriter implements MetricsExporter {

	@Override
	public void writeFile(Map<String, Integer> metrics, String filepath) {
		throw new IllegalArgumentException("Operation aborted due to unknown file writer");
	}
}