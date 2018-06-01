package com.stjia.javabase.java7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * fork/join框架应用
 * @author stjia
 * @date 2018年5月3日
 */
public class MaxValue {
	
	private final ForkJoinPool forkJoinPool = new ForkJoinPool();
	ExecutorService executorService = Executors.newCachedThreadPool();
	
	
	public void calculate(long[] array) {
		MaxValueTask task = new MaxValueTask(array, 0, array.length);
		long result = forkJoinPool.invoke(task);
		System.out.println(result);
	}
	
	
	private static class MaxValueTask extends RecursiveTask<Long> {
		private static final int RANGE_LENGTH = 20; // fork/join 分割阈值，为每个线程处理的数据片段长度
		private final long[] array;
		private final int start;
		private final int end;
		
		/**
		 * 
		 */
		MaxValueTask(long[] array, int start, int end) {
			this.array = array;
			this.start = start;
			this.end = end;
			// TODO Auto-generated constructor stub
		}
		/* (non-Javadoc)
		 * @see java.util.concurrent.RecursiveTask#compute()
		 */
		@Override
		protected Long compute() {
			// TODO Auto-generated method stub
			long max = Long.MIN_VALUE;
			if (end - start <= RANGE_LENGTH) {
				for (int i = start; i < end; i++) {
					if (max < array[i]) {
						max = array[i];
					}
				}
			} else {
				int mid = (start + end)/2;
				MaxValueTask lowTask = new MaxValueTask(array, start, mid);
				MaxValueTask highTask = new MaxValueTask(array, mid, end);
				lowTask.fork(); //fork 用于将拆分的任务放入到工作队列中
				highTask.fork();
				max = Math.max(max, lowTask.join());
				max = Math.max(max, highTask.join());
			}
			
			return max;
		}
		
	}
	
	//测试：
	public static void main(String[] args) {
		long[] arr = new long[100];
		for (int i = 1; i < 101; i++) {
			arr[i - 1] = i;
		}
		
		MaxValue maxValue = new MaxValue();
		maxValue.calculate(arr);
		
	}
}
