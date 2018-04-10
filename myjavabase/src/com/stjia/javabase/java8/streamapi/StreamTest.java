package com.stjia.javabase.java8.streamapi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

/**
 * 
 * @author stjia
 * @time 2018年4月2日
 */
public class StreamTest {
	
	public static void main(String[] args) {
		List<AppleBean> list = Arrays.asList(new AppleBean(100, "red"), 
				new AppleBean(145, "blue"),new AppleBean(112, "green"));
		List<String> names = list.parallelStream().filter(a -> a.getWeight() > 101).sorted((a,b)->a.getWeight() - b.getWeight()).map(AppleBean::getColor).collect(toList());
		List<String> names2 = list.parallelStream().filter(a -> a.getWeight() > 101).sorted(comparing(AppleBean::getWeight)).map(AppleBean::getColor).collect(toList());
		List<AppleBean> names3 = list.parallelStream().filter(a -> a.getWeight() > 101).sorted(comparing(AppleBean::getWeight)).collect(toList());
		printStr(names);
		printStr(names2);
	}
	
	public static void printStr(List<String> strs) {
		for (String string : strs) {
			System.out.println(string);
		}
	}

}
