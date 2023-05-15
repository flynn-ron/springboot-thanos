package boot.thanos;

import java.util.Stack;

public class CalculatorV2 {
    public static double compute(String expression) {
        Stack<Character> operators = new Stack<>();
        Stack<Double> operands = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == ' ') continue;

            if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    operate(operators, operands);
                }
                operators.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                if (i > 0 && (expression.charAt(i - 1) == '+' || expression.charAt(i - 1) == '-' || expression.charAt(i - 1) == '*' || expression.charAt(i - 1) == '/')) {
                    throw new IllegalArgumentException("非法表达式：" + expression.substring(i - 1, i + 1));
                }
                if (i == 0 || (i > 0 && (expression.charAt(i - 1) == '('))) {
                    if (ch == '-') {
                        // 处理负数
                        String numStr = "-";
                        int j = i + 1; // 从下一个字符开始判断数字
                        while (j < expression.length() && (Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.')) {
                            numStr += expression.charAt(j++);
                        }
                        double num = Double.parseDouble(numStr);
                        operands.push(num);
                        i = j - 1;  // 设置索引
                        continue;
                    }
                }
                while (!operators.isEmpty() && operators.peek() != '(' && priority(operators.peek()) >= priority(ch)) {
                    operate(operators, operands);
                }
                operators.push(ch);
            } else {
                String numStr = "";
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.' || expression.charAt(i) == '-')) {
                    numStr += expression.charAt(i++);
                }
                i--;
                double num = Double.parseDouble(numStr);
                operands.push(num);
            }
        }

        while (!operators.isEmpty()) {
            operate(operators, operands);
        }

        return operands.pop();
    }

    // 计算操作数栈中的二元表达式
    private static void operate(Stack<Character> operators, Stack<Double> operands) {
        char op = operators.pop();
        double num2 = operands.pop();
        double num1 = operands.pop();

        if (op == '+') {
            operands.push(num1 + num2);
        } else if (op == '-') {
            operands.push(num1 - num2);
        } else if (op == '*') {
            operands.push(num1 * num2);
        } else if (op == '/') {
            operands.push(num1 / num2);
        }
    }

    // 返回运算符优先级
    private static int priority(char op) {
        if (op == '*' || op == '/') {
            return 2;
        } else if (op == '+' || op == '-') {
            return 1;
        } else {
            return 0;
        }
    }


    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String expression = "((1.1+3)-(-2-1)*2+(1+2)*2)";
        double result = calculator.compute(expression);
        System.out.println(expression + " = " + result);
    }
}

