package com.chensi.codelogic.thread.demo1;

/*
 * @author  chensi
 * @date  2023/6/1
 */
public class Producer extends Thread {
	MyBlockingQueue<String> queue;

	public Producer(MyBlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		int num = 0;
		try {
			while (true) {
				String task = String.valueOf(num);
				queue.put(task);
				System.out.println("produce task " + task);
				num++;
				Thread.sleep((int) (Math.random() * 100));
			}
		} catch (Exception e) {

		}
	}
}
