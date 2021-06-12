package codeanalyzer;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnalyzerTest {
	private final static String TEST_CLASS = "src/test/resources/TestClass.java";
    private final static String READER_TYPE = "local";
    private final static String WRONG_READER_TYPE = "wrong";
	private Analyzer ar = new Regex(TEST_CLASS, READER_TYPE);
    private Analyzer arwrong = new Regex(TEST_CLASS, WRONG_READER_TYPE);
    private Analyzer  as = new Strcomp(TEST_CLASS, READER_TYPE);
    private Analyzer  aswrong = new Strcomp(TEST_CLASS, WRONG_READER_TYPE);
    private Analyzer  an = new NullAnalyzer(TEST_CLASS, READER_TYPE);

    @Test
    public void testCalculateRegexLOC() throws IOException {
		assertEquals(21, ar.calculateLOC());
	}

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateWrongRegexLOC() throws IOException {
		arwrong.calculateLOC();
	}

    @Test
	public void testCalculateStrCompLOC() throws IOException {
		assertEquals(7, as.calculateLOC());
	}

    @Test(expected = IllegalArgumentException.class)
	public void testCalculateWrongStrCompLOC() throws IOException {
		aswrong.calculateLOC();
	}

    @Test
	public void testCalculateNullAnalyzerLOC() throws IOException {
		assertEquals(-1, an.calculateLOC());
	}

    @Test
	public void testCalculateRegexNOM() throws IOException {
		assertEquals(3, ar.calculateNOM());
	}

    @Test(expected = IllegalArgumentException.class)
	public void testCalculateWrongRegexNOM() throws IOException {
		arwrong.calculateNOM();
	}
	
	@Test
	public void testCalculateStrCompNOM() throws IOException {
		assertEquals(3, as.calculateNOM());
	}

    @Test(expected = IllegalArgumentException.class)
	public void testCalculateWrongStrCompNOM() throws IOException {
		aswrong.calculateNOM();
	}

    @Test
	public void testCalculateNullAnalyzerNOM() throws IOException {
		assertEquals(-1, an.calculateNOM());
	}
	
	@Test
	public void testCalculateRegexNOC() throws IOException {
		assertEquals(3, ar.calculateNOC());
	}

    @Test(expected = IllegalArgumentException.class)
	public void testCalculateWrongRegexNOC() throws IOException {
		arwrong.calculateNOC();
	}
	
	@Test
	public void testCalculateStrCompNOC() throws IOException {
		assertEquals(3, as.calculateNOC());
	}

    @Test(expected = IllegalArgumentException.class)
	public void testCalculateWrongStrCompNOC() throws IOException {
		aswrong.calculateNOC();
	}

    @Test
	public void testCalculateNullAnalyzerNOC() throws IOException {
		assertEquals(-1, an.calculateNOC());
	}
}