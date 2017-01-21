package scmallorca.kata.stringcalculator;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumbersExpression {

    private String expression;

    public NumbersExpression(final String expression) {
        this.expression = expression;
    }

    public List<Integer> getNumbers(final List<String> separators) {
        return getStringNumbers(separators).stream().map(number -> Integer.valueOf(number)).collect(Collectors.toList());
    }

    private List<String> getStringNumbers(final List<String> delimiters) {
        final String regex = delimiters.stream().map(delimiter -> Pattern.quote(delimiter)).collect(Collectors.joining("|"));
        return Stream.of(expression.split(regex)).collect(Collectors.toList());
    }

}
