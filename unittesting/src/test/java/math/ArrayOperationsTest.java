package math;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import io.FileIO;

/**
 * A test class that provides a test case for the findPrimesInfile method of the
 * ArrayOperations class, by using Mockito to mock classes MyMath and FileIo .
 * 
 * @author Nikolas Moatsos
 */
public class ArrayOperationsTest {

	/*
	 * A unit that checks the findPrimesInFile method with the use of mocking.
	 */
	@Test
	public void testFindNamesInFileThatStartWith() {
		FileIO fileio = mock(FileIO.class);
		MyMath mm = mock(MyMath.class);

		String path = "src/test/resources/ArrayOperations_test.txt";
		when(fileio.readFile(path)).thenReturn(new int[] { 2, 0, 99, 7, 32, 100, 97, 67, -50, 19, 65 });

		when(mm.isPrime(2)).thenReturn(true);
		when(mm.isPrime(99)).thenReturn(false);
		when(mm.isPrime(7)).thenReturn(true);
		when(mm.isPrime(32)).thenReturn(false);
		when(mm.isPrime(100)).thenReturn(false);
		when(mm.isPrime(97)).thenReturn(true);
		when(mm.isPrime(67)).thenReturn(true);
		when(mm.isPrime(19)).thenReturn(true);
		when(mm.isPrime(65)).thenReturn(false);

		ArrayOperations ao = new ArrayOperations();
		int[] expected = { 2, 7, 97, 67, 19 };
		int[] actual = ao.findPrimesInFile(fileio, path, mm);

		assertArrayEquals(expected, actual);
	}
}
