package com.chensi.thread.communication03;

/*
 * @author  chensi
 * @date  2023/1/3
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多生产者，多消费者问题
 * 使用JDK1.5之后的新特性
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
	private boolean flag = false;
	//使用私有变量并且提供对外的访问方法
	private String name;
	private int count = 1;
	Lock lock = new ReentrantLock();
	Condition procedures = lock.newCondition();
	Condition consumers = lock.newCondition();

	public void set(String name) {
		lock.lock();
		try {
			while (this.flag) {
				try {
					procedures.await();
				} catch (InterruptedException e) {
				}
			}
			this.name = name + count++;
			System.out.println(Thread.currentThread().getName() + "----------生产了" + this.name);
			this.flag = true;
			consumers.signal();
		} finally {
			lock.unlock();
		}
	}

	public void out() {
		lock.lock();
		try {
			while (!this.flag) {
				try {
					consumers.await();
				} catch (InterruptedException e) {
				}
			}
			System.out.println(Thread.currentThread().getName() + "----------消费了" + this.name);
			this.flag = false;
			procedures.signal();
		} finally {
			lock.unlock();
		}
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

