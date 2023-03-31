import java.util.Stack;

public class ReversePolishNotation {
    public static void main(String[] args) {

        String formula = "321+232^^*+8512*2/-/-";

        // From infix form to final result
        System.out.println(algorithm(formula));
        // Result 1537
    }

    public static int algorithm(String formula) {
        Stack<Integer> stack = new Stack<>();
        int firstDigit;
        int secondDigit;
        int result;

        for (int i = 0; i < formula.length(); i++) {
            char x = formula.charAt(i);
            if (Character.isDigit(x)) {
                stack.push(Integer.parseInt(String.valueOf(x)));
            } else {
                if (stack.size() < 2) {
                    System.out.println(("Error in the formula"));
                    return 0;
                }
                firstDigit = stack.pop();
                secondDigit = stack.pop();
                result = calculate(firstDigit,secondDigit, x);
                stack.push(result);
            }
        }
        result = stack.pop();
        if (!stack.isEmpty()) {
            System.out.println(("Error in the formula"));
        }

        return result;
    }

    public static int calculate(int firstDigit, int secondDigit, char c) {
        return switch (c) {
            case '+' -> secondDigit + firstDigit;
            case '-' -> secondDigit - firstDigit;
            case '*' -> secondDigit * firstDigit;
            case '/' -> secondDigit / firstDigit;
            case '^' -> power(secondDigit, firstDigit);
            default -> 0;
        };
    }

    public static int power(int base, int powerRaised) {
        if (powerRaised != 0) {
            return (base * power(base, powerRaised - 1));
        } else {
            return 1;
        }
    }
}



