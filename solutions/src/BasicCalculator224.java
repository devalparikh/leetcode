import java.util.Stack;

// https://leetcode.com/problems/basic-calculator/
public class BasicCalculator224 {
    public static void main(String args[]) {
        BasicCalculator224 calc = new BasicCalculator224();

        System.out.println(calc.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    public int calculate(String s) {

        int sign = 1; // 1 is plus, -1 is minus. Default value is plus
        int sum = 0; // keeps track of sum at current parenthesis level

        Stack<Integer> stack = new Stack<>(); // hold built up sums

        // Iterate through all characters in the expression string
        for(int i = 0; i < s.length(); i++) {

            // Check if current char is number
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {

                // Could be more than 1 digit

                // Read through entire num
                int num = 0;
                while(i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                    num = num*10 + s.charAt(i) - '0';
                    i++;
                }

                // Add to current sum and also apply current sign
                sum += num*sign;
                // bring back i after last i++ when building num
                i--;

            // If current char is +
            } else if(s.charAt(i) == '+') {
                sign = 1;

            // If current char is -
            } else if(s.charAt(i) == '-') {
                sign = -1;

            // If current char is (
            } else if(s.charAt(i) == '(') {

                // Push curent sum and current sign
                stack.push(sum);
                stack.push(sign);

                // Restart state of sum and sign
                sum = 0;
                sign = 1;

                // If current char is )
            } else if(s.charAt(i) == ')') {

                // Pop from stack to get sign
                sum = stack.pop() * sum; // apply sign

                // Pop again from stack to get prev layers sum
                sum += stack.pop(); // add to global sum
            }
        }
        return sum;
    }
}

