package com.stjia.javabase.threadpool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 线程池
 * @author stjia
 * @time 2018年3月11日
 */
public class ThreadPool {
	
	private static ThreadPool instance = null;
	//空闲线程队列
	private Queue<PTThread> idlesThread;
	//已有线程总数
	private int threadCount;
	private boolean isShutDown = false;
	
	private ThreadPool() {
		idlesThread = new ConcurrentLinkedQueue();
		threadCount = 0;
	}
	
	public static ThreadPool getInstance() {
		if (instance == null) {
			return new ThreadPool();
		}
		return instance;
	}
	
	public void repool(PTThread rePoolingThread) {
		if (!isShutDown) {
			idlesThread.add(rePoolingThread);
		} else {
			//关闭线程池时如该线程尚未在队列中，就把其状态设为关闭
			rePoolingThread.shutdown();
//			idlesThread.add(rePoolingThread);
		}
	}
	
	public synchronized void shutdown() {
		for (PTThread ptThread : idlesThread) {
			ptThread.shutdown();
		}
		//设置状态
		isShutDown = true;
	} 
	
	public synchronized void start(Runnable target) {
		isShutDown = false;
		PTThread thread = null;
		//先从空闲队列中取，若空闲队列中没有就新建线程
		if (idlesThread.size() > 0) {
			thread = idlesThread.poll();
			thread.setTarget(target);
			System.out.println("使用线程池中已有的线程" + thread.getName());
		} else {
			threadCount++;
			thread = new PTThread(target, "PTthrea#"+threadCount, this);
			System.out.println("创建线程--" + thread.getName());
			//初次创建线程需手动启动线程
			thread.start();
		}
	}
	

	
	public int getThreadCount() {
		return threadCount;
	}
}
