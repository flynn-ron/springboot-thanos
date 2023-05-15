package boot.thanos;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ronghl
 * @date 2023/05/09
 */
public class CalculatorTest {

    @Test
    void test1() {
        List<String> list = Stream.of(
//                "-1+0",
//                "1+1",
//                "1+2",
//                "1-0",
//                "1-1",
//                "1-2",
//                "1*0",
//                "1*1",
//                "1*2",
//                "1/0",
//                "1/1",
//                "1/2",
//                "(-2+1)*2",
//                "(-2-1)*2",
//                "(-2*2)*2",
//                "(-2/2)*2",
                "((1+3)-(2-1)*2+(1+2)*2)"
        ).collect(Collectors.toList());
        list.forEach(exp -> {
            System.out.println(String.format("%s=%s", exp, Calculator.compute(exp)));
        });
    }


    @Test
    public void testCompute() {
        assertEquals(3.0, Calculator.compute("1+2"), 0.0);
        assertEquals(-4.0, Calculator.compute("1-5"), 0.0);
        assertEquals(6.0, Calculator.compute("2*3"), 0.0);
        assertEquals(2.0, Calculator.compute("6/3"), 0.0);
        assertEquals(16.0, Calculator.compute("(5+3)*2"), 0.0);
        assertEquals(-1.0, Calculator.compute("(5-7)/2"), 0.0);
        assertEquals(-3.0, Calculator.compute("3-2*3"), 0.0);
        assertEquals(6.0, Calculator.compute("2*(1+3)-4/2"), 0.0);
        assertEquals(8.0, Calculator.compute("((1+3)-(2-1)*2+(1+2)*2)"), 0.0);
        assertEquals(20.0, Calculator.compute("((2+3)*4/2)+5*2"), 0.0);
    }

    @Test
    void testComputeNegative() {
//        1. 测试加法：-3 + 4 = 1
        assertEquals(1.0, Calculator.compute("-3+4"), 0.0);

//        2. 测试减法：5 - (-2) = 7
        assertEquals(7.0, Calculator.compute("5-(-2)"), 0.0);

//        3. 测试乘法：-4 * 3 = -12
        assertEquals(-12.0, Calculator.compute("-4*3"), 0.0);

//        4. 测试除法：10 / (-2) = -5
        assertEquals(-5.0, Calculator.compute("10/(-2)"), 0.0);

//        5. 测试加减乘除法混合：(-3 + 5) * (-2) / 2 - 1 = -4
        assertEquals(-3.0, Calculator.compute("(-3+5)*(-2)/2-1"), 0.0);

//        6. 测试加减乘除法混合，带有括号：(-4 + 2) * (-3 - 2) / (-5) = -8
        assertEquals(-2.0, Calculator.compute("(-4+2)*(-3-2)/(-5)"), 0.0);

//        7. 测试只有左括号的表达式：(5 - 3 * (2 + 1) = -4
        assertEquals(-4.0, Calculator.compute("(5-3*(2+1))"), 0.0);

//        8. 测试只有右括号的表达式：5 - ((2 + 1) * 2 - 3)) = 2
        assertEquals(2.0, Calculator.compute("5-((2+1)*2-3)"), 0.0);


    }

    @Test
    void testCompute3() {
        Calculator.compute(5.0, -2.0, '/');
        assertEquals(-2.5, Calculator.compute("((3-2)*4*(-2/2)+(5*(-1+3)-1))/(2+(-4))"), 0.0);
    }

    @Test
    void testCompute4(){
        assertEquals(-2.0, Calculator.compute("((-1+((-2+3)+2))*(4+1)-(3*(-1)+5))/(2*(2-4))"), 0.0);
    }

}
