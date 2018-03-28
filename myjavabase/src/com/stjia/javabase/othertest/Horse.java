package com.stjia.javabase.othertest;

import java.io.ObjectInputStream.GetField;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 赛马
 * 
 * @author stjia
 * @time 2018年3月22日
 */
public class Horse implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private int strides = 0;
	private static Random rand = new Random(47);
	private static CyclicBarrier barrier;

	/**
	 * 构造函数
	 */
	public Horse(CyclicBarrier barrier) {
		// TODO Auto-generated constructor stub
		this.barrier = barrier;
	}

	/**
	 * @return the strides
	 */
	public synchronized int getStrides() {
		return strides;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Horse " + id + " ";
	}

	public String tracks() {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < getStrides(); i++) {
			stringBuffer.append("*");
		}
		stringBuffer.append(id);
		return stringBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					strides += rand.nextInt(3);
				}
			}
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
