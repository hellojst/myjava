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

}
