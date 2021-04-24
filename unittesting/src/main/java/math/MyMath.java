package math;

public class MyMath {

	public int factorial(int n) {
		if (n < 0 || n > 12) {
			throw new IllegalArgumentException("Input should be an Integer greater than 0 and smaller than 13.");
		}

		int fact = 1;
		for (int i = 1; i <= n; i++) {
			fact *= i;
		}

		return fact;
	}
}
