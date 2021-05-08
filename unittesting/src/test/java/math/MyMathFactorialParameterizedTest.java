package math;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import math.MyMath;

/**
 * A test class that implements a Parameterized test for the factorial method of
 * MyMath class.
 * 
 * @author Nikolas Moatsos
 */
@RunWith(Parameterized.class)
public class MyMathFactorialParameterizedTest {

	// the value is the id of each parameter
	@Parameter(value = 0)
	public int number;
	@Parameter(value = 1)
	public int factorial;

	MyMath mm = new MyMath();

	/*
	 * The following method generates the input values for the tests.
	 */
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 0, 1 }, { 1, 1 }, { 2, 2 }, { 12, 479001600 } };

		return Arrays.asList(data);
	}

	/*
	 * A unit test that is executed for each pair of parameters.
	 */
	@Test
	public void testFactorialWithNormalCases() {
		Assert.assertEquals(factorial, mm.factorial(number));
	}
}
