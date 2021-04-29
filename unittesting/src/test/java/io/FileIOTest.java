package io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * A test class that provides test cases for the readFile method of the FileIO class.
 * 
 * @author Nikolas Moatsos
 */
public class FileIOTest {

	FileIO fileio = new FileIO();
	public static String resourcesPath = "src/test/resources/";

	/*
	 * A unit that checks the readFile method with valid numbers as input.
	 */
	@Test
	public void testReadFileValidInput() {
		int[] expectedNumbers = new int[] { 1520, 2, 31, -1, 10, 4, 0, 52, -123, 99, 6, -63 };
		String validInputFilepath = resourcesPath.concat("numbers_valid.txt");

		Assert.assertArrayEquals(expectedNumbers, fileio.readFile(validInputFilepath));
	}

	/*
	 * Test cases with the use of a rule.
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/*
	 * A unit that checks the exception caused in the readFile method when the input
	 * file name is invalid.
	 */
	@Test
	public void testReadFileInvalidName() {
		String invalidNameFilepath = resourcesPath.concat("invalid_name.txt");
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Input file does not exist");
		fileio.readFile(invalidNameFilepath);
	}

	/*
	 * A unit that checks the exception caused in the readFile method when the input
	 * file is empty.
	 */
	@Test
	public void testReadFileEmptyFile() {
		String emptyFilepath = resourcesPath.concat("empty_file.txt");
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Given file is empty");
		fileio.readFile(emptyFilepath);
	}

	/*
	 * A unit that checks the ReadFile method with valid and invalid entries in the
	 * input file.
	 */
	@Test
	public void testReadFileContainsInvalidEntries() {
		int[] expectedNumbers = new int[] { -20, 2, 1234, 89, 0, 574 };
		String invalidEntriesFilepath = resourcesPath.concat("invalid_entries.txt");

		Assert.assertArrayEquals(expectedNumbers, fileio.readFile(invalidEntriesFilepath));
	}

}
