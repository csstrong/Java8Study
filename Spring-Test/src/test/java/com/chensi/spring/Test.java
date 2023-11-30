package com.chensi.spring;

import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Random;

/**
 * @author chensi
 * @date 2023/9/27
 */
public class Test {

    @org.junit.Test
	public void test01() {
		List<String> list = new ArrayList<>();
		list.add("0.0-100.0");
		list.add("-0.1-10.0");
		list.add("-5.0--1.0");

		for (String alarmRange : list) {
			double lowRange = 0d;
			double highRange = 0d;
			int cnt = (int) alarmRange.chars().filter(f -> '-' == f).count();
			if (cnt == 1) {
				//上下限都为正值 (0.0-100.0)
				lowRange = Double.parseDouble(alarmRange.substring(0, alarmRange.indexOf("-")));
				highRange = Double.parseDouble(alarmRange.substring(alarmRange.indexOf("-") + 1));
			} else if (cnt == 2) {
				//上下限存在负值，且为下限值 (-0.1-10.0)
				lowRange = Double.parseDouble(alarmRange.substring(0, alarmRange.lastIndexOf("-")));
				highRange = Double.parseDouble(alarmRange.substring(alarmRange.lastIndexOf("-") + 1));
			} else if (cnt == 3) {
				//上下限都为负值 (-5.0--1.0)
				lowRange = Double.parseDouble(alarmRange.substring(0, alarmRange.lastIndexOf("-") - 1));
				highRange = Double.parseDouble(alarmRange.substring(alarmRange.lastIndexOf("-")));
			}
			System.out.println("lowRange:" + lowRange + ",highRange:" + highRange);
		}
	}

	@org.junit.Test
	public void test02() {
		String s1 = "yyyy/MM/dd HH:mm:ss";
		System.out.println(s1.substring(0, 19));
		System.out.println(s1.substring(0, 10));
	}

	@org.junit.Test
	public void test03() {

		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("当前时间： " + localDateTime);

		LocalDateTime roundedDateTime = roundToNearestFiveMinutes(localDateTime);
		System.out.println("归整后的时间： " + roundedDateTime);
	}

	public LocalDateTime roundToNearestFiveMinutes(LocalDateTime localDateTime) {
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
		int minute = zonedDateTime.getMinute();
		long minutes = minute / 5 ;
		zonedDateTime = zonedDateTime.withMinute((int) minutes).withSecond(0).withNano(0);
		return zonedDateTime.toLocalDateTime();
	}

	@org.junit.Test
	public void test04() throws InterruptedException {
		PriorityQueue<String> queue = new PriorityQueue<>(10, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		boolean flag = true;
		while (flag) {
			Thread.sleep(1000);
			LocalDateTime now = LocalDateTime.now();
			String time = now.format(dateTimeFormatter);
			int size = queue.size();
			if (size > 10) {
				queue.poll();
			}
			queue.offer(time);
			size = queue.size();
			System.out.println("val:" + time + ",size:" + size);
			if (size == 10) {
				flag = false;
			}
		}
		System.out.println(queue);
	}

	@org.junit.Test
	public void test05() {
		Random random = new Random();
		double min = 75, max = 750;
		double mean = min + (max - min) / 10;
		double standardDeviation = 20; // 标准差
		int numberOfRandomNumbers = 100; // 生成随机数的数量

		List<Double> valList = new ArrayList<>();
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			//double v = random.nextGaussian();
			double v = Math.abs(random.nextGaussian());
			double randomNumber = mean + standardDeviation * v;
			if (randomNumber <= min) {
				randomNumber = min;
			} else if (randomNumber >= max) {
				randomNumber = max;
			}
			valList.add(randomNumber);
			System.out.println("随机数 " + (i + 1) + ": " + randomNumber + ". v :" + v);
		}
		valList.stream().sorted().forEach(System.out::println);
	}

	@org.junit.Test
	public void test06() {
		int numberOfRandomNumbers = 10; // 生成随机数的数量
		double lambda = Math.log(100 / 10) / numberOfRandomNumbers; // 计算指数分布的参数λ

		Random random = new Random();
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			double u = random.nextDouble(); // 生成一个0到1之间的随机数
			double x = Math.exp(-lambda * u); // 根据指数分布的公式计算对应的随机数
			double v = 10 + (1 - x) * (100 - 10); // 将计算出的随机数限制在10到100之间
			System.out.println("随机数 " + (i + 1) + ": " + v + "x :" + (1 - x));
		}
	}

}
