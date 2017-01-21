package scmallorca.kata.stringcalculator;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringCalculator {

    public static int add(final String input) throws NegativeNumberNotSupportedException {
        final InputExpression inputExpression = new InputExpression(input);
        if (inputExpression.isEmpty()) {
            return 0;
        }
        final List<Integer> numbers = inputExpression.getNumbers();
        if (numbers.stream().anyMatch(isNegative())) {
            throw NegativeNumberNotSupportedException.create(getNegativeFrom(numbers));
        }
        return numbers.stream().filter(number -> number <= 1000).mapToInt(Integer::intValue).sum();
    }

    private static List<Integer> getNegativeFrom(final List<Integer> numbers) {
        return numbers.stream().filter(isNegative()).collect(Collectors.toList());
    }

    private static Predicate<Integer> isNegative() {
        return number -> number < 0;
    }

}
