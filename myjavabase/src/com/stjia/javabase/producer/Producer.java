package com.stjia.javabase.producer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者-消费者模式
 * 生产者类，负责向内存缓存区中注入数据
 * @author Administrator
 *
 */
public class Producer implements Runnable {
	private volatile boolean isRunning = true;
	private BlockingQueue<PCData> queue; //内存缓冲区
	private static AtomicInteger count = new AtomicInteger();
	private static final int SLEEPTIME = 1000;
	
	public Producer(BlockingQueue<PCData> queue) {
		// TODO Auto-generated constructor stub
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		PCData pcData = null;
		Random random = new Random();
		System.out.println("producer id : " + Thread.currentThread().getId());
		while (isRunning) {
			try {
				Thread.sleep(random.nextInt(SLEEPTIME));
				pcData = new PCData(count.incrementAndGet());
				System.out.println(pcData + "-put into block queue.");
				if (!queue.offer(pcData, 2, TimeUnit.SECONDS)) {
					System.out.println("线程ID:" + Thread.currentThread().getId() + "将" + pcData + "- put into block queue failed!");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(Thread.currentThread().getId() + " interrupted");
			}
			
		}
	}
	
	public void stop() {
		isRunning = false;
	}	
}
