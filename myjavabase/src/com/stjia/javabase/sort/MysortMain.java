package com.stjia.javabase.sort;

import javax.security.auth.kerberos.KerberosKey;

/**
 * 
 * @author stjia
 * @time 2018年3月31日
 */
public class MysortMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("冒泡：");
		SortUtil.printArrays(SortUtil.bubbleSort(SortUtil.getSortData()));
		System.out.println("选择：");
		SortUtil.printArrays(SortUtil.selectionSort(SortUtil.getSortData()));
		System.out.println("插入：");
		SortUtil.printArrays(SortUtil.insertSort(SortUtil.getSortData()));
		//打印可用于希尔排序的递增序列
		SortUtil.generatorShellSort(20);
		System.out.println("希尔：");
		SortUtil.printArrays(SortUtil.shellSort(SortUtil.getSortData()));
		System.out.println("普通快排：");
		int[] sorts = SortUtil.quickSort(SortUtil.getSortData(), SortUtil.NORMAL_QUICK);
		SortUtil.printArrays(sorts);
		System.out.println("三向切分快速排序：");
		int[] sorts2 = SortUtil.quickSort(SortUtil.getSortData(), SortUtil.THREEWAY_QUICK);
		SortUtil.printArrays(sorts2);
		System.out.println("自顶而下的归并排序：");
		int[] sorts3 = SortUtil.mergeSort(SortUtil.getSortData());
		SortUtil.printArrays(sorts3);
		System.out.println("堆排序");
		int[] sorts4 = SortUtil.getSortData();
		SortUtil.heapSort(sorts4);
		SortUtil.printArrays(sorts4);
	}

}
