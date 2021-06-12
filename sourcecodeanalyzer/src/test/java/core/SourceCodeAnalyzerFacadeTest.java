package core;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Assert;
import org.junit.Rule;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SourceCodeAnalyzerFacadeTest {

    SourceCodeAnalyzerFacade scaf = new SourceCodeAnalyzerFacade();
    private final static String TEST_CLASS = "src/test/resources/TestClass.java";
    private final static String READER_TYPE = "local";
    private final static String ANALYZER_TYPE = "regex";
    private final static String OUTPUT_FILEPATH = "src/test/resources/test_output_metrics";
	private final static String OUTPUT_FILE_TYPE = "csv";
    Map<String, Integer> metrics = new HashMap<>();

    @Rule
	public ExpectedException thrown = ExpectedException.none();
    
    @Test 
    public void testCreateMetricsValid() throws IOException {
        metrics.put("loc", 21);
		metrics.put("nom", 3);
		metrics.put("noc", 3);

        assertEquals(metrics, scaf.createMetrics(ANALYZER_TYPE, TEST_CLASS, READER_TYPE));
    }

    @Test
    public void testCreateMetricsWrongAnalyzerTypeException() throws IOException {
        thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Operation aborted due to unknown analyzer type");
        
        scaf.createMetrics("wrong", TEST_CLASS, READER_TYPE);
    }

    @Test
    public void testCreateMetricsWrongFileReaderException() throws IOException {
        thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Operation aborted due to unknown file reader");
        
        scaf.createMetrics(ANALYZER_TYPE, TEST_CLASS, "wrong");
    }

    @Test 
    public void testExportMetricsValid() throws IOException {
        metrics.put("loc", 30);
		metrics.put("nom", 5);
		metrics.put("noc", 2);
		
		// generate and write the output file
		scaf.exportMetrics(metrics, OUTPUT_FILE_TYPE, OUTPUT_FILEPATH);
		
		// evaluate that the file exists
		File outputFile = new File(OUTPUT_FILEPATH + ".csv");
		Assert.assertTrue(outputFile.exists());
		
		// delete the generated file
		outputFile.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExportMetricsWrongFileTypeException() throws IOException {   
        metrics.put("loc", 30);
		metrics.put("nom", 5);
		metrics.put("noc", 2);
		
		scaf.exportMetrics(metrics, "wrong", OUTPUT_FILEPATH);
    }

    @Test 
    public void testExecuteAnalysisValid() throws IOException {
		
        metrics.put("loc", 21);
		metrics.put("nom", 3);
		metrics.put("noc", 3);
        
        scaf.executeAnalysis(ANALYZER_TYPE, TEST_CLASS, READER_TYPE, OUTPUT_FILE_TYPE, OUTPUT_FILEPATH);
        
        BufferedReader csvReader = new BufferedReader(new FileReader(OUTPUT_FILEPATH + ".csv"));
        String row;
        int c = 0;
        Map<String, Integer> actual_metrics = new HashMap<>();
        while ((row = csvReader.readLine()) != null) {
            c++;
            if(c == 2) {
                String[] data = row.split(",");
                actual_metrics.put("loc", Integer.parseInt(data[0]));
		        actual_metrics.put("nom", Integer.parseInt(data[1]));
		        actual_metrics.put("noc", Integer.parseInt(data[2]));
            }
        }
        csvReader.close();

        assertEquals(metrics, actual_metrics);
		
		// delete the generated file
        File outputFile = new File(OUTPUT_FILEPATH + ".csv");
		outputFile.delete();
    }

    @Test
    public void testExecuteAnalysisWrongAnalyzerTypeException() throws IOException {   
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Operation aborted due to unknown analyzer type");

		scaf.executeAnalysis("wrong", TEST_CLASS, READER_TYPE, OUTPUT_FILE_TYPE, OUTPUT_FILEPATH);
    }

    @Test
    public void testExecuteAnalysisWrongFileReaderException() throws IOException {   
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Operation aborted due to unknown file reader");

		scaf.executeAnalysis(ANALYZER_TYPE, TEST_CLASS, "wrong", OUTPUT_FILE_TYPE, OUTPUT_FILEPATH);
    }

    @Test
    public void testExecuteAnalysisWrongOutputFileTypeException() throws IOException {   
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Operation aborted due to unknown file writer");

		scaf.executeAnalysis(ANALYZER_TYPE, TEST_CLASS, READER_TYPE, "wrong", OUTPUT_FILEPATH);
    }
}
