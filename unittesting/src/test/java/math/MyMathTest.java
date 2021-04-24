package math;

import org.junit.Test;
import math.MyMath;

/**
 * A test class that provides test cases for the exceptions of the factorial
 * methods of the MyMath class.
 * 
 * @author Nikolas Moatsos
 */
public class MyMathTest {
	MyMath mm = new MyMath();

	/*
	 * A unit that checks the exception caused when the number is smaller than 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFactorialWithLowerBoundNumber() {
		mm.factorial(-1);
	}

	/*
	 * A unit that checks the exception caused when the number is greater than 12.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFactorialWithUpperBoundNumber() {
		mm.factorial(13);
	}
}
