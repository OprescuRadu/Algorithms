import java.util.*;

public class ShuntingYard {
    public static void main(String[] args) {
        String formula = "3+(2+1)*2^3^2-8/(5-1*2/2)";

        // From the infix form to the postfix form
        algorithm(formula);
        // Result "321+232^^*+8512*2/-/-"
    }

    public static void algorithm(String formula) {

        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        HashMap<Character, Integer> precedence = new HashMap<>();
        precedence.put('^', 4); // the only operator with associativity right
        precedence.put('*', 3);
        precedence.put('/', 2);
        precedence.put('+', 1);
        precedence.put('-', 1);

        for (int i = 0; i < formula.length(); i++) {
            char x = formula.charAt(i);
            if (Character.isDigit(x)) {
                postfix.append(x);
            } else if (precedence.containsKey(x)) {
                while (!stack.isEmpty() && stack.peek() != '(' && ((precedence.get(x) <= precedence.get(stack.peek()) && x != '^')
                        || (precedence.get(x) < precedence.get(stack.peek()) && x == '^'))) {
                    postfix.append(stack.pop());
                }
                stack.push(x);
            } else if (x == '(') {
                stack.push(x);
            } else if (x == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (stack.isEmpty() || stack.peek() != '(') {
                    System.out.println("Error in the formula!");
                    return;
                }
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() != '(') {
                postfix.append(stack.pop());
            } else {
                System.out.println("Error in the formula!");
                return;
            }
        }
        System.out.println(postfix);
    }
}
