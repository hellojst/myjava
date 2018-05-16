package com.stjia.javabase.lintcode;

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
		result = LintCodeUtil.removeDuplicates(b);
		System.out.println(result);
		String string = LintCodeUtil.reverseString("hello");
		string = LintCodeUtil.reverseStr("a.");
		System.out.println(string);
	}

}
