package com.stjia.javabase.leetcode;

/**
 * 
 * @author stjia
 * @time 2018年4月12日
 */
public class Maintest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object result;
		CountSmallBefore before = new CountSmallBefore();
		int[] a = new int[] {1,2,7,8,5};
		before.countOfSmallerNumberII2(a);
		
		int[] b = new int[] {1,2,2};
		result = LeetCodeUtil.removeDuplicates(b);
		System.out.println(result);
		String string = LeetCodeUtil.reverseString("hello");
		string = LeetCodeUtil.reverseStr("a.");
		System.out.println(string);
		
		int i = LeetCodeUtil.rob(new int[] {1,2,3,5});
		i= LeetCodeUtil.rob2(new int[] {1,2,3,1});
		System.out.println(i);
		
	}

}
