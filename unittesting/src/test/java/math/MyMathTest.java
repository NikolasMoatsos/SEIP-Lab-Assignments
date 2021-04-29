package math;

import org.junit.Assert;
import org.junit.Test;
import math.MyMath;

/**
 * A test class that provides test cases for the exceptions of the factorial
 * method and all the test cases for the isPrime method of the MyMath class.
 * 
 * @author Nikolas Moatsos
 */
public class MyMathTest {
	MyMath mm = new MyMath();

	/*
	 * A unit that checks the exception caused in the factorial method when the
	 * number is smaller than 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFactorialWithLowerBoundNumber() {
		mm.factorial(-1);
	}

	/*
	 * A unit that checks the exception caused in the factorial method when the
	 * number is greater than 12.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFactorialWithUpperBoundNumber() {
		mm.factorial(13);
	}

	/*
	 * A unit that checks the isPrime method with a prime number.
	 */
	@Test
	public void testIsPrimeWithPrimeNumber() {
		Assert.assertEquals(true, mm.isPrime(7));
	}

	/*
	 * A unit that checks the isPrime method with a not prime number.
	 */
	@Test
	public void testIsPrimeWithNotPrimeNumber() {
		Assert.assertEquals(false, mm.isPrime(42));
	}

	/*
	 * A unit that checks the isPrime method with the border number.
	 */
	@Test
	public void testIsPrimeWithBorderNumber() {
		Assert.assertEquals(true, mm.isPrime(2));
	}

	/*
	 * A unit that checks the exception caused in the isPrime method when the number
	 * is smaller than 2.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIsPrimeWithInvalidInputNumber() {
		mm.isPrime(1);
	}
}
