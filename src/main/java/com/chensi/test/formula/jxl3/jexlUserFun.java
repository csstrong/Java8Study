package com.chensi.test.formula.jxl3;

import org.apache.commons.jexl3.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/***********************************
 * @author chensi
 * @date 2022/2/19 10:38
 ***********************************/
public class jexlUserFun {
    public static void main(String[] args) {

    }

    @Test
    public void test1() {
        // 初始化Jexl构造器
        JexlBuilder jexlBuilder = new JexlBuilder();
        // 创建Jexl表达式引擎
        JexlEngine jexlEngine = jexlBuilder.create();
        // 创建Jexl表达式解析器
        JexlScript jexlScript = jexlEngine.createScript("if(tem>=25){grade=1;gradeName='有灾害';}else{grade=0;gradeName='无灾害';}");
        // 创建Jexl表达式变量上下文
        JexlContext jexlContext = new MapContext();
        jexlContext.set("tem", 15);
        // 执行Jexl表达式，得到结果
        jexlScript.execute(jexlContext);
        System.out.println(jexlContext.get("grade"));
        System.out.println(jexlContext.get("gradeName"));
    }


    /**
     * Jexl 表达式引擎可以应用在许多场景下，比如：校验数据，解析模版等
     */

    //表达式列表为数组时
    @Test
    public void test2() {
        // 初始化Jexl构造器
        JexlBuilder jexlBuilder = new JexlBuilder();
        // 创建Jexl表达式引擎
        JexlEngine jexlEngine = jexlBuilder.create();
        JexlContext context = new MapContext();//创建Context设值对象
        String expressionStr = "array.size()";//表达式，表达式可以是数组的属性，元素等
        List<Object> array = new ArrayList<Object>();//创建一个列表
        array.add("this is an array");
        array.add(new Integer(2));
        context.set("array", array);//使用context对象将表达式中用到的值设置进去，必须是所有用到的值
        //使用引擎创建表达式对象
        JexlExpression expression = jexlEngine.createExpression(expressionStr);
        Object o = expression.evaluate(context);//使用表达式对象开始计算
        System.out.println(o);//输出：2
    }

    //表达式为语句块时，经常用在数据校验
    @Test
    public void test3() {
        // 初始化Jexl构造器
        JexlBuilder jexlBuilder = new JexlBuilder();
        // 创建Jexl表达式引擎
        JexlEngine engine = jexlBuilder.create();
        JexlContext context = new MapContext();//创建Context设值对象
        String expressionStr = "if(a>b){c=a;}else{c=b};";//表达式，表达式为逻辑语句
        context.set("a", 1);
        context.set("b", 2);
        //JexlExpression expression = engin e.createExpression(expressionStr);//使用引擎创建表达式对象
        JexlScript jexlScript = engine.createScript(expressionStr);//使用引擎创建表达式对象
        Object o = jexlScript.execute(context);
        //Object o = expression.evaluate(context);//使用表达式对象开始计算
        System.out.println(o);//输出：2

        expressionStr = "while(a<b){a=a+b;}";
        jexlScript = engine.createScript(expressionStr);//使用引擎创建表达式对象
        o = jexlScript.execute(context);//使用表达式对象开始计算
        System.out.println(o);//输出：3
    }


    public class Person {
        private String name;
        private String sex;

        public Person(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        public String getInfo() {
            return this.name + "+" + this.sex;
        }
    }

    //当表达式为调用方法时
    @Test
    public void test4() {
        // 初始化Jexl构造器
        JexlBuilder jexlBuilder = new JexlBuilder();
        // 创建Jexl表达式引擎
        JexlEngine engine = jexlBuilder.create();
        JexlContext context = new MapContext();//创建Context设值对象
        String expressionStr = "person.getInfo()";//表达式，表达式为对象调用方法，当然也可以是类调用方法
        Person person = new Person("hliu", "M");
        context.set("person", person);
        JexlExpression expression = engine.createExpression(expressionStr);//使用引擎创建表达式对象
        Object o = expression.evaluate(context);//使用表达式对象开始计算
        //输出：hliu+M
        System.out.println(o);
    }
}
