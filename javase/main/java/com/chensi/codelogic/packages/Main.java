package com.chensi.codelogic.packages;

/*
 * @author  chensi
 * @date  2023/4/21
 */

/**
 * 测试继承破坏了封装的关系
 * 小结：对于子类而言，通过继承实现是没有安全保障的，因为父类修改内部实现细节，它的功能就可能会被破坏；
 * 而对于基类而言，让子类继承和重写方法，就可能丧失随意修改内部实现的自由。
 * 继承关系是用来反应is-a关系的，子类是父类的一种，子类对象也属于父类，父类的属性和行为也适用于子类。
 */
public class Main {
	public static void main(String[] args) {
		Child c = new Child();
		c.addAll(new int[]{1, 2, 3});
		c.clear();
		c.addAll(new int[]{1, 2, 3});

		System.out.println(c.getSum());
	}
}
