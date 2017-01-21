package scmallorca.kata.stringcalculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StringCalculatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void add_given_one_number_should_return_the_number() throws Exception {
        assertThat(add("1"), is(1));
        assertThat(add("2"), is(2));
    }

    @Test
    public void add_given_two_numbers_should_return_the_sum() throws Exception {
        assertThat(add("1,2"), is(3));
        assertThat(add("4,6"), is(10));
        assertThat(add("4,12"), is(16));
        assertThat(add("100,100"), is(200));
    }

    @Test
    public void add_given_empty_string_should_return_zero() throws Exception {
        assertThat(add(""), is(0));
    }

    @Test
    public void add_given_numbers_should_return_the_sum() throws Exception {
        assertThat(add("1,2,3"), is(6));
        assertThat(add("1,1,1,1"), is(4));
        assertThat(add("1,1,1,1,1"), is(5));
    }

    @Test
    public void add_given_numbers_separated_by_new_line_should_return_the_sum() throws Exception {
        assertThat(add("1\n2"), is(3));
        assertThat(add("1\n1\n1"), is(3));
    }

    @Test
    public void add_given_specific_separator_should_return_the_sum() throws Exception {
        assertThat(add("//;\n2;1"), is(3));
        assertThat(add("//*\n2*1"), is(3));
        assertThat(add("//#\n2#1"), is(3));
        assertThat(add("//#\n2"), is(2));
        assertThat(add("//#\n2#1#1"), is(4));
    }

    @Test
    public void add_given_one_negative_number_should_throw_exception() throws Exception {
        expectedException.expect(NegativeNumberNotSupportedException.class);
        expectedException.expectMessage("negativos no soportados: -1");

        add("//;\n2;-1");
    }

    @Test
    public void add_given_several_negative_numbers_should_throw_exception() throws Exception {
        expectedException.expect(NegativeNumberNotSupportedException.class);
        expectedException.expectMessage("negativos no soportados: -2, -1");

        add("//;\n-2;-1");
    }

    @Test
    public void add_should_ignore_numbers_greater_than_one_thousand() throws Exception {
        assertThat(add("1,1001"), is(1));
    }

    @Test
    public void add_given_specific_separator_of_any_length_should_return_the_sum() throws Exception {
        assertThat(add("//##\n2##1"), is(3));
        assertThat(add("//[##]\n2##1"), is(3));
    }

    @Test
    public void add_given_multiple_separators_should_return_the_sum() throws Exception {
        assertThat(add("//[;][#]\n2;1"), is(3));
        assertThat(add("//[;][#]\n1;1#1"), is(3));
        assertThat(add("//[;][##]\n1;1##1"), is(3));
    }

    private int add(final String input) throws NegativeNumberNotSupportedException {
        return StringCalculator.add(input);
    }

}