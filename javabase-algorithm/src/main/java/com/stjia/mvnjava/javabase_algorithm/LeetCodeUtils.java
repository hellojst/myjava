package com.stjia.mvnjava.javabase_algorithm;

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
}
