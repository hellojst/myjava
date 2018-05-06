package com.stjia.javabase.java7;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier 测试
 * @author stjia
 * @date 2018年5月6日
 */
public class CyclicBarrierTest {
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(5);
		final CyclicBarrier barrier = new CyclicBarrier(5);
		for (int i = 0; i < 5; i++) {
			exec.execute(new Player("player:" + i, barrier));
		}
		exec.shutdown();
	}
	
	static class Player implements Runnable{
		
		private final CyclicBarrier barrier;
		private final String name;

		public Player(String name, CyclicBarrier barrier) {
			// TODO Auto-generated constructor stub
			this.name = name;
			this.barrier = barrier;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				TimeUnit.MILLISECONDS.sleep(new Random(47).nextInt(1000));
				System.out.println(name + "已准备，等待其他玩家加入！");
				barrier.await();
				TimeUnit.MICROSECONDS.sleep(new Random(47).nextInt(1000));
				System.out.println(name + "已加入游戏！");
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				System.out.println(name + "已离开游戏！");
			}
		}
		
	}
}
