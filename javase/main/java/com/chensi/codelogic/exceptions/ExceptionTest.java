package com.chensi.codelogic.exceptions;

/*
 * @author  chensi
 * @date  2023/4/23
 */
public class ExceptionTest {
	public static void main(String[] args) {
		//test1();
		test2(args);
	}

	public static void test1() {
		String s = null;
		s.indexOf("a");
		System.out.println("end");
	}

	public static void test2(String[] args) {
		if (args.length < 1) {
			System.out.println("请输入数字");
			return;
		}
		int num = Integer.parseInt(args[0]);
		System.out.println(num);
	}
}
