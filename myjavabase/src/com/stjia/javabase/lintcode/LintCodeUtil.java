package com.stjia.javabase.lintcode;

/**
 * 
 * @author stjia
 * @time 2018年4月12日
 */
public class LintCodeUtil {
	
	/**
	 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *数组顺序不变  不需要考虑数组中超出新长度后面的元素。
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
     * @param s
     * @return
     */
    public static String reverseString(String s) {
    	if (s.length() == 1) {
			return s;
		}
        int index = s.length()/2;
        String s1 = s.substring(0, index);
        String s2 = s.substring(index, s.length());
        return reverseString(s2) + reverseString(s1);
    }
    
    /**
     * 请编写一个函数，其功能是将输入的字符串反转过来。
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
    	for(int i = 0; i < mid; i++) {
			char temp = ss[i];
			ss[i] = ss[length - i -1];
			ss[length - i -1] = temp;
		}
    	return new String(ss);
    }
    

}
