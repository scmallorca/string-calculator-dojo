import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class StringCalculatorTest {

    StringCalculator stringCalculator;

    @Before
    public void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void emptyStringShouldBeZero() {
        assertSum(0,"");
    }

    @Test
    public void oneShouldBeOne() {
        assertSum(1,"1");
    }

    @Test
    public void twoShouldBeTwo() {
        assertSum(2,"2");
    }

    @Test
    public void anyNumberShouldBeThatNumber() {
        int number = 34;
        assertSum(number, String.valueOf(number));
    }

    @Test
    public void oneParamIsNotNumberShouldBeZero() {
        assertSum(0,"cxcx");
    }

    @Test
    public void oneAndTwoShouldBeThree() {
        assertSum(3,"1,2");
    }

    @Test
    public void anyTwoNumbersShouldBeTheSum() {
        int firstNumber = ThreadLocalRandom.current().nextInt(0, 50);
        int secondNumber = ThreadLocalRandom.current().nextInt(0, 50);
        assertSum(firstNumber + secondNumber, String.valueOf(firstNumber) + "," +
                String.valueOf(secondNumber));
    }

    @Test
    public void oneAndTwoAndThreeShouldBeSix() {
        assertSum(6, "1,2,3");
    }

    @Test
    public void anyNumberOfNumbersShouldBeTheSum() {
        assertSum(45, "20,11,9,5");
    }

    @Test
    public void twoParamsAndTheFirstIsNotANumberShouldBeZero() {
        assertSum(0, "x,3");
    }

    @Test
    public void oneAndTwoWithSpecificDelimiterShouldBeThree() {
        assertSum(3, "1\n2");
    }

    @Test
    public void oneAndTwoAndThreeWithTwoDifferentDelimitersShouldBeSix() {
        assertSum(6, "1\n2,3");
    }

    @Test(expected = NegativeNumbersException.class)
    public void negativeNumberShouldThrowNegativeNumbersException() {
        stringCalculator.add("2,-1");
    }

    @Test
    public void negativeNumberShouldThrowNegativeNumbersExceptionWithThatNumber() {
        try {
            stringCalculator.add("2,-1");
            Assert.assertTrue(false);
        } catch (NegativeNumbersException exception) {
            Assert.assertEquals("Negative numbers -1", exception.toString());
        }
    }

    @Test
    public void twoNegativeNumbersShouldThrowNegativeNumbersExceptionWithBothNumbers() {
        try {
            stringCalculator.add("-1,-2");
            Assert.assertTrue(false);
        } catch (NegativeNumbersException exception) {
            Assert.assertEquals("Negative numbers -1, -2", exception.toString());
        }
    }

    @Test
    public void addNewDelimiterShouldSum() {
        assertSum(3, "//;\n1;2");
    }

    @Test
    public void addNewDelimiterAndUseItWithOthersDelimitersShouldSum() {
        assertSum(11, "//b8ubu\n1b8ubu2\n2,6");
    }

    @Test
    public void numGreaterThanOneThousandShouldBeZero() {
        assertSum(0, "1001");
    }

    @Test
    public void addOneDelimiterWIthBracketDelimiterFormatShouldSum() {
        assertSum(15, "//[***]\n1***2***12");
    }

    @Test
    public void addTwoDelimitersWithBracketDelimiterFormatShouldSum() {
        assertSum(6, "//[*)][%]\n1*)2%3");
    }

    @Test
    public void badFormatShouldBeZero() {
        assertSum(0, "//[*)][%]ds\n1*)2%3");
    }

    private void assertSum(int expected, String numbers) {
        Assert.assertEquals(expected, stringCalculator.add(numbers));
    }

}
