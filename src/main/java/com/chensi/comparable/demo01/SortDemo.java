package com.chensi.comparable.demo01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * @author  chensi
 * @date  2023/1/5
 */
public class SortDemo {
	public static void main(String[] args) {
		//Demo1();
		Demo2();

	}

	private static void Demo2() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("abcd");
		al.add("abc");
		al.add("bc");
		al.add("aabadsf");
		al.add("b");
		al.add("cbae");
		al.add("bade");
		al.add("caaa");
		System.out.println("排序前：" + al);
		Collections.sort(al, new ComparatorByLength());
		System.out.println("排序后：" + al);
	}

	/**
	 * Demo1:该方法使用的是不带比较器的Collections.sort方法
	 */
	private static void Demo1() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("abc");
		al.add("bca");
		al.add("aab");
		al.add("bac");
		al.add("cba");
		System.out.println("排序前：" + al);
		Collections.sort(al);
		System.out.println("排序后：" + al);
	}
}

class ComparatorByLength implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		int temp = o1.length() - o2.length();
		if (temp == 0) {
			if (o1.length() > 0 && o2.length() > 0) {
				char c1 = o1.charAt(0);
				if ('c' == c1) {
					return -1;
				} else {
					return 1;
				}
			}
		} else {
			return temp;
		}
		return 1;
	}
}
