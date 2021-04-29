package math;

import java.util.ArrayList;
import java.util.List;
import io.FileIO;

/**
 * The ArrayOperations provides a method that reads the Integer numbers from a
 * file and adds only the primes to an array.
 * 
 * @author Nikolas Moatsos
 * @since April 2021
 */
public class ArrayOperations {

	/**
	 * Creates an array with only prime Integer numbers, from a given file.
	 * 
	 * @param fileIo   the object that provides the method to read the Integer
	 *                 numbers from the file.
	 * @param filepath the path of the file.
	 * @param myMath   the object that provides the method to calculate if a number
	 *                 is prime.
	 * @return an array with prime Integer numbers.
	 */
	int[] findPrimesInFile(FileIO fileIo, String filepath, MyMath myMath) {

		int[] numbers = null;

		// Read the Integer numbers from the file.
		try {
			numbers = fileIo.readFile(filepath);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		List<Integer> primeNumbers = new ArrayList<>();

		// Add only the prime numbers to the ArrayList.
		for (int num : numbers) {
			try {
				if (myMath.isPrime(num)) {
					primeNumbers.add(num);
				}
			} catch (IllegalArgumentException e) {
				// do nothing
			}
		}

		// Convert the ArrayList to an Array.
		return primeNumbers.stream().mapToInt(i -> i).toArray();
	}
}
