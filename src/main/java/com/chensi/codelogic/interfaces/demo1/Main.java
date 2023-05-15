package com.chensi.codelogic.interfaces.demo1;

import java.util.Arrays;

/*
 * @author  chensi
 * @date  2023/4/21
 */
public class Main {
	public static void main(String[] args) {

	}

	public void test(){
		Point[] points = new Point[]{
			new Point(2,3), new Point(3,4), new Point(1,2)
		};
		System.out.println("max: " + CompUtil.max(points));
		//CompUtil.sort(points);
		System.out.println("sort: "+ Arrays.toString(points));
	}
}
