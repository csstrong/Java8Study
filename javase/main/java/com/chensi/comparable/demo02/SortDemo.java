package com.chensi.comparable.demo02;

import java.util.*;

/*
 * @author  chensi
 * @date  2023/1/5
 */
public class SortDemo {
	public static void main(String[] args) {
		//模拟不使用比较器的sort方法
		//Demo1();
		//模拟使用比较器的sort方法
		Demo3();
	}

	private static void Demo1() {
		ArrayList<Person> al = new ArrayList<Person>();
		al.add(new Person("lisi", 24));
		al.add(new Person("wangwu", 25));
		al.add(new Person("zhangsan", 23));
		al.add(new Person("chenqi", 27));
		al.add(new Person("zhaoliu", 26));

		System.out.println("排序前：" + al);
		MyCollections.sort(al);
		System.out.println("排序后：" + al);
	}

	private static void Demo3() {
		ArrayList<Student> al = new ArrayList<Student>();
		al.add(new Student("lisi", 24));
		al.add(new Student("wangwu", 25));
		al.add(new Student("zhangsan", 23));
		al.add(new Student("chenqi", 27));
		al.add(new Student("zhaoliu", 26));

		System.out.println("排序前：" + al);
		MyCollections_1.sort(al, new ComparatorByLength());
		//Collections.sort(al, new ComparatorByLength());
		System.out.println("排序后：" + al);
	}
}

/**
 * 使用自定义Collection类
 */
class MyCollections {
	public static <T extends Comparable<? super T>> void sort(List<T> list) {
		for (int i = 0; i <= list.size() - 2; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).compareTo(list.get(j)) > 0) {
					T t = list.get(i);
					list.set(i, list.get(j));
					list.set(j, t);
				}
			}
		}
	}
}

class MyCollections_1 {
	public static <T> void sort(List<T> list, Comparator<? super T> c) {
		Object[] a = list.toArray();
		Arrays.sort(a, (Comparator) c);
		ListIterator i = list.listIterator();
		for (int j = 0; j < a.length; j++) {
			i.next();
			i.set(a[j]);
		}
	}
}

class ComparatorByLength implements Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		int temp = o1.getName().length() - o2.getName().length();
		return temp == 0 ? o1.getName().compareTo(o2.getName()) : temp;
	}
}

class Person implements Comparable<Person> {

	private String name;
	private int age;

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]\n";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person() {
		super();
	}

	@Override
	public int compareTo(Person o) {
		int temp = this.age - o.getAge();
		return temp == 0 ? this.name.compareTo(o.getName()) : temp;
	}
}

class Student extends Person {
	public Student() {
		super();
	}

	public Student(String name, int age) {
		super(name, age);
	}
}

class Worker extends Person {
	public Worker() {
		super();
	}

	public Worker(String name, int age) {
		super(name, age);
	}
}

