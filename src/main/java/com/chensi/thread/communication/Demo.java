package com.chensi.thread.communication;

/*
 * @author  chensi
 * @date  2023/1/3
 */

/**
 * 线程安全性问题
 */
public class Demo {
	public static void main(String[] args) {
		Resource r = new Resource();
		Input input = new Input(r);
		Output output = new Output(r);
		Thread t0 = new Thread(input);
		Thread t1 = new Thread(output);
		t0.start();
		t1.start();
	}
}

class Resource {
	//用于等待和唤醒操作,默认false,需要input
	boolean flag = false;
	//使用私有变量并且提供对外的访问方法
	private String name;
	private String sex;

	public synchronized void set(String name, String sex) {
		if (this.flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		this.name = name;
		this.sex = sex;
		this.flag = true;
		this.notify();
	}

	public synchronized void out() {
		if (!this.flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		System.out.println(this.name + "---------" + this.sex);
		this.flag = false;
		this.notify();
	}
}

class Input implements Runnable {
	Resource r;

	public Input() {
	}

	public Input(Resource r) {
		this.r = r;
	}

	//@Override
	//public void run() {
	//	boolean flag = false;
	//	while (true) {
	//		//使用Resource对象锁，因为对于输入输出线程来说，是唯一的
	//		synchronized (r) {
	//			if (r.flag) {
	//				try {
	//					//让输入线程被等待
	//					r.wait();
	//				} catch (InterruptedException e) {
	//				}
	//			}
	//			if (flag) {
	//				r.name="Mike";
	//              r.sex="nan";
	//			} else {
	//				r.name="丽丽";
	//              r.sex="女女女女女女女女女女女女";
	//			}
	//		}
	//		flag = !flag;
	//	}
	//}

	@Override
	public void run() {
		boolean flag = false;
		while (true) {
			if (flag)
				r.set("Tome", "man");
			else
				r.set("Mary", "woman");
			flag = !flag;
		}
	}
}

class Output implements Runnable {
	Resource r;

	public Output() {
	}

	public Output(Resource r) {
		this.r = r;
	}

	//@Override
	//public void run() {
	//	while (true) {
	//		synchronized (r) {
	//			if (!r.flag) {
	//				try {
	//					//让输入线程被等待
	//					r.wait();
	//				} catch (InterruptedException e) {
	//				}
	//			}
	//			System.out.println(r.name + "--------" + r.sex);
	//			r.flag = false;
	//			r.notify();
	//		}
	//	}
	//}

	@Override
	public void run() {
		while (true) {
			r.out();
		}
	}
}

