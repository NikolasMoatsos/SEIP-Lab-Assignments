package math;

/**
 * The MyMath provides the factorial operation for the numbers between the range
 * [0,12].
 * 
 * @author Nikolas Moatsos
 * @since April 2021
 */
public class MyMath {

	/**
	 * Performs the arithmetic operation of the factorial.
	 * 
	 * @param n the number of the factorial.
	 * @return the value of the factorial.
	 */
	public int factorial(int n) {
		// if the n is not in the range [0,12], throw the exception.
		if (n < 0 || n > 12) {
			throw new IllegalArgumentException("Input should be an Integer greater than 0 and smaller than 13.");
		}
		// Otherwise, calculate the factorial of n.
		int fact = 1;
		for (int i = 1; i <= n; i++) {
			fact *= i;
		}

		return fact;
	}
}
