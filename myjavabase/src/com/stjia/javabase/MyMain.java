package com.stjia.javabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import com.stjia.javabase.guardedsuspension.ClientThread;
import com.stjia.javabase.guardedsuspension.RequsetQueue;
import com.stjia.javabase.guardedsuspension.ServerThread;
import com.stjia.javabase.masterworker.Master;
import com.stjia.javabase.masterworker.PlusWorker;
import com.stjia.javabase.producer.Customer;
import com.stjia.javabase.producer.PCData;
import com.stjia.javabase.producer.Producer;
import com.stjia.javabase.threadpool.MyThread;
import com.stjia.javabase.threadpool.ThreadPool;

public class MyMain {
	public static void main(String[] args) {
//		//1. future模式
//		FutureImpl futureImpl = new FutureImpl();
//		try {
//			futureImpl.futureTest();
//		} catch (InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		//2. work-master模式
//		Master master = new Master(new PlusWorker(), 4);
//		//提交每一个子任务
//		for (int i = 0; i < 100; i++) {
//			master.submit(i);
//		}
//		//启动线程
//		master.execute();
//		int finalResult = 0;
//		Map<String, Object> results = master.getResultMap();
//		while (!results.isEmpty() || !master.isAllComplete()) {
//			List<String> keys = new ArrayList<>();
//			keys.addAll(results.keySet());
//			for (String key : keys) {
//				int result = (int) results.get(key);
//				finalResult = finalResult + result;
//				results.remove(key);
//			}
//		}
//		System.out.println("总结果为： " + finalResult);
		
		
//		//3. Guarded Suspension模式
//		RequsetQueue requsetQueue = new RequsetQueue();
//		//先让服务端跑起来
//		for (int i = 0; i < 5; i++) {
//			new ServerThread(requsetQueue, "Server Thread-" + i).start();
//		}
//		//客户端在发送请求
//		for (int i = 0; i < 10; i++) {
//			new ClientThread(requsetQueue, "Client Thread-" + i).start();
//		}
		
		
		
//		//4. 生产者-消费者模式
//		BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(10); //建立缓冲区
//		Producer producer1 = new Producer(queue);
//		Producer producer2 = new Producer(queue);
//		Producer producer3 = new Producer(queue);
//		Customer customer1 = new Customer(queue);
//		Customer customer2 = new Customer(queue);
////		Customer customer3 = new Customer(queue);
//		ExecutorService service = Executors.newCachedThreadPool(); //建立线程池
//		//启动生产者
//		service.execute(producer1);
//		service.execute(producer2);
//		service.execute(producer3);
//		//启动消费者
//		service.execute(customer1);
//		service.execute(customer2);
////		service.execute(customer3);
//		try {
//			Thread.sleep(10*1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("thread is interrupted!");
//		}
//		//停止生产者
//		producer1.stop();
//		producer2.stop();
//		producer3.stop();
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			System.out.println("thread is interrupted!");
//			e.printStackTrace();
//		}
//		
//		service.shutdown();
		
		
		//5. 线程池
		ThreadPool threadPool = ThreadPool.getInstance();
		for (int i = 0; i < 100; i++) {
			threadPool.start(new MyThread("Test ThreadPool - " + i));
		}
		try {
			Thread.sleep(5000);;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadPool.shutdown();
		try {
			Thread.sleep(5000);;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("线程池个数：" + threadPool.getThreadCount());
		
		for (int i = 0; i < 50; i++) {
			threadPool.start(new MyThread("Test next ThreadPool - " + i));
		}
		
		
		
		                                 
	}
}
