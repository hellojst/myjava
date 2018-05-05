package com.stjia.javabase.collector;

import java.util.LinkedHashMap;
import java.util.concurrent.FutureTask;

/**
 * @author stjia
 * @date 2018年4月24日
 */
public class TestMain {
	public static void main(String[] args) {
		LinkedHashMap<Integer, String> map = new LinkedHashMap<>(new MapData<>(new MapDataGenerator(), 15));
		System.out.println(map);
	}
}
