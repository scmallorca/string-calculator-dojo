import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

    private List<String> delimiters;

    private List<Integer> negativeNumbers;

    private static final String START_CUSTOM_DELIMITERS = "//";

    private static final String END_CUSTOM_DELIMITERS = "\n";

    private static final String START_CUSTOM_DELIMITER = "[";

    private static final String END_CUSTOM_DELIMITER = "]";

    private static final int BIG_NUMBER = 1000;

    public StringCalculator() {
        negativeNumbers = new ArrayList();
        delimiters = new ArrayList();

        // Default values
        delimiters.add(",");
        delimiters.add("\n");
    }

    public int add(String numbers) {

        int result;
        if (numbers.equals("")) {
            return 0;
        } else if (hasDelimiter(numbers)) {

            numbers = checkCustomDelimiter(numbers);

            try  {
                result = sumSeveralNumbers(numbers);
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            try {
                result = getNumber(numbers);
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new NegativeNumbersException(negativeNumbers);
        }

        return result;
    }

    // Recursive function
    private int sumSeveralNumbers(String numbers) {
        String delimiter = getFirstDelimiter(numbers);

        // Base case
        if (delimiter.equals("")) {
            return getNumber(numbers);
        }

        // Escape special characters
        String quote = Pattern.quote(delimiter);

        String[] splitNumbers = numbers.split(quote, 2);
        return getNumber(splitNumbers[0]) + sumSeveralNumbers(splitNumbers[1]);
    }

    private boolean hasDelimiter(String numbers) {
        for (String delimiter : delimiters) {
            if (numbers.contains(delimiter)) {
                return true;
            }
        }
        return false;
    }

    private String getFirstDelimiter(String numbers) {
        int firstPosition = -1;
        String firstDelimiter = "";
        for (String delimiter : delimiters) {
            int index = numbers.indexOf(delimiter);
            if (index != -1) {
                if (firstPosition == -1 || index < firstPosition) {
                    firstPosition = index;
                    firstDelimiter = delimiter;
                }
            }
        }
        return firstDelimiter;
    }

    private int getNumber(String number) {
        int n = Integer.valueOf(number);

        if (n < 0) {
            negativeNumbers.add(n);
        } else if (n > BIG_NUMBER) {
            return 0;
        }

        return n;
    }

    private String checkCustomDelimiter(String numbers) {
        if (numbers.startsWith(START_CUSTOM_DELIMITERS) && numbers.contains(END_CUSTOM_DELIMITERS)) {

            numbers = numbers.substring(START_CUSTOM_DELIMITERS.length());

            if (isBracketDelimiterFormat(numbers)) {
                numbers = numbers.substring(START_CUSTOM_DELIMITER.length());
                String endCustomDelimiter = END_CUSTOM_DELIMITER;
                numbers = addCustomDelimiter(numbers, endCustomDelimiter);

                while (isBracketDelimiterFormat(numbers)) {
                    numbers = numbers.substring(START_CUSTOM_DELIMITER.length());
                    numbers = addCustomDelimiter(numbers, endCustomDelimiter);
                }

                if (numbers.startsWith(END_CUSTOM_DELIMITERS)) {
                    numbers = numbers.substring(END_CUSTOM_DELIMITERS.length());
                }

            } else {
                numbers = addCustomDelimiter(numbers, END_CUSTOM_DELIMITERS);
            }
        }
        return numbers;
    }

    private String addCustomDelimiter(String numbers, String endCustomDelimiter) {
        String[] splitDelimiter = numbers.split(endCustomDelimiter, 2);

        String newDelimiter = splitDelimiter[0].substring(0, splitDelimiter[0].length());

        // Add new
        delimiters.add(newDelimiter);

        // Return the rest of the numbers
        return splitDelimiter[1];
    }

    private boolean isBracketDelimiterFormat(String numbers) {
        if (numbers.indexOf(START_CUSTOM_DELIMITER) == 0 && numbers.contains(END_CUSTOM_DELIMITER)) {
            return true;
        }
        return false;
    }

}
