package com.stjia.javabase.priorityqueue;

/**
 * @author stjia
 * @date 2018年4月20日
 */
public class MinN {
	private final static int N = 30;

	public static void main(String[] args) {

		MaxPriorityQueue<Integer> maxPriorityQueue = new MaxPriorityQueue<>(N + 1);
		for (int i = 0; i < 50; i++) {
			maxPriorityQueue.insert(i);
			if (maxPriorityQueue.size() > N) { //若优先队列中存在m+1个元素，删除最大值，使其保持在M个元素
				maxPriorityQueue.delMax();
			}
		}

		System.out.println(maxPriorityQueue);
	}

}
