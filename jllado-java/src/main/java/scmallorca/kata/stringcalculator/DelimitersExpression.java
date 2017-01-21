package scmallorca.kata.stringcalculator;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DelimitersExpression {

    public static final String START_DELIMITER_CONTAINER = "[";
    public static final String END_DELIMITER_CONTAINER = "]";
    private String expression;

    public DelimitersExpression(final String expression) {
        this.expression = expression;
    }

    public List<String> getSeparators() {
        if ("".equals(expression)) {
            return Collections.emptyList();
        }
        return Stream.of(expression.split(Pattern.quote(END_DELIMITER_CONTAINER + START_DELIMITER_CONTAINER)))
                .map(separator -> removeSeparatorContainer(separator))
                .collect(Collectors.toList());
    }

    private String removeSeparatorContainer(final String separator) {
        return separator.replace(START_DELIMITER_CONTAINER, "").replace(END_DELIMITER_CONTAINER, "");
    }

}
