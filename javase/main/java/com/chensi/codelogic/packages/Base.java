package com.chensi.codelogic.packages;

/*
 * @author  chensi
 * @date  2023/4/21
 */
public class Base {
	private static final int MAX_NUM = 1000;
	private int[] arr = new int[MAX_NUM];
	private int count;

	public void add(int number) {
		if (count < MAX_NUM) {
			arr[count++] = number;
		}
	}

	public void addAll(int[] numbers) {
		for (int num : numbers) {
			add(num);
		}
	}

	//修改子类的实现功能和细节
	//public void addAll(int[] numbers) {
	//	for (int num : numbers) {
	//		if (count < MAX_NUM) {
	//			arr[count++] = num;
	//		}
	//	}
	//}

	public void clear() {
		for (int i = 0; i < count; i++) {
			arr[i] = 0;
		}
		count = 0;
	}
}
