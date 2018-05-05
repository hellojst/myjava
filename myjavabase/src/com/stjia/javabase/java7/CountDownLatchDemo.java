package com.stjia.javabase.java7;

import java.io.FileReader;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * countdownlatch 只起一次性计数作用   将其放入带同步对象和等待对象中用countdown减计数器，await阻塞等待线程 
 * @author stjia
 * @date 2018年5月4日
 */
public class CountDownLatchDemo {
	static final int SIZE = 100;
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for (int i = 0; i < 10; i++) {
			exec.execute(new WaitingTask(latch));
		}
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new TaskPortion(latch));
		}
		System.out.println("launched all tasks");
		exec.shutdown();
	}
	
	// 需要同时完成的任务，这些任务完成后就通过latch。countdown将数量减一；
	static class TaskPortion implements Runnable {
		private static int count = 0;
		private int id = count++;
		private final CountDownLatch latch;
		private static Random random = new Random(47);

		public TaskPortion(CountDownLatch latch) {
			// TODO Auto-generated constructor stub
			this.latch = latch;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				doWork();
				latch.countDown();  //每完成一个任务，latch数量减1；
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void doWork() throws InterruptedException {
			TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
			System.out.println(this + "completed");
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return String.format("%1$-3d", id);
		}
		
	}
	
	// 需要等待TaskPortion全部完成（latchdown计数值为0）才能执行，否则都阻塞在latch。await处；
	static class WaitingTask implements Runnable {

		private static int count = 0;
		private int id = count++;
		private final CountDownLatch latch;
		private static Random random = new Random(47);
		
		public WaitingTask(CountDownLatch latch) {
			// TODO Auto-generated constructor stub
			this.latch = latch;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				latch.await();
				System.out.println("Latch barrier passed for " + this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return String.format("waiting Task %1$-3d ", id);
		}
		
	}

}
