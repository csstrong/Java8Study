package com.chensi.formula.jxl3;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;

/***********************************
 * @author chensi
 * @date 2022/2/19 10:33
 ***********************************/
public class commonsJexl3Test {
    public static void main(String[] args) {
        //String expressionString = "1+2+3";
        String expressionString = "100*10-(200+300)";
        JexlEngine jexlEngine = new JexlBuilder().create();
        JexlExpression jexlExpression = jexlEngine.createExpression(expressionString);
        Object evaluate = jexlExpression.evaluate(null);
        System.out.println(evaluate);
    }
}
