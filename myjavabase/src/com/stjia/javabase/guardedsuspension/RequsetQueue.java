package com.stjia.javabase.guardedsuspension;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 任务请求队列，封装所有请求
 * @author Administrator
 *client进程往队列中加要处理的任务， server进程从队列中取要处理的任务
 */
public class RequsetQueue {

	private Queue<Request> queue = new ConcurrentLinkedQueue<Request>();
	
	public synchronized Request getRequest() {
		if (queue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return queue.poll();
	}
	
	public synchronized void addRequest(Request request) {
		queue.add(request);
		notifyAll();
	}
}
