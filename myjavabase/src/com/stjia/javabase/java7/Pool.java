package com.stjia.javabase.java7;

import java.awt.Checkbox;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author stjia
 * @date 2018年5月5日
 */
public class Pool<T> {
	private static int id = 0;
	private int size;
	private List<T> items = new ArrayList<>();
	private volatile boolean[] isCheckOut; // boolean 默认为false；未检出
	private Semaphore available;

	/**
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 */
	public Pool(Class<T> classObject, int size) {
		// TODO Auto-generated constructor stub
		this.size = size;
		isCheckOut = new boolean[size];
		available = new Semaphore(size, true); // 使用公平锁
		for (int i = 0; i < size; i++) {
			try {
				items.add(classObject.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 
	 */
	public Pool() {
		// TODO Auto-generated constructor stub
		id++;
	}

	public T checkOut() throws InterruptedException {
		available.acquire();
		return getItem();
	}

	public void checkIn(T item) {
		if (release(item)) {
			available.release();
		}
	}

	private synchronized T getItem() {
		for (int i = 0; i < size; i++) {
			if (!isCheckOut[i]) {
				isCheckOut[i] = true;
				return items.get(i);
			}
		}
		return null; // 表示没有可用的，已全部检出 实际应用中正常是达不到此处的，isCheckOut和items等长，若全部检出会阻塞在acquire处
	}

	private synchronized boolean release(T item) {
		int index = items.indexOf(item);
		if (index == -1)
			return false; // 在列表中找不到
		if (isCheckOut[index]) { // 若此item是检出状态，则设为未出
			isCheckOut[index] = false;
			return true;
		}
		return false; // 若是未检出状态，应该是不对的
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Pool + " + id;
	}


	public static void main(String[] args) {
		Pool<Pool> stringPools = new Pool<>(Pool.class, 7);
		List<Pool> lStrings = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			try {
				lStrings.add(stringPools.checkOut());
				if (i % 3 == 0) {
					stringPools.checkIn(lStrings.get(i));
					System.out.println(lStrings.get(i));
				}
				System.out.println(lStrings);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
