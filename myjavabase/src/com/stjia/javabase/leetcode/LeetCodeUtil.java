package com.stjia.javabase.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
	 * 给定一个二叉树，找出其最大深度。 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
	 * 
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
		for (int i = 1; i <= n; i++) {
			String temp = "";
			if (isFizz(i))
				temp += "Fizz";
			if (isBuzz(i))
				temp += "Buzz";
			output.add(temp.isEmpty() ? (i + "") : temp);
		}
		return output;
	}

	private static boolean isFizz(int n) {
		return n % 3 == 0;
	}

	private static boolean isBuzz(int n) {
		return n % 5 == 0;
	}

	/**
	 * 括号匹配
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValid(String s) {
		char[] chars = s.toCharArray();
		LinkedList<Character> operChar = new LinkedList<>();
		boolean isValid = true;
		for (int i = 0; i < s.length(); i++) {
			char ch = chars[i];
			if (ch == '(' || ch == '{' || ch == '[') {
				operChar.push(ch);
			} else if (ch == ')' || ch == ']' || ch == '}') {
				if (!operChar.isEmpty()) {
					isValid = isMatcher(ch, operChar.pop());
				} else {
					isValid = false;
				}

			} else {
				continue;
			}
			if (!isValid) {
				break;
			}
		}
		return isValid && operChar.isEmpty();
	}

	/**
	 * ch 与pattern是否匹配
	 * 
	 * @param ch
	 * @param pattern
	 * @return
	 */
	private static boolean isMatcher(char ch, char pattern) {
		switch (pattern) {
		case '(':
			return ch == ')';
		case '[':
			return ch == ']';
		case '{':
			return ch == '}';
		default:
			return false;
		}
	}

	/**
	 * 最大利润
	 * 
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}
		int profit = 0;
		LinkedList<Integer> buyPrice = new LinkedList<>();
		int length = prices.length;
		if (prices[1] - prices[0] > 0)
			buyPrice.push(prices[0]);
		for (int i = 1; i < prices.length - 1; i++) {
			// 谷值买入
			if (prices[i] <= prices[i - 1] && (prices[i]) < prices[i + 1]) { // _/ 或 \/
				if (buyPrice.isEmpty()) {
					buyPrice.push(prices[i]);
				}
			}

			// 峰值卖出； 前提是有买过
			if (prices[i] >= prices[i - 1] && (prices[i] > prices[i + 1])) { // T\ 或 /\
				if (!buyPrice.isEmpty()) {
					profit += prices[i] - buyPrice.pop();
				}
			}
		}
		if (prices[length - 1] >= prices[length - 2]) {
			if (!buyPrice.isEmpty()) {
				profit += prices[length - 1] - buyPrice.pop();
			}
		}
		return profit;
	}

	/**
	 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
	 * 
	 * @param s
	 * @return
	 */
	public static int firstUniqChar(String s) {
		char[] chars = s.toCharArray();
		Map<Character, List<Integer>> map = new LinkedHashMap<>(); // map 存字符与其索引，
																	// linkedmap保持其有序性，则无需全部遍历map，只需找到第一个符合条件的值即可
		int length = chars.length;
		for (int i = 0; i < length; i++) {
			char c = chars[i];
			if (map.get(c) == null) {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(c, list);
			} else {
				map.get(c).add(i);
			}
		}
		for (List<Integer> list : map.values()) {
			if (list.size() == 1) {
				return list.get(0);
			}
		}
		return -1;
	}

	/**
	 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点,给定的 n 保证是有效的, 你能尝试使用一趟扫描实现吗？
	 * 
	 * @param head
	 * @param n
	 * @return 使用长度为n的队列应该是个好方法； //或是定义两个指针间距为n,跟队列效果相同；
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head.next == null) {
			return null;
		}
		// 一下方法前提是有被删除节点有前驱节点
		Queue<ListNode> priorityQueue = new LinkedList<>();
		int capacity = n + 1;
		int flag = 0;
		ListNode temp = head;
		while (temp != null) {
			priorityQueue.offer(temp);
			flag++;
			if (flag > capacity) {
				priorityQueue.poll();
			}
			temp = temp.next;
		}
		if (flag == n) {
			// 被删除节点无前置节点
			ListNode deListNode = head;
			head = head.next;
			deListNode.next = null;
		} else {
			// 被删除节点有前置节点
			ListNode preNode = priorityQueue.poll();
			ListNode deListNode = preNode.next;
			preNode.next = deListNode.next;
			deListNode.next = null;
		}
		return head;
	}

	/**
	 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int[] newArrays = new int[m];
//		System.arraycopy(nums1, 0, newArrays, 0, m);
		for(int i = 0; i < m; i++) {
			newArrays[i] = nums1[i];
			nums1[i] = 0;
		}
		int lt = 0, gt = 0; // 定义两个扫描指针，一个扫描nums1, 一个扫描 nums2;
		for (int i = 0; i < m + n; i++) {
			if (lt >= m) {
				nums1[i] = nums2[gt++];
			} else if (gt >= n) {
				nums1[i] = newArrays[lt++];
			} else if (newArrays[lt] > nums2[gt]) {
				nums1[i] = nums2[gt++];
			} else if (newArrays[lt] <= nums2[gt]) {
				nums1[i] = newArrays[lt++];
			}
		}
	}
	
	/**
	 * 给定一个二叉树，检查它是否是镜像对称的。
	 * @param root
	 * @return
	 */
    public static boolean isSymmetric(TreeNode root) {
    	if (root == null) return true;
        return isEqual(root.left, root.right);
    }
    //递归   为镜像对称的条件是 其左树的左树与右树的右树对称  左树的右树与右树的左树对称，且左树的值等于右树的值
    private static boolean isEqual(TreeNode left, TreeNode right) {
//    	if (left == null) {
//			return right == null ? true : false;
//		}
//    	if (right == null) {
//			return left == null ? true : false;
//		}
    	//与上面注释掉的判断结果相同  另一种写法但先&&后||顺序不能变
    	if (left == null && right == null) {
			return true;
		}
    	if (left == null || right == null) {
			return false;
		}
    	return isEqual(left.left, right.right) && isEqual(left.right, right.left) && left.val == right.val;  
    }

    //一个一个的移动，但是会超时
    public static void rotate(int[] nums, int k) {
    	if (k <= 0 || nums.length < 2) {
			return;
		}
        for (int i = 0; i < k; i++) {
			rotateForOnec(nums, 1);
		}
    }
    
    /**
     * 不循环可以一步到位  遇到偶数可以拆为k-1 与  1  k值不好把握，很容易某个点后无限循环导致之后的点无法参与
     * @param nums
     * @param k
     */
    private static void rotateForOnec(int[] nums, int k) {
    	int length = nums.length;
        int count = 0;
        int index = 0;
        int temp = nums[index];  //将第一个位置值取出等待与下一个到达点交换
        while(count <= length) {
        	int nextIndex = (index + k) % length; //index的下一个位置   通过除length取余来实现围绕length长度旋转
        	//将nextIndex初值与nextIndex的上个点的缓存在temp中的值交换
        	int changeTemp = nums[nextIndex];
        	nums[nextIndex] = temp;
        	temp = changeTemp;
        	index = nextIndex;
        	count++;
        }
    }
    
    //有一种好方法满足时间复杂度O(n), 空间复杂度O(1)  善于观察，利用数组反转，
    /**
     * 步长为k,总长为n；将数组反转 数组分为两段[0,k) 和 [k, n)
     * 最终结果的前k段为[0,k)的反转,后面部分为[k,n)的反转；依次思路求解
     * @param nums
     * @param k
     */
    public static void rotateReserve(int[] nums, int k) {
    	if (k <= 0 || nums.length < 2) {
			return;
		}
    	k = k % nums.length;
    	reverse(nums, 0, nums.length - 1);
    	reverse(nums, 0, k - 1); //前k-1的反转
    	reverse(nums, k, nums.length - 1); //[k,n)的反转
    }
    
    private static void reverse(int[] nums, int lo, int hi) {
    	int length = hi - lo + 1;
    	int half = length / 2;
    	for(int k = 0; k < half; k++) {
    		int temp = nums[lo];
    		nums[lo] = nums[hi];
    		nums[hi] = temp;
    		lo++;
    		hi--;
    	}
    }
    
    /**
     * 先排序，再判断  方法一；  若单纯的只想找出是否重复可边排序边判断
     * @param nums
     * @return
     */
    public static boolean containsDuplicate(int[] nums) {
        quick3waySort(nums, 0, nums.length - 1);
        for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) {
				return true;
			}
		}
        return false;
    }
    
    private  static void  quick3waySort(int[] nums, int lo, int hi) {
    	if (lo >= hi) {
			return;
		}
    	int stValue = nums[lo];
    	int lt = lo, gt = hi, i = lo + 1;  //保持lt是小于stValue和等于stValue的边界， gt是大于等于的边界
        while(i <= gt) {
        	if (nums[i] < stValue) {
				exchange(nums, i, lt);
				lt++;
				i++;
			} else if (nums[i] > stValue) {
				exchange(nums, i, gt);
				gt--;
			} else {
				i++;
			}
        }
    	quick3waySort(nums, lo, lt - 1);
    	quick3waySort(nums, gt +1, hi);
    }
    
    /**
     * 方法2
     * @param nums
     * @return
     */
    public static boolean containsDuplicate2(int[] nums) {
        return quick3waySort2(nums, 0, nums.length - 1);
    }
    
    /**
     * 三向排序    [lo,lt) < stValue; [lt,i] == stValue; [i,gt]:未扫描区域，扫描完成后i与gt重合; [gt,hi] > stValue;
     * @param nums
     * @param lo
     * @param hi
     * @return
     */
    private  static boolean  quick3waySort2(int[] nums, int lo, int hi) {
    	if (lo >= hi) {
			return false;
		}
    	int stValue = nums[lo];
    	int lt = lo, gt = hi, i = lo + 1;  //保持lt是小于stValue和等于stValue的边界， gt是大于等于的边界
        while(i <= gt) {
        	if (nums[i] < stValue) {
				exchange(nums, i, lt);
				lt++;
				i++;
			} else if (nums[i] > stValue) {
				exchange(nums, i, gt);
				gt--;
			} else {
				i++;
			}
        }
        if (gt > lt) {  //即大于与小于中间无间隔
			return true;
		}
    	return quick3waySort2(nums, lo, lt - 1) || quick3waySort2(nums, gt +1, hi);
    }
    
    private static void exchange(int[] datas, int i, int j) {
    	int temp = datas[i];
    	datas[i] = datas[j];
    	datas[j] = temp;
    }
    
    /**
     * 递归会超时 因为存在很多重复计算     由大到小  由尾到头  逆向求解打散     由整到零
     * 方法一： 递归 因为最多只能跨两步 所以 若大于2 可以以2位一个基本单位  （因为2为不可分割的最小单位） 一个基本单位里有两种方法
     * 第n阶的方法和为 1: 若在n-1的位置只需一种方法  若在n-2处的位置有两种，一次两步·或 一步一步，一步步与到n-1处的方法中的一种重合 所以也是一种方法
     * 所以 f(n) = f(n-1)+f(n-2);
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if(n == 0 || n == 1 || n == 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
        //或使用一下三目运算符进行联合
//        return (n == 0 || n == 1 || n == 2) ? n : climbStairs(n - 1) + climbStairs(n - 2);
    }
    
    /**
     * 当递归存在大量重复计算时要考虑使用迭代来代替递归
     * 迭代：由零到整   由分散到整体  只需要一直存两个连续值来求第三个值
     * 假设n阶总数为f(n): 若第一次跳了一阶，剩f(n-1)种方法；若第一次跳了2阶，则剩f(n-2)种方法；  (走过的楼梯都去掉)
     * 所以f(n) = f(n-1) + f(n-2)
     * 迭代可以消除重复计算 
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
    	if (n == 0 || n == 1 || n ==2) return n;
    	int step1 = 1, step2 = 2;
    	int result = 0;
    	for (int i = 3; i <= n; i++) {
			result = step1 + step2;
			step1 = step2;
			step2 = result;
		}
		return result;
    }
    
    /**
     * 给定一个整数数组，除了一个元素外，每个元素都会出现两次。找到那一个。
     * 一个没啥特色，效率一般，还需要额外空间的方法    
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
    	List<Integer> list = new ArrayList<>();
    	for(int i = 0; i < nums.length; i++) {
    		if (!list.contains(nums[i])) {
				list.add(nums[i]);
			} else {
				Iterator<Integer> iterator = list.iterator();
				while (iterator.hasNext()) {
					int value = iterator.next();
					if (nums[i] == value) {
						iterator.remove();
					}
				}
				
			}
    	}
        return list.get(0);
    }
    
    /**
     * 给定一个整数数组，除了一个元素外，每个元素都会出现两次。找到那一个。
     * 以上问题的另一种算法：： 位运算
     * 1；相同的数异或结果为0；  2：任意值与0异或结果为值本身
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
    	int result = 0;
    	for(int i : nums) {
    		result = result ^ i;
    	}
    	return result;
    }
    
    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        String temp = s.replaceAll("[^a-zA-Z0-9]", "");  //string为final类型的，所以必须将值付给另一个final，本身的string不会变
        if (s.isEmpty()) {
			return true;
		}
        
        char[] chars = temp.toLowerCase().toCharArray();
        int length = chars.length;
        for(int i = 0; i < length/2; i++) {
        	if (chars[i] != chars[length - i -1]) {
				return false;
			}
        }
        return true;
    }
    
    /**
     * 递归
     * 反转链表
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
    	ListNode node;
    	if (head == null || head.next == null) {
    		return head;
		}
    	node = reverseList(head.next);
    	head.next.next = head;
    	head.next = null;
    	return node;
    }
    
    /**
     * 迭代
     * 反转链表
     * @param head
     * @return
     */
    public static ListNode reverseListIte(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode pointNode = head, newNode = null;
		while (pointNode != null) {
			ListNode node = pointNode;
			pointNode = pointNode.next;
			node.next = newNode;
			newNode = node;
		}
		return newNode;
	}
    
    /**
     * 给定两个数组，写一个方法来计算它们的交集
     * 两个数组，一个逐个输出数组值去与另一个数组中每一次元素对比
     * 
    如果给定的数组已经排好序呢？你将如何优化你的算法？   先比收尾，特殊情况直接判断，若在范围内再用二分查找；  基本思路与该方法同
    如果 nums1 的大小比 nums2 小很多，哪种方法更优？  用nums1作为被标准被比较数组，有很大几率nums2数据没用完，nums1可比数据就没了，就结束魂环出结果了
    如果nums2的元素存储在磁盘上，内存是有限的，你不能一次加载所有的元素到内存中，你该怎么办？  nio按块读取？再逐个与nums1中比，直至满足结束条件；

     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        int length = nums1.length;
        int length2 = nums2.length;
        for(int i = 0; i < length2; i++) { // nums2
        	if (length <= 0) {
				break;
			}
        	//将符合条件的值调整到数组的末尾，并将比较数组的可比较长度往前移动一位，实现将交集保存的目的
        	for(int j = 0; j < length; j++) { // nums1
        		if (nums2[i] == nums1[j]) {
					int temp;
					temp = nums1[j];
					nums1[j] = nums1[length - 1];
					nums1[length - 1] = temp;
					length--;
					break;
				}
        	}
        }
        int size = nums1.length - length;
        int[] results = new int[size];
        int j = 0;
        // 将保存在nums末尾的交集复制出来
        for(int k = nums1.length - size; k < nums1.length; k++) {
        	results[j] = nums1[k];
        	j++;
        }
        return results;
    }
    
    /**
     * 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。

最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        int index = digits.length - 1; //尾指针
        while(index >= 0 && digits[index] + 1 >= 10) {
        		digits[index] = (digits[index] + 1) % 10;
        		index--;
        }
        if (index >= 0) {
        	digits[index] += 1; 
		} else {
			int[] temp = new int[digits.length + 1];
			temp[0] = 1;
			System.arraycopy(digits, 0, temp, 1, digits.length);
			digits = temp;
		}
        return digits;
    }
    
//    private static ListNode reverseLinkList(ListNode node) {
//    	if (node.next == null) {
//    		return;
//		}
//    	reverseLinkList(node.next);
//    	node.next.next = node;
//    }
    
    
    
	// public static boolean isValid(String s) {
	// char[] chars = s.toCharArray();
	// boolean isValid = true;
//	LinkedList<Character> cha1 = new LinkedList<>(); // '扩号'栈
	// LinkedList<Character> cha2 = new LinkedList<>(); //'['栈
	// LinkedList<Character> cha3 = new LinkedList<>(); //'{'栈
	// char lastSpec = 0; //最后一个特殊字符；
	// for (int i = 0; i < s.length(); i++) {
	// char ch = chars[i];
	// switch (ch) {
	// case '(':
	// cha1.push(ch);
	// lastSpec = '(';
	// break;
	// case '[':
	// cha2.push(ch);
	// lastSpec = '[';
	// break;
	// case '{' :
	// cha3.push(ch);
	// lastSpec = '{';
	// break;
	// case ')' :
	// if (!cha1.isEmpty() && lastSpec == '(') {
	// cha1.pop();
	// } else {
	// isValid = false;
	// }
	// break;
	// case ']' :
	// if (!cha2.isEmpty() && lastSpec == '[') {
	// cha2.pop();
	// } else {
	// isValid = false;
	// }
	// break;
	// case '}' :
	// if (!cha3.isEmpty() && lastSpec == '{') {
	// cha3.pop();
	// } else {
	// isValid = false;
	// }
	// default:
	// break;
	// }
	//
	// if (!isValid) {
	// break;
	// }
	// }
	//
	// return isValid && cha1.isEmpty() && cha2.isEmpty() && cha3.isEmpty();
	// }

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
			// 类比洗牌的方法，每个位置都进行移动
			Random random = new Random();
			int length = shuffleArray.length;
			for (int i = 0; i < length; i++) {
				int newPosition = random.nextInt(length); // 随机产生新位置
				int temp = shuffleArray[i];
				shuffleArray[i] = shuffleArray[newPosition];
				shuffleArray[newPosition] = temp;
			}

			return shuffleArray;
		}
	}

}
