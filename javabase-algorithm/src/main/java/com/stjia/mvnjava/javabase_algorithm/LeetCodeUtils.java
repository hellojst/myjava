package com.stjia.mvnjava.javabase_algorithm;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author stjia
 * @date 2018年6月2日
 */
public class LeetCodeUtils {
	/**
	 * 给定一个 32 位有符号整数，将整数中的数字进行反转。 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31 −
	 * 1]。根据这个假设，如果反转后的整数溢出，则返回 0。 2^31-1 = 2,147,483,647; -2^31 = -2,147,483,648;
	 * 
	 * 解题思路： 进行字符串反转： 1.保存正负的标记位，用来确定进行反转的字串数组范围 2.反转后的字符串先转成long型判断是否越界
	 * //也可对字符数组进行按位比较
	 * 
	 * @param x
	 * @return
	 */
	public static int reverseInt(int x) {
		int result = 0;
		boolean flag = x >= 0 ? true : false;
		char[] splits = Integer.toString(x).toCharArray();
		int length = splits.length;
		int firstPos = flag ? 0 : 1;
		for (int index = 0; index < (length - firstPos) / 2; index++) {
			char temp = splits[index + firstPos];
			splits[index + firstPos] = splits[length - 1 - index];
			splits[length - index - 1] = temp;
		}
		// int i = firstPos;
		// for (; i < splits.length; i++) {
		// if (splits[i] != '0') {
		// break;
		// }
		// }

		long l = Long.parseLong(new String(splits));
		if (l >= Math.pow(2, 31) * (-1) && l <= (Math.pow(2, 31) - 1)) {
			return Integer.parseInt(new String(splits));
		}
		return result;
	}

	/**
	 * m的n次幂
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static float Mi(long m, int n) {
		if (n > 0) {
			return m << (n - 1);
		} else if (n == 0) {
			return m >> 1;
		} else {
			return 1.0f / (m << (-1 - n));
		}
	}

	/**
	 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
	 * 
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
			for (int j = 0; j <= lastIndex; j++) {
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
	 * 
	 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
	 * 
	 * 注意你不能在买入股票前卖出股票。
	 *
	 * 分治思想 转化为利润数组，则为求最大连续子串
	 * 
	 * @param prices
	 * @return
	 */
	public static int maxProfit4Once(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int[] profits = new int[prices.length - 1]; // 每天之间的利润数组
		for (int i = 0; i < profits.length; i++) {
			profits[i] = prices[i + 1] - prices[i];
		}

		int temp = 0;
		int maxprofits = 0;
		for (int i = 0; i < profits.length; i++) {
			if (temp + profits[i] > 0)
				temp = temp + profits[i]; // 如果加上下一天仍是获利的则可继续此连续子串
			else
				temp = 0; // 如果加上下一天后子串和为负，则此子串没有往下加的必要了，重置子串开始新的子串

			maxprofits = Math.max(temp, maxprofits);

		}

		return maxprofits;
	}

	/**
	 * 非递归算法 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
	 * 
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
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				l.next = l2;
				l = l.next;
				l2 = l2.next;
			} else if (l2 == null) {
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
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode mergeTwoListsRecursion(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
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

	/**
	 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。 一个二叉搜索树具有如下特征： 节点的左子树只包含小于当前节点的数。
	 * 节点的右子树只包含大于当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树。 一个二叉搜索树具有如下特征： 节点的左子树只包含小于当前节点的数。
	 * 节点的右子树只包含大于当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树。
	 * 
	 * 该特质二叉树中序遍历的结果应为有序的 也可以保存下来前一个值，每次比较当前值与中序遍历的前一值大小关系，有错就返回不在继续
	 * 
	 * @param root
	 * @return
	 */
	public static boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;

		List<TreeNode> nodes = treeNodes(root, new ArrayList<TreeNode>());
		int length = nodes.size();
		for (int i = 0; i < length - 1; i++) {
			if (nodes.get(i).val >= nodes.get(i + 1).val) {
				return false;
			}
		}
		return true;
	}

	private static List<TreeNode> treeNodes(TreeNode node, List<TreeNode> nodeList) {
		if (node == null)
			return new ArrayList<TreeNode>();
		if (node.left != null) {
			nodeList = treeNodes(node.left, nodeList);
		}
		nodeList.add(node);
		if (node.right != null) {
			nodeList = treeNodes(node.right, nodeList);
		}
		return nodeList;

	}

	/**
	 * 有效的数独 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。 数字 1-9 在每一行只能出现一次。 数字 1-9
	 * 在每一列只能出现一次。 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
	 * 
	 * @param board
	 * @return 解题思路：不用取每一个数验证其是否有横纵和块中有重复的，会有大量的重复验证 1.验证每一行是否有重复值 ：有就不可能是数独
	 *         2.验证每一列是否有重复值 ：有就不可能是数独 3.验证每一个3x3的块是否有重复值 ：有就不可能是数独
	 */
	public static boolean isValidSudoku(char[][] board) {
		// 验证行
		for (int i = 0; i < 9; i++) { // 行 row
			List<Character> cols = new ArrayList<Character>();
			for (int j = 0; j < 9; j++) {
				char ch = board[i][j];
				if (isNumberChar(ch)) {
					if (!cols.isEmpty() && cols.contains(ch)) {
						return false;
					} else {
						cols.add(ch);
					}
				}
			}
		}

		// 验证列
		for (int i = 0; i < 9; i++) { // 列 col
			List<Character> cols = new ArrayList<Character>();
			for (int j = 0; j < 9; j++) {
				char ch = board[j][i];
				if (isNumberChar(ch)) {
					if (!cols.isEmpty() && cols.contains(ch)) {
						return false;
					} else {
						cols.add(ch);
					}
				}
			}
		}

		boolean validateResult = true;
		// 验证每一个立方块
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				validateResult = validateResult && validateBlock(i, j, board);
			}
		}
		return validateResult;
	}

	private static boolean isNumberChar(char ch) {
		return ch >= '0' && ch <= '9';
	}

	private static boolean validateBlock(int i, int j, char[][] board) {
		List<Character> chars = new ArrayList<Character>();
		for (int k = 0; k < 3; k++) {
			for (int t = 0; t < 3; t++) {
				char ch = board[i * 3 + k][j * 3 + t];
				if (!isNumberChar(ch)) {
					continue;
				}
				if (!chars.isEmpty() && chars.contains(ch)) {
					return false;
				} else {
					chars.add(ch);
				}
			}
		}
		return true;
	}

	public static int myAtoi(String str) {
		int min = (int) (Math.pow(2, 31)*(-1));
		int max = (int) (Math.pow(2, 31) - 1);
		int result = 0;
		int index = 0;
		boolean isNumber = false;
		char[] chs = str.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			if (volidateChar(chs[i]) && !isNumber) {
				index = i;
				isNumber = true;
			} else if(chs[i] >= '0' && chs[i] <= '9') {
				isNumber = true;
				index = i;
			} else if (chs[i] == ' ' && !isNumber) {
				continue;
			} else {
				break;
			}
		}
		if (index == 0 && !isNumber) {
			return 0;
		}
		String sub = str.substring(0, index + 1);
		sub = sub.trim();
		if (sub.length() == 1) {
			if (sub.charAt(0) < '0' || sub.charAt(0) > '9') {
				return 0;
			}
		}
		if (sub.isEmpty()) {
			return 0;
		}
		int indexChar = 0;
		char opch = 0;
		for(int i = 0; i <  sub.length(); i++) {
			if (sub.charAt(i) == '0') {
				indexChar = i;
				continue;
			} else  if (volidateChar(sub.charAt(i))) {
				opch = sub.charAt(i);
			}else {
				indexChar = i;
				break;
			}
		}
		sub = (opch + sub.substring(indexChar)).trim();
		if (sub.length() > 12) {
			if (sub.contains("-")) {
				return min;
			}
			return max;
		}

		long longvalue = Long.parseLong(sub);
		if (longvalue > max) {
			return max;
		} else if (longvalue < min) {
			return min;
		} else {
			return (int) longvalue;
		}
	}

	private static boolean volidateChar(char ch) {
		if (ch == '-')
			return true;
		if (ch == '+')
			return true;
		return false;
	}

	/**
	 * 在找到第一个非空字符之前，需要移除掉字符串中的空格字符。如果第一个非空字符是正号或负号，选取该符号，
	 * 并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。如果第一个非空字符是数字，
	 * 则直接将其与之后连续的数字字符组合起来，形成整数。
	 * @param str
	 * @return
	 */
	public static int myAtoiRegex(String str) {
		int min = (int) (Math.pow(2, 31)*(-1));
		int max = (int) (Math.pow(2, 31) - 1);
		int result = 0;
		String regex = "\\s*([-+]?\\d+)*.*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			String value = matcher.group(1);
			String matchZeroRegex = "[-+]*(0*)\\w*";
			Matcher zeroMatcher = Pattern.compile(matchZeroRegex).matcher(value);
			
			if (zeroMatcher.find()) {
				String groupstr = zeroMatcher.group(1);
			    value = value.replaceFirst(groupstr, "");
			}
			if (value.length() > 12) {
				if (value.contains("-")) {
					return min;
				}
				return max;
			}

			long longvalue = Long.parseLong(value);
			if (longvalue > max) {
				return max;
			} else if (longvalue < min) {
				return min;
			} else {
				return (int) longvalue;
			}

		}
		return result;
	}

	/**
	 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 
	 * 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
	 * @param haystack
	 * @param needle
	 * @return
	 */
    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
			return 0;
		}
        char[] haychs = haystack.toCharArray();
        char[] neechs = needle.toCharArray();
        int chylen = haychs.length;
        int neelen = neechs.length;
        for(int i = 0; i < chylen; i++) {
        	if (neechs[0] != haychs[i]) {
        		continue;
			}
        	//表示每一位是否都相等，遇到不相等的就返回false跳出循环
        	boolean validate = true;
        	for(int j = 0; j < neelen; j++) {
        		//如果超出第一个字符范围，则为匹配不了返回-1；
        		if (i + j >= chylen) {
					return -1;
				}
        		if (neechs[j] == haychs[i + j]) {
					continue;
				} else {
					validate = false;
					break;
				}
        	}
        	if (validate) {
				return i;
			}
        }
        return -1;
    }
	
    /**
     * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
     * @param n
     * @return
     */
    public static int hammingWeight(int n) {
    	String bin = Integer.toBinaryString(n);
    	char[] chs = bin.toCharArray();
    	int sum = 0;
    	for(int i = 0; i < chs.length; i++) {
    		if (chs[i] == '1') {
				sum++;
			}
    	}
    	
    	return sum;
    }
    
    /**
     * 超出时间限制
     * 统计所有小于非负整数 n 的质数的数量。
     * @param n
     * @return
     */
    public static int countPrimesTimeLimit(int n) {
    	n--;
    	if (n < 2) {
			return 0;
		}
        int sum = 0;
        while(n >= 2) {
        	boolean b = isPrimes(n);
//        	boolean b = volidatePrimes(n);
        	if (b) {
        		System.out.print(n);
				sum++;
			}
        	n--;
        }
        return sum;
    }
    
    private static boolean volidatePrimes(int n) {
    	int m = n - 1;
    	while(n > 1 && m > 1) {
    		if (n % m == 0) {
				return false;
			}
    	}
    	return true;
    }
    
    //一个数如果在遍历到它的时候都没有被标记，则证明在1~m-1的区间内没有能够整除它的数，所以这个数一定是一个质数
   // - 只需遍历1~√n，大于√n的数如果没有遍历到一定是质数，且也不需要将后续的倍数标记，因为√n*√n >= n
    private static boolean isPrimes(int n){
    	for(int i = 2; i <= Math.sqrt(n); i++) {
    		if (n % i == 0) return false;
    	}
    	return true;
    }
    
    /**
     * 用一张Boolean型表去记录计算过的数哪些是质数哪些是偶数，先查表在计算
     * @return
     */
    public static int countPrimes(int n){
    	//boolean 默认为false；  若非质数改为true
    	boolean[] booltable = new boolean[n + 1];
    	int sum = 0;
    	for(int i = 2; i < n; i++) {
    		if (!booltable[i] && isPrimes(i)) {
				sum++;
				continue;
			}
    		for (int j = i; j < n; j = j + i) {
				booltable[j] = true;
			}
    	}
    	return sum;
    }
    
    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 思路：设一个前置指针（标记位）指向第一个0，非0就往后顺移直到找到第一个0并停止等待非0值来占据此位置；这样每
     * 个非0值就只移动一次，避免了频繁移动
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
    	int lenth = nums.length;
    	int position = 0; //记录第一个0的位置
        for(int i = 0; i < lenth; i++) {
        	if (nums[i] == 0) {
				continue;
			}
        	//i 位置前没有0 则position跟着往后移即可
        	if (nums[i] != 0 && i == position) {
				position++;
        	// 遇到非0值且其前有0值就将该值放到position位置
        	} else if (nums[i] != 0 && i > position) { 
				nums[position] = nums[i];
				nums[i] = 0;
				position++;
			} 
        	
        }
    }
    
    /**
     * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。同样的元素不能被重复利用。
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
        	int neededValue = target - nums[i];
        	for(int j = i + 1; j < nums.length; j++) {
        		if (neededValue == nums[j]) {
					return new int[] {i, j};
				}
        	}
        }
        return null;
    }
    
    public static String countAndSay(int n) {
    	String desStr = "1";
        for(int i = 2; i <= n; i++) {
        	desStr = composeStr(desStr.toCharArray());
        }
        return desStr;
    }
    
    private static String composeStr(char[] chs) {
    	StringBuilder stringBuilder = new StringBuilder();
    	List<Character> chList = new ArrayList<Character>();
    	for(int i = 0; i < chs.length; i++) {
    		if (chList.contains(chs[i])) {
				chList.add(chs[i]);
			} else {
				if (!chList.isEmpty()) {
					stringBuilder.append(chList.size() + "" + chList.get(0));
				}
				chList.clear();
				chList.add(chs[i]);
			}
    	}
    	if (!chList.isEmpty()) {
    		stringBuilder.append(chList.size() + "" + chList.get(0));
		}
    	return stringBuilder.toString();
    }
    
    /**
     * 思路 先排序 在查找   和为0 即前两项的和 与 第三项 在0 的侧， 最小值在负数区取， 最大值在正数区取，  第一个与第二个值的和需小于等于0；
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
注意：答案中不可以包含重复的三元组。
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	if (nums.length < 3) {
			return results;
		}
        sortArray(nums, 0, nums.length - 1);
        int sepratorPostion = firstPositiveNum(nums); //正负分界点(含0)
        if (sepratorPostion == nums.length) {
			return results;
		}
        Set<List<Integer>> sets = new HashSet<List<Integer>>();
        for (int i = 0; i <= sepratorPostion; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] > 0) {
					break;
				}
				int needed = 0 - (nums[i] + nums[j]);
				int firstPosition = sepratorPostion > j ? sepratorPostion : j + 1;
				if (firstPosition > nums.length - 1) {
					break;
				}
				int postion = binarySearch(nums, needed, firstPosition);
				if (postion != -1) {
					List<Integer> result = new ArrayList<Integer>();
					result.add(nums[i]);
					result.add(nums[j]);
					result.add(nums[postion]);
					sets.add(result);
				}
			}
		}
        results.addAll(sets);
        return results;
    }
    
    /**
     * 排序
     * @param nums
     * @param lo
     * @param hi
     */
    private static void sortArray(int[] nums, int lo, int hi) {
    	if (lo >= hi) {
			return;
		}
    	int partition = partition(nums, lo, hi);
    	sortArray(nums, lo, partition - 1);
    	sortArray(nums, partition + 1, hi);
    }
    
    /**
     * 快速排序的核心算法
     * @param nums
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(int[] nums, int lo, int hi) {
    	//参考值
    	int value = nums[lo]; 
    	int i = lo, j = hi + 1;
    	while(true) {
    		while(nums[++i] < value) {
    			if(i >= hi) break;
    		}
    		while(nums[--j] > value) {
    			if(lo >= j) break;
    		}
    		if (i >= j) {
				break;
			}
    		int temp = nums[i]; //一个是先自增在取lo
    		nums[i] = nums[j]; 
    		nums[j] = temp;
    	}
    	int temp = nums[j];
    	nums[j] = nums[lo];
    	nums[lo] = temp;
    	return j; 
    }
    
    /**
     * 二分查找， 
     * @param nums
     * @param parm 待查找对象
     * @param positon 查找的起始位置
     * @return 找到的对象的索引
     */
    private static int binarySearch(int[] nums, int parm, int positon) {
    	int lo = positon, hi = nums.length - 1;
    	int middle = (lo + hi) / 2;
    	if (nums[lo] == parm) {
			return lo;
		}
    	if (nums[hi] == parm) {
			return hi;
		}
    	while (lo < middle && middle < hi) {
			if (nums[middle] > parm) {
				hi = middle;
				middle = (hi + lo) / 2;
			} else if (nums[middle] < parm) {
				lo = middle;
				middle = (hi + lo) / 2;
			} else {
				return middle;
			}
		}
    	return -1;
    } 
    
    /**
     * 记录 第一个非负数的位置  用于分区域查找
     * @param nums
     * @return
     */
    private static int firstPositiveNum(int[] nums) {
    	int lo = 0;
    	int hi = nums.length - 1;
    	int middle = (hi + lo) / 2;
    	if (nums[0] >= 0) {
    		return 0;
    	}
    	if (nums[hi] <= 0) {
			return hi;
		}
    	while (true) {
    		
			if (nums[middle] >= 0) {
				if ((middle - 1 >= 0 && nums[middle - 1] < 0) || middle < 1) {
					return middle;
				}
				
				if (nums[middle - 1] >= 0) {
					hi = middle;
					middle = (hi + lo) / 2;
				}
			} else if (nums[middle] < 0) {
				if (middle + 1 <= hi && nums[middle + 1] >= 0) {
					return middle + 1;
				}
				lo = middle;
				middle = (hi + lo) / 2;
			}
		}
    }
    
    /**
     * 跳跃游戏
     * 从后往前遍历，给每一个位置设置一个标志位，表示能否通过这个位置到达终点。这个标志位的设置，
     * 由这个点能否通过排在它后面的点到达终点。有两种可能性：一种是从该点可以直接到达终点，
     * 另一种是通过排在后面的标志位为true的点到达终点。
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int lenth = nums.length;
        boolean[] isCanJumps = new boolean[lenth];
        isCanJumps[lenth - 1] = true;
        //从后往前计算每个点是否可以跳到最后一步，以前面所得到的boolean可最终得到第一个位置是否可以跳到最后一个位置
        for(int i = lenth - 2; i >= 0; i--) {
        	if (nums[i] >= lenth - 1 - i) {
				isCanJumps[i] = true;
				continue;
			}
        	boolean isCanjump = false;
        	for (int j = 1; j <= nums[i]; j++) {
				if (isCanJumps[i + j]) {
					isCanjump = true;
					break;
				}
			}
        	isCanJumps[i] = isCanjump;
        }
        return isCanJumps[0];
    }
    
    /**
     * 跳跃游戏 ---- 贪心算法
     *  思路：贪心算法; 
     * 从第一个数开始, 寻找可以一个可以跳最远的点; 
     * 例1：3 1 2 4 1 0 0 
     * 1.从第一个位置0,可以跳到位置1和位置2和位置3; 
     * 2.如果跳到位置1,那么最远就可以跳到位置(1+1); 
     * 3.如果跳到位置2,那么最远就可以跳到位置(2+2); 
     * 4.如果跳到位置3,那么最远就可以跳到位置(3+4); 
     * 5.故选择跳到位置3 ,重复1.2.3步; 
     *  
     * 算法分析： 
     * 1.如果选择跳到位置3 ,就无法跳到位置2和位置3, 那么会不会因此错过最优解？ 答：不会！ 
     * 2.因为任意位置1和位置2能到达的位置, 位置3都可以到达; 
     * 3.故不会错过最优解; 
     * @return
     */
    public static boolean canJumpGreed(int[] nums) {
    	// value为当前位置值， left为左边界，即能达到最远处的下标值，即i的最优移动位置（局部最优解）
    	// right为该右边界，及某点所能达到的最远距离，即局部最优解的判断条件，也可判断可达最远距离
    	int value, left = 0, right = 0;
    	for(int i = 0; i < nums.length - 1 && nums[i] != 0 && right < nums.length - 1; ) {
    		value = nums[i];
    		right = 0;
    		for(int j = 1; j <= value && i + j < nums.length; j++) {
    			int position = i + j;
    			int canReachPosition = position + nums[position];
    			if (canReachPosition > right) {
					left = position;
					right = canReachPosition;
				}
    		}
    		i = left;
    	}
    	//通过最后得到的最远可达距离与nums的长度对比得出是否可达
    	if (right < nums.length - 1) {
			return false;
		} else {
			return true;
		}
    }
    
//    /**
//     * 差不多需三次两两交换两个边一个斜线
//     * @param matrix
//     */
//    public static void rotate(int[][] matrix) {
//    	if (matrix == null || matrix.length == 0) {
//			return;
//		}
//        int col = matrix.length;
//        int row = matrix[0].length;
//        for(int i = 0; i < col / 2; i++) {
//        	for(int j = i; j < row - i; j++) {
//        		int temp = matrix[i][j];
//        		matrix[i][j] = matrix[col - j - 1][i];
//        		matrix[col - j - 1][i] = temp;
//        		
//        		temp = matrix[j][row - i - 1];
//        		matrix[j][row - i - 1] = matrix[col - i - 1][row - j -1];
//        		matrix[col - i - 1][row - j -1] = temp;
//        		
//        		temp = matrix[col - j - 1][i];
//        		matrix[col - j - 1][i] = matrix[j][row - i - 1];
//        		matrix[j][row - i - 1] = temp;
//        	}
//        }
//        
//    }
    
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
			this.val = x;
		}
	}

}
