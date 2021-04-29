package math;

/**
 * The MyMath provides the factorial operation for the Integer numbers between
 * the range [0,12] and a method to calculate if an Integer number greater than
 * 1 is prime.
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
	 * @exception IllegalArgumentException when the input number is out of the range
	 *                                     [0,12].
	 */
	public int factorial(int n) {
		// If the n is not in the range [0,12], throw the exception.
		if (n < 0 || n > 12) {
			throw new IllegalArgumentException("Input should be an Integer greater than -1 and smaller than 13.");
		}
		// Otherwise, calculate the factorial of n.
		int fact = 1;
		for (int i = 1; i <= n; i++) {
			fact *= i;
		}

		return fact;
	}

	/**
	 * Calculates if a number is prime or not.
	 * 
	 * @param n the number to be calculated.
	 * @return true if the number is prime and false otherwise.
	 * @exception IllegalArgumentException when the input number is smaller than 2.
	 */
	public boolean isPrime(int n) {
		// If the number is smaller than 2, throw the exception.
		if (n < 2) {
			throw new IllegalArgumentException("Input should be an Integer greater than 1");
		}
		// Otherwise, calculate if the number is prime.
		boolean isPrime = true;
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}

}
