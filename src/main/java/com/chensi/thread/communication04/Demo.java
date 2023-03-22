package com.chensi.thread.communication04;

/*
 * @author  chensi
 * @date  2023/1/4
 */

import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多生产者多消费者问题
 * 容器可以使用数组，生产者消费者都需要一个指针变量，表示下一个生产出的烤鸭的位置和下一个将要消费的烤鸭所在的位置。此外，应当有一个变量标识当前容器内有多少烤鸭，以便于决定生产者和消费者在容器已满和容器为空的时候的动作。
 * 此外，需要一把同步锁，这是必须的，锁上有两个监视器，这是为了提高效率，原因前面已经解释过。
 * 变量说明：
 * lock：锁名。
 * notFull：生产者监视器。
 * notEmpty：消费者监视器。
 * items：对象数组，存放“烤鸭”
 * putptr：指示生产者下一个生产出来的烤鸭应当存放在什么位置。
 * takeptr：指示消费者下一个将要消费的烤鸭所在的位置。
 * count：指示当前容器中的烤鸭数量，初始值是0。
 * 容器的大小是5。
 */
public class Demo {
	public static void main(String[] args) {
		BoundedBuffer r = new BoundedBuffer();
		Procedure input = new Procedure(r);
		Consumer output = new Consumer(r);
		Thread t0 = new Thread(input);
		Thread t1 = new Thread(input);

		Thread t2 = new Thread(output);
		Thread t3 = new Thread(output);

		Thread t4 = new Thread(input);

		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class BoundedBuffer {

	final Lock lock = new ReentrantLock();
	final Condition produce = lock.newCondition();
	final Condition comsumer = lock.newCondition();

	final Object[] items = new Object[5];
	int putptr, takeptr, count = 0;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {
				//生产线已经饱和，无需生产。线程进入等待池。
				produce.await();
			}
			//生产下一个产品
			items[putptr] = x;
			System.out.println(Thread.currentThread().getName() + ":----生产" + items[putptr] + putptr);
			//如果生产到了尾部，则从头开始生产
			if (++putptr == items.length) putptr = 0;
			//数量+1
			++count;
			//唤醒消费者进入锁池
			comsumer.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				comsumer.await();
			}
			//消费产品
			Object x = items[takeptr];
			System.out.println(Thread.currentThread().getName() + ":-----------消费" + items[takeptr] + takeptr);
			if (++takeptr == items.length) takeptr = 0;//消费到了最后一个产品，则从头开始找货物
			//货物数量自减
			--count;
			produce.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
}

class Procedure implements Runnable {
	BoundedBuffer r;

	public Procedure() {
	}

	public Procedure(BoundedBuffer r) {
		this.r = r;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				r.put("habi");
			} catch (InterruptedException e) {
			}
		}
	}
}

class Consumer implements Runnable {
	BoundedBuffer r;

	public Consumer() {
	}

	public Consumer(BoundedBuffer r) {
		this.r = r;
	}

	@Override
	public void run() {
		while (true) {
			while (true) {
				try {
					Thread.sleep(1000);
					r.take();
				} catch (InterruptedException e) {
				}
			}
		}
	}
}

