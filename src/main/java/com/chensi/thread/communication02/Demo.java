package com.chensi.thread.communication02;

/*
 * @author  chensi
 * @date  2023/1/3
 */

/**
 * 线程安全性问题
 * 1)等待池：假设一个线程A调用了某个对象的wait()方法，线程A就会释放该对象的锁后，进入到了该对象的等待池。
 *          等待池中的线程不会去竞争该对象的锁。
 *
 * 2）锁池：只有获取了对象的锁，线程才能执行对象的synchronized代码，对象的锁每次只有一个线程可以获得，
 *          其他线程只能在锁池中等待
 */
public class Demo {
	public static void main(String[] args) {
		Resource r = new Resource();
		Procedure input = new Procedure(r);
		Consumer output = new Consumer(r);
		Thread t0 = new Thread(input);
		Thread t1 = new Thread(input);
		Thread t2 = new Thread(output);
		Thread t3 = new Thread(output);
		t0.start();
		t1.start();
		t2.start();
		t3.start();
	}
}

class Resource {
	//用于等待和唤醒操作,默认false,需要input
	boolean flag = false;
	//使用私有变量并且提供对外的访问方法
	private String name;
	private int count = 1;

	public synchronized void set(String name) {
		while (this.flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		this.name = name + count++;
		System.out.println(Thread.currentThread().getName() + "----------生产了" + this.name);
		this.flag = true;
		//this.notify();
		this.notifyAll();
	}

	public synchronized void out() {
		while (!this.flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		System.out.println(Thread.currentThread().getName() + "----------消费了" + this.name);
		this.flag = false;
		//this.notify();
		this.notifyAll();
	}
}

class Procedure implements Runnable {
	Resource r;

	public Procedure() {
	}

	public Procedure(Resource r) {
		this.r = r;
	}

	@Override
	public void run() {
		while (true) {
			r.set("物品");
		}
	}
}

class Consumer implements Runnable {
	Resource r;

	public Consumer() {
	}

	public Consumer(Resource r) {
		this.r = r;
	}

	@Override
	public void run() {
		while (true) {
			r.out();
		}
	}
}

