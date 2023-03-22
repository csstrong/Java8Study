package com.chensi.pattern.observer;

/*
 * @author  chensi
 * @date  2023/1/6
 */

/**
 * 观察者模式存在三个对象
 * 1.监听者 xxxListener
 * 一般是个接口
 * 2.被监听者
 * 任意对象都能成为被监听者
 * 3.监听到的事件 xxxEvent
 * 永远是个具体类，用来放监听到的数据，永远都会有一个方法getSource,该方法返回被监听的对象
 */
public class Main {
	public static void main(String[] args) {
		Person p = new Person();
		p.addPersonListenter(new PersonListener() {
			@Override
			public void doListen(PersonEvent e) {
				System.out.println("被监听到在running");
				//throw new RuntimeException("he is running!");
			}
		});
		p.run();
	}
}

interface PersonListener {
	void doListen(PersonEvent e);
}

class PersonEvent {
	Person p = null;

	public PersonEvent(Person p) {
		this.p = p;
	}

	public Person getSource() {
		return p;
	}
}

class Person {
	PersonListener listener = null;

	public void addPersonListenter(PersonListener listener) {
		//完成传递监听器的过程
		this.listener = listener;
	}

	public void run() {
		//被监听者只要有动作，监听器也跟着一起动
		//如果不加监听器的话也能够正常跑步
		if (listener != null) {
			listener.doListen(new PersonEvent(this));
		}
		System.out.println("Now running.");
	}
}
