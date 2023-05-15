package com.chensi.codelogic.enums;

/*
 * @author  chensi
 * @date  2023/4/23
 */
public class Main {
	public static void main(String[] args) {
		Size s = Size.MEDIUM;
		System.out.println(s.getAbbr()); //输出M
		s = Size.fromAbbr("L");
		System.out.println(s.getTitle()); //输出“大号”
	}
}
