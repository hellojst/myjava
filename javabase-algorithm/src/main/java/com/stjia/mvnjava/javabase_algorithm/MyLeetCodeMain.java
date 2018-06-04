package com.stjia.mvnjava.javabase_algorithm;

import java.util.List;

import com.stjia.mvnjava.javabase_algorithm.LeetCodeUtils.ListNode;

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
		System.out.println(LeetCodeUtils.maxProfit4Once(new int[] { 1, 2 }));

		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l4 = new ListNode(4);
		l1.next = l2;
		l2.next = l4;
		
		ListNode l11 = new ListNode(1);
		ListNode l33 = new ListNode(3);
		ListNode l44 = new ListNode(4);
		l11.next = l33;
		l33.next = l44;
		
		printNode(LeetCodeUtils.mergeTwoLists(l1, l11));
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
