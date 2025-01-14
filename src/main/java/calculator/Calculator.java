package calculator;

import java.util.regex.Pattern;

public class Calculator {
    public int calculate(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        String delimiter = ",|:";
        String number = input;

        if (input.startsWith("//")) {
            if (input.length() >= 5 && input.charAt(3) == '\\' && input.charAt(4) == 'n') {
                String customDelimiter = Pattern.quote(String.valueOf(input.charAt(2)));
                delimiter = delimiter + "|" + customDelimiter;
                number = input.substring(5);
            } else {
                throw new IllegalArgumentException("유효하지 않은 구분자 형식입니다.");
            }
        }

        String[] numbers = splitString(number, delimiter);
        int sum = sumNumbers(numbers);

        return sum;
    }

    private String[] splitString(String number, String delimiter) {
        return number.split(delimiter, -1);
    }

    private int sumNumbers(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            if (!number.trim().isEmpty()) {
                int num = parseNumber(number.trim());
                validateNumber(num);
                sum += num;
            }
        }
        return sum;
    }

    private int parseNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 변환에 실패했습니다.");
        }
    }

    private void validateNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
    }
}