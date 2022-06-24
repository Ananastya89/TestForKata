import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

enum RomanNumberForAnsw {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100);

    private int number;

    RomanNumberForAnsw(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static List<RomanNumberForAnsw> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumberForAnsw e) -> e.getNumber()).reversed())
                .collect(Collectors.toList());
    }
}


