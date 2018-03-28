package com.stjia.javabase.lamda;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author stjia
 * @time 2018年3月28日
 */
public class TestLamda {
	public static void main(String[] args) {
		List<Apple> apples = Arrays.asList(new Apple(156, "red"), new Apple(140, "blue"), new Apple(178, "red"));
		List<Apple> myList = MyFilter.filter(apples, (Apple apple) -> apple.getWeight()>150 && apple.getColor().equals("red"));
		System.out.println(myList);
		apples.sort((Apple apple1, Apple apple2) -> apple1.getWeight()-apple2.getWeight());
		System.out.println(apples);
		new Thread(() -> System.out.println("匿名内部类Runnable的run方法体的内容可以直接放在这里！")).start();
	}

}
