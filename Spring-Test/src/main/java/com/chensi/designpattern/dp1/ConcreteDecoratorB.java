package com.chensi.designpattern.dp1;

/*
 * @author  chensi
 * @date  2023/4/7
 */
public class ConcreteDecoratorB extends Decorator {

	public void Operation() {
		//首先运行原有Component的Operation()
		super.Operation();
		this.addedBehavior();
	}

	//本类的独有功能
	private void addedBehavior(){
		System.out.println("具体装饰对象B的独有操作");
	}
}
