package math;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import math.ArithmeticOperations;

/**
 * A test class that provides test cases for the methods of the
 * ArithmeticOperations class.
 * 
 * @author Nikolas Moatsos
 */
public class ArithmeticOperationsTest {
	ArithmeticOperations ao = new ArithmeticOperations();

	/*
	 * A unit that checks the division of two positive Integer values.
	 */
	@Test
	public void testDivideWithTwoPositiveIntegerValues() {
		Assert.assertEquals(2, ao.divide(4, 2), 0);
	}

	/*
	 * A unit that checks the division of two positive Double values.
	 */
	@Test
	public void testDivideWithTwoPositiveDoubleValues() {
		Assert.assertEquals(2.5, ao.divide(10.5, 4.2), 0);
	}

	/*
	 * A unit that checks the division of one positive and one negative Integer
	 * value.
	 */
	@Test
	public void testDivideWithOnePositiveAndOneNegativeIntegerValue() {
		Assert.assertEquals(-5, ao.divide(20, -4), 0);
	}

	/*
	 * A unit that checks the division of one negative Integer Value and one
	 * positive Double value.
	 */
	@Test
	public void testDivideWithOneNegativeAndOnePositiveIntegerAndDoubleValue() {
		Assert.assertEquals(-5.6, ao.divide(-42, 7.5), 0);
	}

	/*
	 * A unit that checks the division of two negative Double values.
	 */
	@Test
	public void testDivideWithTwoNegativeDoubleValues() {
		Assert.assertEquals(5.8, ao.divide(-14.5, -2.5), 0);
	}

	/*
	 * A unit that checks the exception caused when the division has 0 as
	 * denominator.
	 */
	@Test(expected = ArithmeticException.class)
	public void testDivideWithZeroDemonitator() {
		ao.divide(5, 0);
	}

	/*
	 * A unit that checks the division with 0 as numerator.
	 */
	@Test
	public void testDivideWithZeroNumerator() {
		Assert.assertEquals(0, ao.divide(0, -2), 0);
	}

	/*
	 * A unit that checks the multiplication of two positive values.
	 */
	@Test
	public void testMultiplyWithPositiveValues() {
		Assert.assertEquals(48, ao.multiply(6, 8));
	}

	/*
	 * A unit that checks the multiplication of 0 and one positive value.
	 */
	@Test
	public void testMultiplyWithZeroAndPositiveValue() {
		Assert.assertEquals(0, ao.multiply(0, 12));
	}

	/*
	 * A unit that checks the multiplication of one positive value and 0.
	 */
	@Test
	public void testMultiplyWithPositiveAndZeroValue() {
		Assert.assertEquals(0, ao.multiply(6, 0));
	}

	/*
	 * Test cases with the use of a rule.
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/*
	 * A unit that checks the exception caused when the input values are negative.
	 */
	@Test
	public void testMultiplyWithNegativeValues() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("x & y should be >= 0");
		ao.multiply(-7, -5);
	}

	/*
	 * A unit that checks the exception caused when first value is negative.
	 */
	@Test
	public void testMultiplyFirstNegativeValue() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("x & y should be >= 0");
		ao.multiply(-1, 4);
	}

	/*
	 * A unit that checks the exception caused when second value is negative.
	 */
	@Test
	public void testMultiplySecondNegativeValue() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("x & y should be >= 0");
		ao.multiply(9, -20);
	}

	/*
	 * A unit that checks the exception caused when the product of the
	 * multiplication does not fit an Integer variable.
	 */
	@Test
	public void testMultiplyWithNotFitingValue() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("The product does not fit in an Integer variable");
		ao.multiply(Integer.MAX_VALUE / 2 + 1, 2);
	}
}
