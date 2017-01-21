package scmallorca.kata.stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputExpression {

    public static final List<String> DEFAULT_DELIMITERS = Arrays.asList(",", "\n");

    public static final String START_DELIMITERS_EXPRESSION = "//";
    public static final String END_DELIMITERS_EXPRESSION = "\n";

    private final String expression;

    public InputExpression(final String expression) {
        this.expression = expression;
    }

    public boolean isEmpty() {
        return "".equals(expression);
    }

    private DelimitersExpression getDelimitersExpression() {
        if (!hasCustomSeparator()) {
            return new DelimitersExpression("");
        }
        return new DelimitersExpression(expression.substring(getBeginDelimitersIndex(), getEndDelimitersIndex()));
    }

    private boolean hasCustomSeparator() {
        return expression.startsWith(START_DELIMITERS_EXPRESSION);
    }

    private int getEndDelimitersIndex() {
        return expression.indexOf(END_DELIMITERS_EXPRESSION);
    }

    private int getBeginDelimitersIndex() {
        return expression.indexOf(START_DELIMITERS_EXPRESSION) + START_DELIMITERS_EXPRESSION.length();
    }

    private NumbersExpression getNumberExpression() {
        if (!hasCustomSeparator()) {
            return new NumbersExpression(expression);
        }
        return new NumbersExpression(expression.substring(getEndDelimitersIndex() + 1));
    }

    public List<Integer> getNumbers() {
        return getNumberExpression().getNumbers(getDelimiters());
    }

    private List<String> getDelimiters() {
        final List<String> separators = new ArrayList<>();
        separators.addAll(DEFAULT_DELIMITERS);
        separators.addAll(getDelimitersExpression().getSeparators());
        return separators;
    }

}
