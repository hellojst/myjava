package com.stjia.javabase.leetcode;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;

import org.junit.validator.ValidateWith;

import com.stjia.javabase.leetcode.LeetCodeUtil.ListNode;
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
//		// TODO Auto-generated method stub
//		Object result;
//		CountSmallBefore before = new CountSmallBefore();
//		int[] a = new int[] {1,2,7,8,5};
//		before.countOfSmallerNumberII2(a);
//		
//		int[] b = new int[] {1,2,2};
//		result = LeetCodeUtil.removeDuplicates(b);
//		System.out.println(result);
//		String string = LeetCodeUtil.reverseString("hello");
//		string = LeetCodeUtil.reverseStr("a.");
//		System.out.println(string);
//		
//		int i = LeetCodeUtil.rob(new int[] {1,2,3,5});
//		i= LeetCodeUtil.rob2(new int[] {1,2,3,1});
//		System.out.println(i);
//		
////		Solution solution = new LeetCodeUtil.Solution(new int[] {1,2,3,4,5}); //静态 内部类用法
//		
//		Solution solution = new LeetCodeUtil().new Solution(new int[] {1,2,3,4,5}); //非静态   内部类用法
//		printArray(solution.shuffle());
//		printArray(solution.reset());
//		printArray(solution.shuffle());
//		
//		System.out.println(LeetCodeUtil.fizzBuzz(15));
//		System.out.println(LeetCodeUtil.isValid("(]"));
//		System.out.println(LeetCodeUtil.maxProfit(new int[] {1,9,6,9,1,7,1,1,5,9,9,9}));
		ListNode head = new ListNode(1);
		ListNode head2 = new ListNode(2);
		ListNode head3 = new ListNode(3);
		ListNode head4 = new ListNode(4);
		ListNode head5 = new ListNode(5);
		head.next =head2;
		head3.next = head4;
		head2.next = head3;
		head4.next = head5;
//		ListNode root = LeetCodeUtil.removeNthFromEnd(head, 2);
//		printNode(root);
//		int[] arr1 = new int[] {1,2,3,0,0,0};
//		int[] arr2 = new int[] {2,5,6};
//		LeetCodeUtil.merge(arr1, 3, arr2, 3);
//		printArray(arr1);
		
		
		int[] ins = new int[] {1,2,3,4,5,6,7};
//		LeetCodeUtil.rotate(ins, 2);
		LeetCodeUtil.rotateReserve(ins, 3);
		printArray(ins);
		
		int[] arr1 = new int[] {1,2,3,1};
		System.out.println(LeetCodeUtil.containsDuplicate(arr1));
		
		int[] arr2 = new int[] {2,2,1};
		System.out.println(LeetCodeUtil.singleNumber(arr2));
		
		System.out.println(LeetCodeUtil.isPalindrome("A man, a plan, a canal: Panama"));
//		printNode(LeetCodeUtil.reverseList(head));
		printNode(LeetCodeUtil.reverseListIte(head));
		
		int[] num1 = new int[] {1,2};
		int[] num2 = new int[] {1,1};
		printArray(LeetCodeUtil.intersect(num1, num2));
	}
	
	private static void printArray(int[] array) {
			for (int i : array) {
				System.out.print(i);
			}
			System.out.println();
	}

	private static void printNode(ListNode node) {
		System.out.print("[");
		while (node != null) {
			System.out.print(node.val);
			if (node.next != null) {
				System.out.print(",");
			}
			node = node.next;
		}
		System.out.print("]");
	}
	
}
