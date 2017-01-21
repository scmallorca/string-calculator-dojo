package scmallorca.kata.stringcalculator;

import java.util.List;
import java.util.stream.Collectors;

public class NegativeNumberNotSupportedException extends Exception {

    public NegativeNumberNotSupportedException(final String message) {
        super(message);
    }

    public static NegativeNumberNotSupportedException create(final List<Integer> numbers) {
        final String numbersMessage = numbers.stream().map(number -> number.toString()).collect(Collectors.joining(", "));
        return new NegativeNumberNotSupportedException("negativos no soportados: " + numbersMessage);
    }
}
