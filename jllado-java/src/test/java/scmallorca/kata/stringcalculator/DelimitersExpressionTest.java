package scmallorca.kata.stringcalculator;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

public class DelimitersExpressionTest {

    @Test
    public void should_find_separator() throws Exception {
        assertThat(new DelimitersExpression(";").getSeparators(), is(Arrays.asList(";")));
    }

    @Test
    public void should_find_separator_given_separator_of_any_length() throws Exception {
        assertThat(new DelimitersExpression("[##]").getSeparators(), is(Arrays.asList("##")));
    }

    @Test
    public void should_find_separators_given_multiple_separators() throws Exception {
        assertThat(new DelimitersExpression("[;][#]").getSeparators(), is(Arrays.asList(";", "#")));
    }

    @Test
    public void should_return_empty_list_separators_given_empty_expersion() throws Exception {
        assertThat(new DelimitersExpression("").getSeparators(), hasSize(0));
    }

}
