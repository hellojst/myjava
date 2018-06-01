package com.stjia.javabase.sort;

import java.util.Random;

import javax.imageio.event.IIOReadWarningListener;
import javax.lang.model.element.NestingKind;

/**
 * 排序工具类 默认为升序：从小到大 --- 想要降序只需倒置赋值即可
 * 
 * @author stjia
 * @time 2018年3月31日
 */
public class SortUtil {

	// 测试数据长度
	private static final int LENGTH = 100;
	// shell递增数列，即h序列长度  可通过generatorShellSort生成
	private static final int[] shellHsort = new int[] { 1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 
			8929, 16001, 36289, 64769, 146305, 260609, 587521, 1045505, 2354689, 4188161, 9427969, 
			16764929, 37730305, 67084289, 150958081, 268386305, 603906049, 1073643521};
	public static int NORMAL_QUICK = 0;
	public static int THREEWAY_QUICK = 1;
	private static int[] aux; //自顶而下等递归归并所需的辅助数组
	private static int[] pq; //堆排序辅助数组
	private static int N = 0; //存储于pq[1...N]中，pq[0]没有使用

	public static int[] getSortData() {
		int[] datas = new int[LENGTH];
		Random random = new Random(123);
		for (int i = 0; i < LENGTH; i++) {
			int j = datas[i];
			// 生成0-500的随机数
			datas[i] = random.nextInt(512);
		}
		return datas;
	}
	public static int[] getSortData(int count) {
		int[] datas = new int[count];
		Random random = new Random(123);
		for (int i = 0; i < count; i++) {
			int j = datas[i];
			// 生成0-500的随机数
			datas[i] = random.nextInt(512);
		}
		return datas;
	}

	// 用于希尔排序的递增序列 通过9x4^k - 9x2^k + 1 与 4^k - 3x2^k + 1 综合得到，可将性能提升 20%-40%
	public static int[] shellhSort() {
		return shellHsort;
	}
	// 用于希尔排序的递增序列 通过9x4^k - 9x2^k + 1 与 4^k - 3x2^k + 1 综合得到，可将性能提升 20%-40%
	public static void generatorShellSort(int length) {
		long[] first = new long[length];
		long[] second = new long[length];
		for (int i = 0; i < length; i++) {
			first[i] = (long) (9*Math.pow(4, i)-9*Math.pow(2, i)+1); 
			second[i] = (long) (Math.pow(4, i) - 3 * Math.pow(2, i) + 1);
		}
		System.out.print("9x4^k - 9x2^k + 1: ");
		for (long l : first) {
			System.out.print(l + ", ");
		}
		
		System.out.print("\n4^k - 3x2^k + 1: ");
		for (long l : second) {
			System.out.print(l + ", ");
		}
		
		//归并： 合并后两个数组  归并排序的方法体，两个有序数列的合并
		long[] merge = new long[length*2];
		int n = length * 2;
		int f = 0; //指定first数组的下标
		int s = 0; //指定second数组的下标
		for (int i = 0; i < n; i++) {
			if (f >= length) merge[i] = second[s++];
			else if (s >= length) merge[i] = first[f++]; //先考虑极端，其中一个被取尽了，只用将另一个按序放入即可
			else if (first[f] > second[s]) merge[i] = second[s++];  //一般情况
			else merge[i] = first[f++];
		}
		System.out.print("\n合并后：");
		for (long l : merge) {
			System.out.print(l + ", ");
		}
		//换行
		System.out.println();
	}

	/**
	 * 返回适合的shell递增序列部分
	 * 
	 * @param datas
	 * @return
	 */
	public static int[] getSuitableShellHsort(int[] datas) {
		int length = datas.length;
		int index = 0; // 希尔排序能分割的最大h序列长度的下标
		for (int i = 0; i < shellHsort.length; i++) {
			if (shellHsort[i] > length) {
				index = i - 1;
				break;
			}
		}
		if (index < 0) {
			throw new RuntimeException("排序数组为空！");
		}
		int[] suitsorts = new int[index + 1];
		for (int i = 0; i < index + 1; i++) {
			suitsorts[i] = shellHsort[i];
		}
		return suitsorts;
	}

	/**
	 * 升序改倒序， 倒着赋值即可
	 * 
	 * @return
	 */
	public static int[] exchangeSort(int[] sorts) {
		int length = sorts.length;
		int[] exsorts = new int[length];
		for (int i = 0; i < length; i++) {
			exsorts[i] = sorts[length - 1];
		}
		return exsorts;
	}
	
	public static void printArrays(int[] datas) {
		for (int i : datas) {
			System.out.print(i + ", ");
		}
		//换行
		System.out.println();
	}

	/**
	 * 冒泡排序 思想：每一轮交换都将后面数字与i处数字比较，小则交换，每轮过后i处都为最小值
	 * 
	 * @param datas
	 *            待排
	 * @return 已排
	 */
	public static int[] bubbleSort(int[] datas) {
		int length = datas.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				if (datas[i] > datas[j]) {
					int temp = datas[i];
					datas[i] = datas[j];
					datas[j] = datas[i];
				}
			}
		}
		return datas;
	}

	/**
	 * 选择排序 思想：每轮找出一最小值记下下标，一轮结束后将最小值与i处交换，最多只需交换datas.length次，比较N^2/2次
	 * 特点：左边部分是有序的，<j的部分不用操作
	 * 
	 * @param datas
	 * @return
	 */
	public static int[] selectionSort(int[] datas) {
		int length = datas.length;
		for (int i = 0; i < length - 1; i++) {
			int min = i;// 记录一轮（i+1 ---- length）中的最小值索引
			for (int j = i + 1; j < length; j++) {
				if (datas[j] < datas[min]) {
					min = j ;
				}
			}
			// 一轮之后将最小值与i交换位置；
			int temp = datas[i];
			datas[i] = datas[min];
			datas[min] = temp;
		}
		return datas;
	}

	/**
	 * 普通插入排序 shell是跨h的插入操作； 思想，邻位交换直到插入到了合适位置； 特点，左边部分一直有比较插入操作，右边为到部分不操作：：
	 * 操作对象是j前的所有数据
	 * 
	 * @param datas
	 * @return
	 */
	public static int[] insertSort(int[] datas) {
		int length = datas.length;
		for (int i = 0; i < length; i++) {
			// 每一轮都在j前的数据里找到j的合适插入位置：j未插入时其j前本身为有序数列，将j逐个往前交换即可找到合适位置
			for (int j = i; j > 0; j--) {
				// 将j逐个前移直到找到合适位置
				if (datas[j] < datas[j - 1]) {
					int temp = datas[j];
					datas[j] = datas[j - 1];
					datas[j - 1] = temp;
				}
			}
		}
		return datas;
	}

	/**
	 * 希尔排序
	 * 希尔排序可以看作是特殊的插入排序， 将间隔h设为1即为普通的插入排序
	 * 希尔排序通过h间隔可使排序比较和插入更平缓，每次交换h个位置对于倒序数组与可以有效减少比较和移动次数，是排序复杂度降到平方级以下
	 * @param datas
	 * @return
	 */
	public static int[] shellSort(int[] datas) {
		//获取一个已知的比较好的序列， 也可以自己构造递增序列
		int[] hsorts = getSuitableShellHsort(datas);
		int length = datas.length;
		int index = hsorts.length - 1;
		while(index >= 0) {
			for (int i = hsorts[index]; i < length; i++) {
				//h=hsorts[sortlength - 1];将data[i]插入到datas[i-h],datas[i-2*h],datas[i-3*h]...中去，即为
				//length/h个插入排序，跨度为h
				for (int j = i; j >= hsorts[index]; j -= hsorts[index]) {
					//小则前移
					if (datas[j] < datas[j - hsorts[index]]) {
						int temp = datas[j];
						datas[j] = datas[j-hsorts[index]];
						datas[j-hsorts[index]] = temp;
					}
				}
			}
			index--; 
		}
		return datas;
	}

	/**
	 * 快速排序
	 * @param datas
	 * @return
	 */
	public static int[] quickSort(int[] datas, int type) {
		if (type == NORMAL_QUICK) {
			quickSort(datas, 0, datas.length - 1);
		} else if (type == THREEWAY_QUICK) {
			quick3waySort(datas, 0, datas.length - 1);
		} else {
			throw new RuntimeException("no match quick sort type");
		}
		return datas;
	}
	
	private static void quickSort(int[] datas, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int position = partition(datas, lo, hi);
		quickSort(datas, lo, position - 1);
		quickSort(datas, position + 1, hi);
	};
	
	/**
	 * 此为快速排序的核心算法
	 * 快速排序的切分
	 * 执行一次此方法即返回索引的左侧均小于索引处值，右侧均大于索引处值
	 * @param lo 最低位
	 * @param hi 最高位
	 * @return 切分点
	 */
	private static int partition(int[] datas, int lo, int hi) {
		//将数组切分为 a[lo...i-1],a[i],a[i+1...hi]
		int i =lo, j = hi + 1; //左右扫描指针
		int v = datas[lo]; //切分元素
		while (true) {
			//切分元素，以第一个值v为切分的值，则左边为小于v右边值大于V，下面两个while，用于从两头逼近将小值换到左大值换到右侧
			while (datas[++i] < v) if (i == hi) break;
			while (datas[--j] > v) if (j == lo) break;
			if (i >= j) break;
			
			//将小值移到V左，大值移到v右
			int temp = datas[i];
			datas[i] = datas[j];
			datas[j] = temp;
		}
		
		//将v放到中间位置，其左均比其小，其右均比其大 因为跳出循环条件为i>=j;还应注意的是，左指针先动，i停止的地方要么比v大，要么到达右指针位置；
		//而右指针后动，所以最后跳出大循环时左指针指的是大于v的第一个数，右指针则可以刚好跨过，到大于V的（左指针位置）的数左边一位数，与V换位即可，即右指针与V换位
		
		//更简单的理解，因为以第一个值为参照，第一个值在左侧，换到第一位的应是小于参照值的，右侧指针到第一个小于参照值的值；
		int temp = v; //v即最低位 datas[lo]; //切分元素值
		v = datas[j];
		datas[j] = temp;
		
		return j;
	}
	
	/**
	 * 三向切分快速排序
	 * 指针lt ，gt a[lo。。lt-1]都小于v， a[gt+1..hi]都大于v， a[lt..i-1]都等于v，a[i..gt]不确定
	 * @param datas
	 * @param lo
	 * @param hi
	 */
	public static void quick3waySort(int[] datas, int lo, int hi) {
		if (lo >= hi) return;
		int lt = lo, i = lo + 1, gt = hi;
		int v = datas[lo]; //作为参考值，
		while (i <= gt) { //gt后移动过去的大于v的值，i到gt之间的值为要遍历待移动的值
			//i从左到有移动遍历数组，小于v的值与lt指针位置换位，从而保证lt已左都小于v，大于v的值与gt处值换位，
			//保证gt以右都是大于v的值，等于v则继续往后遍历，保证lt与i间都等于v
			if (datas[i] < v)  exch(datas, lt++, i++);
			else if (datas[i] > v)  exch(datas, i, gt--);
			else i++;
		}
		quick3waySort(datas, lo, lt - 1);//lo ---- lt-1是无序的
		quick3waySort(datas, gt + 1, hi);//gt+1 ---- hi是无序的
	}
	
	/**
	 * 交换元素
	 * @param datas
	 * @param i
	 * @param j
	 */
	public static void exch(int[] datas, int i, int j) {
		int temp = datas[i];
		datas[i] = datas[j];
		datas[j] = temp;
	}
	
	
	/**
	 * 抽象归并算法： 将mid左右的两个有序数列归并成一个有序数列，
	 * 归并算法的核心，借助递归到一个最小单元（左右都为一个有序数列：长度为1时，可从一个最小的有序数列归并为一个大的有序数列）
	 * @param datas
	 * @param lo
	 * @param mid
	 * @param hi
	 */
	private static void merge(int[] datas, int lo, int mid, int hi) {
		int i = lo, j = mid + 1; //前后部分指针起点
		for(int k = lo; k <= hi; k++) aux[k] = datas[k]; //构建副本
		for (int k = lo; k <= hi; k++) { // 归并，
			if (i > mid) datas[k] = aux[j++]; //左半边用尽就去右半边元素
			else if (j > hi) datas[k] = aux[i++]; //右半边用尽就取左半边元素  左半边或右半边用尽时改值就是最后一个值
			else if (aux[i] > aux[j]) datas[k] = aux[j++];
			else datas[k] = aux[i++];
		}
			
	}
	
	/**
	 * 归并算法
	 * @param datas
	 */
	public static int[] mergeSort(int[] datas) {
		aux = new int[datas.length]; //一次性分配空间
		hi2loMergerSort(datas, 0, datas.length - 1);
		return datas;
	}
	
	//递归实现
	public static void hi2loMergerSort(int[] datas, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		//差分，将这两部分进行递归；
		hi2loMergerSort(datas, lo, mid);
		hi2loMergerSort(datas, mid + 1, hi);
		merge(datas, lo, mid, hi);	
	}
	
	
	//堆排序
	private boolean less(int i, int j) {
		return pq[i] - pq[j] < 0;
	}
	//堆排序
	private static boolean less(int[] datas ,int i, int j) {
		return datas[i] - datas[j] < 0;
	}
	
	private void exch(int i, int j) {
		int temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	//上浮 ： 由下至上的堆有序化
	private void swim(int k) {
		//还未浮到最上层，且子大于根时交换
		while (k > 0 && less(k/2, k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	
	//下沉 ：由上向下的堆有序化  根从1开始 k的子为2*k
	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k; //k节点的左子节点；
			if (j < N && less(j, j + 1)) j++; //比较k节点的左右子节点，取较大的与父节点交换
			if (!less(k, j)) break; //若父 > 子 表示命中，下沉结束；
			exch(k, j);
			k = j;
		}
	}
	
	//下沉 ：由上向下的堆有序化（注意未插入前堆是有序化过的）, 不需借助外部成员变量  根从0开始，k的子为2*k+1
	private static void sink(int[] datas,int k, int count) {
		while (2 * k + 1 <= count) {
			int j = 2 * k + 1; //k节点的左子节点；
			if (j < count && less(datas, j, j + 1)) j++; //比较k节点的左右子节点，取较大的与父节点交换
			if (!less(datas, k, j)) break; //若父 > 子 表示命中，下沉结束；
			exch(datas, k, j);
			k = j;
		}
	}
	
	/**
	 * 当根节点为1时，k的子节点：2*k， 2*k+1; k的父节点为 k/2;
	 * 当根节点为0时，k的子节点：2*k + 1, 2*k + 2; k的父节点为(k - 1)/2;
	 * @param datas
	 */
	public static void heapSort(int[] datas) {
		int N = datas.length - 1;
		for (int i = (N-1)/2; i >= 0; i--) { //先构造一个有序堆，从最后一个点的父节点开始   该树根为0
			sink(datas, i, N);
		}
		
		//因为堆有序时唯一可以确定的是根节点是最大值，因为根节点在最上层且只有一个元素
		//所以可以取出根节点并与最后一个值换位，并将总数减一，剩下的所有在进行下沉
		while (N > 0) {
			exch(datas, 0, N--);
			sink(datas, 0, N);
		}
	}
}
