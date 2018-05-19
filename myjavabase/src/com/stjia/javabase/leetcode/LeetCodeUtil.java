package com.stjia.javabase.leetcode;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author stjia
 * @time 2018年4月12日
 */
public class LeetCodeUtil {

	/**
	 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
	 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。 数组顺序不变 不需要考虑数组中超出新长度后面的元素。
	 * 
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int j = 0;
		for (int i : nums) {
			if (j == 0 || i > nums[j - 1]) {
				nums[j++] = i;
			}
		}
		return j;
	}

	/**
	 * 请编写一个函数，其功能是将输入的字符串反转过来。
	 * 
	 * @param s
	 * @return
	 */
	public static String reverseString(String s) {
		if (s.length() == 1) {
			return s;
		}
		int index = s.length() / 2;
		String s1 = s.substring(0, index);
		String s2 = s.substring(index, s.length());
		return reverseString(s2) + reverseString(s1);
	}

	/**
	 * 请编写一个函数，其功能是将输入的字符串反转过来。
	 * 
	 * @param s
	 * @return
	 */
	public static String reverseStr(String s) {
		if (s.isEmpty()) {
			return s;
		}
		char[] ss = s.toCharArray();
		int length = ss.length;
		int mid = length / 2;
		for (int i = 0; i < mid; i++) {
			char temp = ss[i];
			ss[i] = ss[length - i - 1];
			ss[length - i - 1] = temp;
		}
		return new String(ss);
	}

	/**
	 * 方法一 打家劫舍： 递归会超时 因为每次递归都有大量的重复计算 f(0)=array(0); f(1)=max(array[0],array[1])
	 * ... f(n)=max(array[n]+f(n-2), f(n-1)))
	 * 
	 * @param nums
	 * @return
	 */
	public static int rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		return countGold(nums, nums.length - 1);
	}

	private static int countGold(int[] nums, int n) {
		if (n == 0)
			return nums[0];
		if (n == 1)
			return Math.max(nums[0], nums[1]);
		else {
			return Math.max(nums[n] + countGold(nums, n - 2), countGold(nums, n - 1));
		}
	}

	/**
	 * 动态规划 方法二 打家劫舍： 采用迭代 思想与方法已同，方式与递归相反， 递归是倒着来的 n-->0; 迭代是 0-->n
	 * 
	 * @param nums
	 * @return
	 */
	public static int rob2(int[] nums) {
		// 先处理特殊情况
		if (nums.length == 0)
			return 0;
		if (nums.length == 1)
			return nums[0];

		// a为f(n-2), b为f(n-1):即前n-1的最大值
		int a = nums[0], b = Math.max(nums[1], a);
		for (int i = 2; i < nums.length; i++) { // i为array[n]
			// 以下三行，为此迭代结束更新值为下次迭代做准备； 整体化
			int temp = b;
			b = Math.max(a + nums[i], b);
			a = temp;
		}
		return b;
	}

	/**
	 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾的）节点，您将只被给予要求被删除的节点
	 * 
	 * @param node
	 */
	public static void deleteNode(ListNode node) {
		ListNode node1 = node.next;
		node.next = node.next.next;
		node.val = node1.val;
		node1.next = null;
	}

	/**
	 * 给定一个二叉树，找出其最大深度。
     *   二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
	 * @param root
	 * @return
	 */
	public static int maxDepth(TreeNode root) {
		return count(root);
	}

	private static int count(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(count(root.left) + 1, count(root.right) + 1);
	}

    public static List<String> fizzBuzz(int n) {
        List<String> output = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
        	String temp = "";
        	if(isFizz(i)) temp += "Fizz";
        	if(isBuzz(i)) temp += "Buzz";
        	output.add(temp.isEmpty() ? (i + "") : temp);
        }
        return output;
    }
	
    private static boolean isFizz(int n) {
    	return n%3 == 0;
    }
	
    private static boolean isBuzz(int n) {
    	return n%5 == 0;
    }
	
	private static class ListNode {
		int val;
		ListNode next;

		@SuppressWarnings("unused")
		ListNode(int x) {
			val = x;
		}
	}

	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	 class Solution {

		private int[] nums;
	    public Solution(int[] nums) {
	        this.nums = nums;
	    }
	    
	    /** Resets the array to its original configuration and return it. */
	    public int[] reset() {
	        return nums;
	    }
	    
	    /** Returns a random shuffling of the array. */
	    public int[] shuffle() {
	        int[] shuffleArray = nums.clone();
	        //类比洗牌的方法，每个位置都进行移动
	        Random random = new Random();
	        int length = shuffleArray.length;
	        for (int i = 0; i < length; i++) {
				int newPosition = random.nextInt(length); //随机产生新位置
				int temp = shuffleArray[i];
				shuffleArray[i] = shuffleArray[newPosition];
				shuffleArray[newPosition] = temp;
			}
	        
	        return shuffleArray;
	    }
	}

}
