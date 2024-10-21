package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AddCalculator {
    static Pattern customDelimiterRegex = Pattern.compile("//(.)\\\\n(.*)");

    boolean isCustomDelimiterExisted(String input) {
        Matcher matcher = customDelimiterRegex.matcher(input);
        return matcher.matches();
    }

    String[] validateCustomDelimiterInput(String input) {
        Matcher matcher = customDelimiterRegex.matcher(input);

        if (!matcher.find()) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
        }

        // TODO: 예외 상황 처리 로직 추가

        String customDelimiter = matcher.group(1);

        return matcher.group(2).split(",|:|" + customDelimiter);
    }

    String[] validateDefaultInput(String input) {

        // TODO: 예외 상황 처리 로직 추가

        return input.split("[,:]");
    }

    static int[] toIntArray(String[] parsedList) {
        int[] numbers = new int[parsedList.length];
        for (int i = 0; i < parsedList.length; i++) {
            numbers[i] = Integer.parseInt(parsedList[i]);
        }
        return numbers;
    }

    static int add(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

}

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int result = 0;
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        result = calculateSum(input);
        System.out.println("결과 : " + result);
    }

    private static int calculateSum(String input) {
        AddCalculator calculator = new AddCalculator();
        String[] parsedList;
        if (calculator.isCustomDelimiterExisted(input)) {
            parsedList = calculator.validateCustomDelimiterInput(input);
        } else {
            parsedList = calculator.validateDefaultInput(input);
        }
        int[] numbers = AddCalculator.toIntArray(parsedList);
        return AddCalculator.add(numbers);
    }

}
