import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printMenu();
        String input = sc.nextLine();
        if (input.split(" ").length != 3) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else {
            System.out.println(calc(input));
        }
    }

    public static String calc(String input) {
        String answ = null;
        String[] partsOfExpression = input.split("\\s+");
        int firstNumber;
        int secondNumber;
        char action = partsOfExpression[1].trim().charAt(0);
        try {
            if (isArabic(partsOfExpression[0]) && isArabic(partsOfExpression[2])) {
                firstNumber = Integer.parseInt(partsOfExpression[0].trim());
                secondNumber = Integer.parseInt(partsOfExpression[2].trim());
                if (firstNumber  < 1 || firstNumber > 10 || secondNumber < 1 || secondNumber > 10) {
                    answ = "throws Exception //т.к. число д.б. от 1 до 10";
                } else {
                    switch (action) {
                        case '+' -> answ = plus(firstNumber, secondNumber);
                        case '-' -> answ = minus(firstNumber, secondNumber);
                        case '*' -> answ = multiply(firstNumber, secondNumber);
                        case '/' -> answ = divide(firstNumber, secondNumber);
                        default -> answ = "throws Exception //т.к. неверный оператор уравнения";
                    }
                }
            } else if (isRoman(partsOfExpression[0]) && isRoman(partsOfExpression[2])) {
                firstNumber = RomanNumber.valueOf(partsOfExpression[0]).getNumber();
                secondNumber = RomanNumber.valueOf(partsOfExpression[2]).getNumber();
                switch (action) {
                    case '+' : answ = arabicToRoman(Integer.parseInt(plus(firstNumber, secondNumber)));
                        break;
                    case '-' : if (firstNumber > secondNumber) {
                        answ = arabicToRoman(Integer.parseInt(minus(firstNumber, secondNumber)));
                    } else {
                        answ = "throws Exception //т.к. в римской системе нет отрицательных чисел";
                    }
                        break;
                    case '*' : answ = arabicToRoman(Integer.parseInt(multiply(firstNumber, secondNumber)));
                        break;
                    case '/' : answ = arabicToRoman(Integer.parseInt(divide(firstNumber, secondNumber)));
                        break;
                    default : answ = "throws Exception //т.к. неверный оператор уравнения";
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            answ = "throws Exception //т.к. используются одновременно разные системы счисления";

        }
        return answ;
    }


    public static void printMenu() {
        System.out.println("Введите выражение для вычисления");
    }

    public static boolean isArabic(String numForCheck) {
        try {
            Arrays.stream(ArabicNumber.values()).anyMatch(element -> element.getNumber() == Integer.parseInt(numForCheck));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isRoman(String numForCheck) {
        try {
            Arrays.stream(ArabicNumber.values()).anyMatch(element -> element.values().toString().equals(numForCheck));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String plus(int a, int b) {
        return Integer.toString(a + b);
    }

    public static String minus(int a, int b) {
        return Integer.toString(a - b);
    }

    public static String multiply(int a, int b) {
        return Integer.toString(a * b);
    }

    public static String divide(int a, int b) {
        return Integer.toString(a / b);
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }
        List<RomanNumberForAnsw> romanNumerals = RomanNumberForAnsw.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumberForAnsw currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getNumber() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getNumber();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
