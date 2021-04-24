package math;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import math.MyMath;

public class MyMathTest {
	MyMath mm = new MyMath();

	@Test(expected = IllegalArgumentException.class)
	public void testFactorialWithLowerBoundNumber() {
		mm.factorial(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFactorialWithUpperBoundNumber() {
		mm.factorial(13);
	}
}
