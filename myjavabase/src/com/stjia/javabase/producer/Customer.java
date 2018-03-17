package com.stjia.javabase.producer;

import java.text.MessageFormat;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import javax.xml.crypto.Data;

/**
 * 消费者，用于处理缓冲块中的数据
 * @author Administrator
 *
 */
public class Customer implements Runnable{
	private BlockingQueue<PCData> queue;
	private volatile boolean isRunning = true;
	
	public Customer(BlockingQueue<PCData> queue) {
		// TODO Auto-generated constructor stub
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Customer id = " + Thread.currentThread().getId());
		Random random  = new Random();
		while (true) {
			try {
				PCData data = queue.take();
				if (data != null) {
					int result = data.getData() * data.getData();
					System.out.println("线程ID:" + Thread.currentThread().getId()
							+ "-处理结果:" + MessageFormat.format("{0} x {1} = {2} ", data.getData(),data.getData(),result));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
