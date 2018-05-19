package com.stjia.javabase.leetcode;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;

import org.junit.validator.ValidateWith;

import com.stjia.javabase.leetcode.LeetCodeUtil.Solution;

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
		
//		Solution solution = new LeetCodeUtil.Solution(new int[] {1,2,3,4,5}); //静态 内部类用法
		
		Solution solution = new LeetCodeUtil().new Solution(new int[] {1,2,3,4,5}); //非静态   内部类用法
		printArray(solution.shuffle());
		printArray(solution.reset());
		printArray(solution.shuffle());
		
		System.out.println(LeetCodeUtil.fizzBuzz(15));
	}
	
	private static void printArray(int[] array) {
			for (int i : array) {
				System.out.print(i);
			}
			System.out.println();
	}

}
