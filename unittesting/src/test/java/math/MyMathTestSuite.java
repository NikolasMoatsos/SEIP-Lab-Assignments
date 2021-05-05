package math;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * A class used as a holder of the @RunWith and @Suite annotations.
 * 
 * @author Nikolas Moatsos
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ MyMathTest.class, MyMathFactorialParameterizedTest.class, MyMathIsPrimeParameterizedTest.class })
public class MyMathTestSuite {
	/*
	 * This class remains empty. Is used only as a holder of the above annotations.
	 */
}
