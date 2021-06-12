package filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public interface SourceFileReader {
    public List<String> readFileIntoList(String filepath) throws IOException;
    public String readFileIntoString(String filepath) throws IOException;
}

class LocalFile implements SourceFileReader {

    @Override
    public List<String>  readFileIntoList(String filepath) throws IOException {
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

class WebFile implements SourceFileReader {

	@Override
    public List<String>  readFileIntoList(String filepath) throws IOException {
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

class NullFile implements SourceFileReader {

	@Override
    public List<String>  readFileIntoList(String filepath) throws IOException {
		return null;
	}

	@Override
    public String readFileIntoString(String filepath) throws IOException {
		return null;
	}
}