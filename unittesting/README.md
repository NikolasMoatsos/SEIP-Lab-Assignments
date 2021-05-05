# Unit Testing - Assignment

This is a Java assignment serving as a brief demonstration for Unit Testing within the Software Engineering in Practice course. It makes use of Maven to automate the build process.

## Java classes
- **ArithmeticOperations**: A class that provides the arithmetic operations of division and multiplication.
- **MyMath**: A class that provides the arithmetic operation of the factorial and a method to calculate if a number is prime.
- **FileIO**: A class that provides a method which returns an array with only the Integer numbers from a given file.
- **ArrayOperations**: A class that provides a method which returns an array with only the Integer prime numbers from a given file (uses MyMath and FileIO).

## Java Test Classes
- **ArithmeticOperationsTest**: Test cases for the ArithmeticOperations class.
- **MyMathFactorialParameterizedTest**: Parameterized test cases for the factorial method of the MyMath class.
- **MyMathIsPrimeParameterizedTest**: Parameterized test cases for the isPrime method of the MyMath class.
- **MyMathTest**: Test cases for the exceptions of the MyMath class.
- **MyMathTestSuite**: A test suite to run the test cases of the MyMathParameterizedTest and MyMathTest classes.
- **FileIOTest**: Test cases for the FileIO class.
- **ArrayOperationsTest**: Test case for the ArrayOperatios class, with the use of Mockito to mock MyMath and FileIO classes.

## Technologies used
- Git, Github
- Apache Maven 3.8.1
- Java 1.8
- Eclipse IDE
- JUnit version 4.12
- JaCoCo
- Mockito
- Codecov 

## Build the project
In order to build the project you must first make sure you have installed properly Git, Java and Maven. Then,

1. Clone this repository, with the command: <code>git clone https://github.com/NikolasMoatsos/SEIP-Lab-Assignments.git</code>.
2. Enter to your local copy of this repository, with the command: <code>cd SEIP-Lab-Assignments</code>.

### To build the project:

- Execute the command: <code>mvn clean install jacoco:report</code>. 

### To simply run the unit tests:

- Execute the command: <code>mvn test</code>.

### To generate Test-Coverage report:

- Execute the command: <code>mvn test jacoco:report</code>.

*The report of code coverage generated from JaCoCo is located in the following path:*  `unittesting/target/site/jacoco`.

In order to generate the cobertura coverage report include the `cobertura:cobertura` in your `mvn` command and in order to see the analysis in the Codecov website run succesfully a TravisCI build.
