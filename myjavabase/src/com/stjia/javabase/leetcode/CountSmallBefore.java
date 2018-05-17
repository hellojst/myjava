package com.stjia.javabase.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数数组（下标由 0 到 n-1， n 表示数组的规模，取值范围由 0 到10000）。对于数组中的每个 ai 元素，请计算 ai
 * 前的数中比它小的元素的数量。
 * 
 * @author stjia
 * @time 2018年4月12日
 */
public class CountSmallBefore {

	public List<Integer> countOfSmallerNumberII1(int[] A) {
		// write your code here
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < A.length; i++) {
			int v = A[i], n = 0;
			for (int j = 0; j < i; j++) {
				if (A[j] < v) {
					n++;
				}
			}
			list.add(n);
		}

		return list;
	}

	public List<Integer> countOfSmallerNumberII2(int[] A) {
		// write your code here
		List<Integer> list = new ArrayList<>();
		// 先插入排序兼统计；
		int length = A.length;
		int C[] = new int[length];
		for (int i = 0; i <length; i++) {
			int index = 0;
			int v = A[i];
			int B[] = new int[i + 1];
			for (int j = 0; j < B.length; j++) {
				if (v > B[j]) {
					index++;
				} else {
					System.arraycopy(B, index, B, index + 1, length - index - 1);
					B[index] = v;
					break;
				}
			}
			list.add(index);
		}

		return list;
	}



}
