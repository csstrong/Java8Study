package com.chensi.test.formula.jep;

import org.junit.Test;
import org.nfunk.jep.JEP;

/***********************************
 * @author chensi
 * @date 2022/2/19 10:43
 ***********************************/
public class JepTest {
    public static void main(String[] args) {
        JEP jep = new JEP();
        // 设置公式
        String expression = "100/a";
        // 给变量赋值
        jep.addVariable("a", 3.0);
        // 运算
        jep.parseExpression(expression);
        // 得出结果
        System.out.println(jep.getValue());
    }

    @Test
    public void test1() {
        JEP jep = new JEP();
        try {
            //Node n1 = jep.parse("y=x^2");
            jep.parseExpression("y=x^2");
            //Node n2 = jep.parse("z=x+y");
            for (double x = 0.0; x <= 1.0; x += 0.1) {
                jep.addVariable("x", x);
                Object value1 = jep.getValue();
                //Object value2 = jep.evaluate(n2);
                System.out.println(value1);
                //System.out.println(value2);
                System.out.println("========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
