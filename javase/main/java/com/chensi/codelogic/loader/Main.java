package com.chensi.codelogic.loader;

/*
 * @author  chensi
 * @date  2023/4/20
 */
//测试继承的关系下，父、子类的不同组成成分的加载顺序
public class Main {
	public static void main(String[] args) {
		System.out.println("---- new Child()");
		Child c = new Child();
		System.out.println("\n---- c.action()");
		c.action();
		Base b = c;
		System.out.println("\n---- b.action()");
		b.action();
		System.out.println("\n---- b.s: " + b.s);
		System.out.println("\n---- c.s: " + c.s);
	}
}
