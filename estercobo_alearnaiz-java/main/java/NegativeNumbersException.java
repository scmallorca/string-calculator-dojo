import java.util.ArrayList;
import java.util.List;

public class NegativeNumbersException extends RuntimeException {

    private List<Integer> numbers;

    public NegativeNumbersException(List<Integer> numbers) {
        this.numbers = new ArrayList();
        this.numbers.addAll(numbers);
    }

    public String toString() {
        String result = "";
        for (int number : numbers) {
            if (result.equals("")) {
                result = "Negative numbers " + String.valueOf(number);
            } else {
                result += ", " + String.valueOf(number);
            }
        }
        return result;
    }

}
