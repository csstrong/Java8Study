package com.chensi.codelogic.thread.demo1;

/*
 * @author  chensi
 * @date  2023/6/1
 */
public class Main {
	public static void main(String[] args) {
		MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
		new Producer(queue).start();
		new Consumer(queue).start();
	}
}
