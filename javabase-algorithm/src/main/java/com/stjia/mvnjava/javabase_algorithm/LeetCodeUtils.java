package com.stjia.mvnjava.javabase_algorithm;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

/**
 * @author stjia
 * @date 2018年6月2日
 */
public class LeetCodeUtils {
	/**
	 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
	 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
	 * 2^31-1 = 2,147,483,647;  -2^31 = -2,147,483,648;
	 * 
	 * 解题思路： 进行字符串反转： 1.保存正负的标记位，用来确定进行反转的字串数组范围   2.反转后的字符串先转成long型判断是否越界 //也可对字符数组进行按位比较
	 * @param x
	 * @return
	 */
    public static int reverseInt(int x) {
        int result = 0;
        boolean flag = x >= 0 ? true : false;
		char[] splits = Integer.toString(x).toCharArray();
		int length = splits.length;
		int firstPos = flag ? 0 : 1;
        for(int index = 0; index < (length - firstPos) / 2; index++) {
        	char temp = splits[index + firstPos];
        	splits[index + firstPos] = splits[length - 1 - index];
        	splits[length - index -1] = temp;
        }
//        int i = firstPos;
//        for (; i < splits.length; i++) {
//			if (splits[i] != '0') {
//				break;
//			}
//		}
        
        long l = Long.parseLong(new String(splits));
        if (l >= Math.pow(2, 31)*(-1) && l <= (Math.pow(2, 31) - 1)) {
			return Integer.parseInt(new String(splits));
		}
        return result;
    }
    
    
    /**
     * m的n次幂
     * @param m
     * @param n
     * @return
     */
    public static float Mi(long m, int n) {
    	if (n > 0) {
    		return m << (n - 1);
		}else if (n == 0) {
			return m >> 1;
		} else {
			return 1.0f / (m << (-1 - n));
		}
    }
    
    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int sl = ss.length, tl = ts.length;
        if (sl != tl) {
			return false;
		}
        int lastIndex = ss.length - 1;
		for (int i = 0; i < ts.length; i++) {
			for(int j = 0; j <= lastIndex; j++) {
				if (j == lastIndex && ts[i] != ss[lastIndex]) {
					return false;
				}
				if (ts[i] == ss[j]) {
					char temp = ss[j];
					ss[j] = ss[lastIndex];
					ss[lastIndex] = temp;
					lastIndex--;
					break;
				}
			}
		}
		if (lastIndex >= 0) {
			return false;
		}
		return true;
    }
    
    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

注意你不能在买入股票前卖出股票。
	 *
	 * 分治思想 转化为利润数组，则为求最大连续子串
     * @param prices
     * @return
     */
    public static int maxProfit4Once(int[] prices) {
    	if (prices == null || prices.length < 2) {
			return 0;
		}
    	int[] profits = new int[prices.length - 1]; //每天之间的利润数组
    	for (int i = 0; i < profits.length; i++) {
			profits[i] = prices[i + 1] - prices[i];
		}
    	
    	int temp = 0;
    	int maxprofits = 0;
    	for(int i = 0; i < profits.length; i++) {
    		if (temp + profits[i] > 0)  temp = temp + profits[i]; // 如果加上下一天仍是获利的则可继续此连续子串
			else temp = 0; // 如果加上下一天后子串和为负，则此子串没有往下加的必要了，重置子串开始新的子串
    		
    		maxprofits = Math.max(temp, maxprofits);
				
    	}
    	
    	return maxprofits;
    }

    /**
     * 非递归算法
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode newhead = null;
    	if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		} 
    	if (l1.val > l2.val) {
			newhead = l2;
			l2 = l2.next;
		} else {
			newhead = l1;
			l1 = l1.next;
		}
    	ListNode l = newhead;
        while(l1 != null || l2 != null) {
        	if (l1 == null) {
				l.next = l2;
				l = l.next;
				l2 = l2.next;
			} else if(l2 == null) {
				l.next = l1;
				l = l.next;
				l1 = l1.next;
			} else if (l1.val > l2.val) {
				l.next = l2;
				l = l.next;
				l2 = l2.next;
			} else {
				l.next = l1;
				l = l.next;
				l1 = l1.next;
			}
        }
        return newhead;
    }
    
    /**
     * 合并两个有序链表的递归实现
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoListsRecursion(ListNode l1, ListNode l2) {
    	if (l1 == null) return l2;
    	if (l2 == null) return l1;
    	ListNode newhead = null;
    	if (l1.val > l2.val) {
    		newhead = l2;
    		newhead.next = mergeTwoLists(l1, l2.next);
    	} else {
    		newhead = l1;
    		newhead.next = mergeTwoLists(l1.next, l2);
    	}
    	return newhead;
    }
    
	static class ListNode {
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

	
}
