package com.chensi.codelogic.packages;

/*
 * @author  chensi
 * @date  2023/4/21
 */
public class Child extends Base {
	private long sum;

	@Override
	public void add(int number) {
		super.add(number);
		sum += number;
	}

	//@Override
	//public void addAll(int[] numbers) {
	//	super.addAll(numbers);
	//	for (int i = 0; i < numbers.length; i++) {
	//		sum += numbers[i];
	//	}
	//}

	@Override
	public void addAll(int[] numbers) {
		super.addAll(numbers);
	}

	@Override
	public void clear() {
		super.clear();
		this.sum = 0;
	}

	public long getSum() {
		return sum;
	}
}
