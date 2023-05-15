package com.chensi.designpattern.dp1;

/*
 * @author  chensi
 * @date  2023/4/7
 */
public class ConcreteDecoratorA extends Decorator {
	//本类独有字段，以区别于ConcreteDecoratorB类
	private String addedState;

	public void Operation() {
		//首先运行原有Component的Operation()
		super.Operation();

		//再执行本类的独有功能
		this.addedState = "具体装饰对象A的独有操作";
		System.out.println(this.addedState);
	}
}
