package com.chensi.designpattern.dp1;

/*
 * @author  chensi
 * @date  2023/4/7
 */
public class Main {
	public static void main(String[] args) {
		ConcreteComponent c = new ConcreteComponent();
		ConcreteDecoratorA d1 = new ConcreteDecoratorA();
		ConcreteDecoratorB d2 = new ConcreteDecoratorB();

		d1.SetComponent(c);
		d2.SetComponent(d1);
		d2.Operation();
	}
}
