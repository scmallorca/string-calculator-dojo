package scmallorca.kata.stringcalculator;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class NumbersExpressionTest {

    @Test
    public void should_return_number_given_separator() throws Exception {
        assertThat(new NumbersExpression("2;1").getNumbers(Arrays.asList(";")), is(Arrays.asList(2, 1)));
    }

    @Test
    public void should_return_number_given_multiple_separator() throws Exception {
        assertThat(new NumbersExpression("2;1").getNumbers(Arrays.asList(";", "#")), is(Arrays.asList(2, 1)));
        assertThat(new NumbersExpression("2;1#2").getNumbers(Arrays.asList(";", "#")), is(Arrays.asList(2, 1, 2)));
        assertThat(new NumbersExpression("2;1#2;3").getNumbers(Arrays.asList(";", "#")), is(Arrays.asList(2, 1, 2, 3)));
    }

}