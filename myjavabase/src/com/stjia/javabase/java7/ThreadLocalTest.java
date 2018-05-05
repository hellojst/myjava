package com.stjia.javabase.java7;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 类
 * @author stjia
 * @date 2018年5月5日
 */
public class ThreadLocalTest implements Runnable {
	private final int id;
	private static int number = 0;
	
	public ThreadLocalTest(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
		number++;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("number %d and id %d value is %d", number, id, ThreadLocalVariableHolder.get());
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new ThreadLocalTest(i));
		}
		try {
			TimeUnit.SECONDS.sleep(2);
			exec.shutdownNow();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	static class ThreadLocalVariableHolder {
		private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {

			private Random random = new Random(47);

			@Override
			protected Integer initialValue() {
				// TODO Auto-generated method stub
				return random.nextInt(10000);
			}
			
			
			
		};
		
		public static void increment() {
			int val = value.get() + 1;
			value.set(val);
			System.out.println(number + " set value: " + val);
		}
		
		public static int get() {
			int val = value.get();
			System.out.println(number + " get value: " + val);
			return value.get();
		}
	}
}
