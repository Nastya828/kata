package Test_task;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) throws Exception {
        String[] arrayExpression = input.split(" ");

        if (arrayExpression.length != 3) {
            throw new Exception("Error expression!");
        }

        String firstNumber = arrayExpression[0];
        String operator = arrayExpression[1];
        String secondNumber = arrayExpression[2];
        return String.valueOf(check(operator, firstNumber, secondNumber));

    }

    public static int check(String operator, String firstNumber, String secondNumber) throws Exception {

        String ar = "[1-9]|10";
        boolean arab = (firstNumber.matches(ar)) && (secondNumber.matches(ar));

        String s = "I{1,3}+|IV|VI{0,3}|IX|X";

        boolean numberRome = (firstNumber.matches(s)) && (secondNumber.matches(s));

        if (!operator.matches("[+\\-*/]") || !(arab || numberRome)) {
            throw new Exception("Error expression!");
        }
        if (arab) {
            return arabNumber(Integer.parseInt(firstNumber),
                    Integer.parseInt(secondNumber), operator.charAt(0));
        }
        if (numberRome) {
            return numbersOfRome((firstNumber), (secondNumber), operator.charAt(0));
        }
        return 0;
    }


    public static int arabNumber(int a, int b, char c) {

        switch (c) {
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case '/' -> {
                return a / b;
            }
            case '*' -> {
                return a * b;
            }
            default -> {
                return 0;
            }
        }
    }

    public static int numbersOfRome(String a, String b, char c) throws Exception {
        String[] romeNum = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        Map<String, Integer> romeNumWithArab = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            romeNumWithArab.put(romeNum[i], i + 1);
        }
        if (romeNumWithArab.get(a) <= romeNumWithArab.get(b) && (c == '-')) {
            throw new Exception("Wrong numbers");
        }

        return arabNumber(romeNumWithArab.get(a), romeNumWithArab.get(b), c);
    }
}