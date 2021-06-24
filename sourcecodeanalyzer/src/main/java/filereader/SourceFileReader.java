package filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * The SourceFileReader interface and the classes that implements it. It
 * contains all the methods to read the file, regarding the Analyzer types.
 *
 * @author NikolasMoatsos
 * @version 1.0
 * @since 2021-06-13
 */
public interface SourceFileReader {
	/**
	 * Reads a file and returns it into a list.
	 * 
	 * @exception IOException when an IO interruption occurs
	 */
	public List<String> readFileIntoList(String filepath) throws IOException;

	/**
	 * Reads a file and returns it into a String.
	 * 
	 * @exception IOException when an IO interruption occurs
	 */
	public String readFileIntoString(String filepath) throws IOException;
}

/**
 * The LocalFile class that implements the file reader methods for the Analyzer
 * types.
 */
class LocalFile implements SourceFileReader {

	@Override
	public List<String> readFileIntoList(String filepath) throws IOException {
		List<String> lines = new ArrayList<>();
		File file = new File(filepath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		reader.close();
		return lines;
	}

	@Override
	public String readFileIntoString(String filepath) throws IOException {
		StringBuilder sb = new StringBuilder();
		File file = new File(filepath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		reader.close();
		return sb.toString();
	}
}

/**
 * The WebFile class that implements the file reader methods for the Analyzer
 * types.
 */
class WebFile implements SourceFileReader {

	@Override
	public List<String> readFileIntoList(String filepath) throws IOException {
		List<String> lines = new ArrayList<>();
		URL url = new URL(filepath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		reader.close();
		return lines;
	}

	@Override
	public String readFileIntoString(String filepath) throws IOException {
		StringBuilder sb = new StringBuilder();
		URL url = new URL(filepath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		reader.close();
		return sb.toString();
	}
}

/**
 * The NullFile class that implements the file reader methods for the unknown
 * file locations.
 */
class NullFile implements SourceFileReader {

	@Override
	public List<String> readFileIntoList(String filepath) throws IOException {
		return null;
	}

	@Override
	public String readFileIntoString(String filepath) throws IOException {
		return null;
	}
}