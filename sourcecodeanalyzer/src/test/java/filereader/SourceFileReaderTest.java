package filereader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class SourceFileReaderTest {

	SourceFileReader sfrlocal = new LocalFile();
	SourceFileReader sfrweb = new WebFile();
	SourceFileReader sfrnull = new NullFile();
	private static List<String> expectedList;
	private static String expectedString;
	private final static String TEST_CLASS_LOCAL = "src/test/resources/TestClass.java";
	private final static String TEST_CLASS_WEB = "https://drive.google.com/uc?export=download&id=1z51FZXqPyun4oeB7ERFlOgfcoDfLLLhg";

	@BeforeClass
	public static void setUp() throws IOException {
		expectedList = Files.readAllLines(new File(TEST_CLASS_LOCAL).toPath(), Charset.defaultCharset());
		expectedString = String.join("\n", expectedList) + "\n"; // transforms a list into a String (with 'new line' as
																	// delimiter)
	}

	@Test
	public void testReadFileIntoListLocal() throws IOException {
		// read a locally stored file into a List
		List<String> actualList = sfrlocal.readFileIntoList(TEST_CLASS_LOCAL);

		String[] expecteds = expectedList.stream().toArray(String[]::new);
		String[] actuals = actualList.stream().toArray(String[]::new);

		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testReadFileIntoStringLocal() throws IOException {
		// read a locally stored file into a String
		String actuals = sfrlocal.readFileIntoString(TEST_CLASS_LOCAL);

		assertEquals(expectedString, actuals);
	}

	@Test
	public void testReadFileIntoListWeb() throws IOException {
		// read a file stored in the web into a List
		List<String> actualList = sfrweb.readFileIntoList(TEST_CLASS_WEB);

		String[] expecteds = expectedList.stream().toArray(String[]::new);
		String[] actuals = actualList.stream().toArray(String[]::new);

		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testReadFileIntoStringWeb() throws IOException {
		// read a file stored in the web into a String
		String actuals = sfrweb.readFileIntoString(TEST_CLASS_WEB);

		assertEquals(expectedString, actuals);
	}

	@Test
	public void testReadFileIntoListNull() throws IOException {
		// give a none existing type to cause a null List return
		List<String> actualList = sfrnull.readFileIntoList("any-filepath");

		assertNull(actualList);
	}

	@Test
	public void testReadFileIntoStringNull() throws IOException {
		// give a none existing type to cause a null String return
		String actualString = sfrnull.readFileIntoString("any-filepath");

		assertNull(actualString);
	}
}