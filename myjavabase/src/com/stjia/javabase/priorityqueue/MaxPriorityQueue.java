package com.stjia.javabase.priorityqueue;
/**
 *基于堆的 优先队列，
 *存一组数中的最小的N个数，取最大的N个与次相反，根比叶子都小；
 * @author stjia
 * @date 2018年4月20日
 */

public class MaxPriorityQueue<Key extends Comparable<Key>> {
	private Key[] pq; //基于堆的完全二叉树
	private int N = 0; //存储于pq[1..N]中，pq[0]没有使用；
	

	public MaxPriorityQueue(int max) {
		// TODO Auto-generated constructor stub
		pq = (Key[])new Comparable[max + 1]; //泛型的类型擦除使不支持泛型数组
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N; //因为n=0没有用，所以size==N；
	}
	
	public void insert(Key v) {
		pq[++N] = v;
		swim(N); //新插入点都用上浮使其有序；
	}
	
	public Key delMax() {
		Key max = pq[1];
		exch(1, N--); //将其移到最后一个位置，总数减1；实现删除效果
		pq[N + 1] = null; //置为null是为了防止对象游离造成不能被gc回收，导致内存泄漏；
		sink(1); //因为把最后一个跟第一个值交换了位置，所以要从上到下，执行下潜恢复堆有序；
		return max;
	}
	
	/**
	 * 一下两个为核心算法  堆的上浮与下沉
	 * @param k为最插入处的位置，由插入点向上浮动
	 * 上浮是由于插入点比父节点更大而被打破，
	 */
	private void swim(int k) {
		while (k > 1 && less(k/2, k)) { //k/2处值大于k处值时继续上浮直至父节点较大
			exch(k/2, k);
			k = k / 2;
		}
	}
	
	/**
	 * 下潜：与上浮相反，由于改处节点比两个子节点或其中一个子节点更小了而被打破，
	 * @param k
	 */
	private void sink(int k) {
		while (k*2 <= N) {
			int j = k*2;
			if (j < N && less(j, j + 1)) j++; //若有右子节点，且右子节点大于左子节点,取较大的节点
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	
	/**
	 * 比较 一般都是i-j;
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j){
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 1; i < N; i++) {
			stringBuilder.append(pq[i]).append(",");
		}
		return stringBuilder.toString();
	}
	
	
}
