package com.stjia.mvnjava.javabase_algorithm;

import java.util.List;

import com.stjia.mvnjava.javabase_algorithm.LeetCodeUtils.ListNode;

/**
 * @author stjia
 * @date 2018年6月2日
 */
public class MyLeetCodeMain {
	public static void main(String[] args) {
		/**
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
		char[][] shudu = new char[][] {
			  {'5', '3', '.', '.', '7', '.',  '.', '.', '.'},
			  {'6', '.', '.', '1', '9', '5', '.', '.',  '.'},
			  {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
			  {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
			  {'4','.','.','8','.','3','.','.','1'},
			  {'7','.','.','.','2','.','.','.','6'},
			  {'.','6','.','.','.','.','2','8','.'},
			  {'.','.','.','4','1','9','.','.','5'},
			  {'.','.','.','.','8','.','.','7','9'}
		};
		System.out.println(LeetCodeUtils.isValidSudoku(shudu));
		System.out.println(LeetCodeUtils.myAtoi("   -000231l1361065549"));
		System.out.println(LeetCodeUtils.myAtoiRegex("   -00020031l1361065549"));
		System.out.println(LeetCodeUtils.hammingWeight(125));
		System.out.println(LeetCodeUtils.countPrimes(10));
		int[] arrays = new int[] {0,1,0,3,12};
		LeetCodeUtils.moveZeroes(arrays);
		printArray(arrays);
		*/
		System.out.println(LeetCodeUtils.countAndSay(4));
//		int[] testarray = new int[] {0, 0 , 0};
//		int[] testarray = new int[] {-1, 0, 1, 2, -1, -4};
		int[] testarray = new int[] {-1,-1,-1,1};
		List<List<Integer>> ressults = LeetCodeUtils.threeSum(testarray);
		System.out.println(LeetCodeUtils.canJumpGreed(new int[] {5,9,3,2,1,0,2,3,3,1,0,0}));
	}

	private static void printArray(int[] array) {
		System.out.print("[");
		for(int i = 0; i < array.length; i++) {
			System.out.print(i);
			if (i != array.length - 1) {
				System.out.print(",");
			}
		}
		System.out.println("]");
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
