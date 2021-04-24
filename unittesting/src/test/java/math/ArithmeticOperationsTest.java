package math;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import math.ArithmeticOperations;

public class ArithmeticOperationsTest {
	
	ArithmeticOperations ao = new ArithmeticOperations();

	@Test
	public void testDivideWithTwoPositiveIntegerValues() {
		Assert.assertEquals(2, ao.divide(4, 2), 0);
	}

	@Test
	public void testDivideWithTwoPositiveDoubleValues() {
		Assert.assertEquals(2.5, ao.divide(10.5, 4.2), 0);
	}

	@Test
	public void testDivideWithOneNegativeAndOnePositiveIntegerValue() {
		Assert.assertEquals(-5, ao.divide(20, -4), 0);
	}

	@Test
	public void testDivideWithTwoNegativeDoubleValues() {
		Assert.assertEquals(5.8, ao.divide(-14.5, -2.5), 0);
	}

	@Test(expected = ArithmeticException.class)
	public void testDivideWithZeroDemonitator() {
		ao.divide(5, 0);
	}

	@Test
	public void testDivideWithZeroNumerator() {
		Assert.assertEquals(0, ao.divide(0, -2), 0);
	}

	@Test
	public void testMultiplyWithPositiveValues() {
		Assert.assertEquals(48, ao.multiply(6, 8));
	}
	
	@Test
	public void testMultiplyWithZeroAndPositiveValue() {
		Assert.assertEquals(0, ao.multiply(0, 12));
	}
	
	@Test
	public void testMultiplyWithPositiveAndZeroValue() {
		Assert.assertEquals(0, ao.multiply(6, 0));
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testMultiplyWithNegativeValues() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("x & y should be >= 0");
		ao.multiply(-7, -5);
	}

	@Test
	public void testMultiplyWithNotFitingValue() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("The product does not fit in an Integer variable");
		ao.multiply(Integer.MAX_VALUE / 2 + 1, 2);
	}
}
