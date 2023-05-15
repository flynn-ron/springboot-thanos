package boot.thanos;

import java.util.Stack;

/**
 * @author ronghl
 * @date 2023/05/08
 */
public class Calculator {

    public static final int DEFAULT_SCALE = 4;

    public static double compute(String expression) {
        if (expression == null || expression.length() == 0) {
            throw new IllegalArgumentException("expression不能为空");
        }
        expression = expression.replace(" ", "");
        Stack<Double> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);
            if ('-' == c) {
                // 检查是否为负数, 若为负数，则解析值并push
                if (i == 0 || (i > 0 && expression.charAt(i - 1) == '(')) {
                    numStack.push(0d);
                    opStack.push(c);
                    i++;
                    continue;
                }
            } else if ('(' == c) {
                opStack.push(c);
                i++;
                continue;
            }
            if (isDigit(c)) {
                // 数字，往下找出完整的数值
                int j = i + 1;
                while (j < expression.length() && isDigit(expression.charAt(j))) {
                    j++;
                }
                double num = Double.parseDouble(expression.substring(i, j));
                numStack.push(num);
                i = j;
                continue;
            }
            if (isOperator(c)) {
                // 前方运算优先级为平级/更高，则前方两个数值先计算。
                while (!opStack.isEmpty() && getPriority(opStack.peek()) >= getPriority(c)) {
                    execOneOperation(opStack, numStack);
                }
                // push 操作符
                opStack.push(c);
                i++;
                continue;
            }
            if (')' == c) {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    execOneOperation(opStack, numStack);
                }
                // 弹出一个左括号
                opStack.pop();
                i++;
                continue;
            }
            throw new IllegalArgumentException("不支持的操作符：" + c);
        }
        while (!opStack.isEmpty() && numStack.size() > 1) {
            execOneOperation(opStack, numStack);
        }
        if (opStack.size() > 0 || numStack.size() > 1) {
            throw new IllegalArgumentException("非法表达式: " + expression);
        }
        return numStack.pop();
    }

    private static void execOneOperation(Stack<Character> opStack, Stack<Double> numStack) {
        char op = opStack.pop();
        Double num2 = numStack.pop();
        if (numStack.isEmpty()) {
            throw new IllegalArgumentException("非法表达式!");
        }
        Double num1 = numStack.pop();
        System.out.println(String.format("执行一次运算操作: %s %s %s", num1, op, num2));
        numStack.push(compute(num1, num2, op, DEFAULT_SCALE));
    }

    /**
     * 运算符优先级
     *
     * @param op
     * @return
     */
    public static int getPriority(char op) {
        switch (op) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    public static boolean isDigit(char c) {
        return ('0' <= c && c <= '9') || c == '.';
    }

    public static boolean isOperator(char c) {
        return '+' == c || '-' == c || '*' == c | '/' == c;
    }


    public static double compute(double a, double b, char op) {
        return compute(a, b, op, DEFAULT_SCALE);
    }

    public static double compute(double a, double b, char op, int scale) {
        double result;
        switch (op) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("除数不能为0");
                }
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("不支持的操作");
        }
        return Math.round(result * Math.pow(10, scale))/ Math.pow(10, scale);
    }

}
