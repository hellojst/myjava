package com.stjia.mvnjava.javabase_algorithm;

/**
 * @author stjia
 * @date 2018年6月2日
 */
public class MyLeetCodeMain {
	public static void main(String[] args) {
		double d = LeetCodeUtils.Mi(2, -2);
		
		System.out.println(d);
		
		System.out.println(Integer.toString(-214).toCharArray());
		System.out.println(LeetCodeUtils.reverseInt(-2100));
		System.out.println(LeetCodeUtils.isAnagram("anagram", "nagaram"));
		System.out.println(LeetCodeUtils.maxProfit4Once(new int[] {1,2}));
	}
}
