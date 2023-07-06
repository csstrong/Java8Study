package com.chensi.codelogic.thread.demo1;

/*
 * @author  chensi
 * @date  2023/6/1
 */
public class Consumer extends Thread {
	MyBlockingQueue<String> queue;

	public Consumer(MyBlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String task = queue.take();
				System.out.println("handle task " + task);
				Thread.sleep((int) (Math.random() * 100));
			}
		} catch (InterruptedException e) {

		}
	}
}
