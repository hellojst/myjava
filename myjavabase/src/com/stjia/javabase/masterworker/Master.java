package com.stjia.javabase.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
	//任务队列
	protected Queue<Object> workQueue = new ConcurrentLinkedQueue<>();
	//Worker队列
	protected Map<String, Thread> threadMap = new HashMap<>();
	//子任务处理结果集
	protected Map<String, Object> resultMap = new ConcurrentHashMap<>();
	
	public Master(Worker worker, int workerCount) {
		//将每个小任务放到任务队列中去
		worker.setWorkQueue(workQueue);
		worker.setResultMap(resultMap);
		for (int i = 0; i < workerCount; i++) {
			threadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
		}
	}
		
	public boolean isAllComplete() {
		for (Entry<String, Thread> entry : threadMap.entrySet()) {
			if (entry.getValue().getState() != Thread.State.TERMINATED) {
				return false;
			}
		}	
		return true;
	}
	
	public void submit(Object object) {
		workQueue.add(object);
	}
	
	public void execute() {
		for (Entry<String, Thread> entry : threadMap.entrySet()) {
			entry.getValue().start();
			System.out.println("线程--" + entry.getValue().getName() + "：启动");
		}
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
}
