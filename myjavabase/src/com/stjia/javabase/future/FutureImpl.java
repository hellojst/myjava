package com.stjia.javabase.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.stjia.javabase.bean.DataBean;

public class FutureImpl {
	public void futureTest() throws InterruptedException, ExecutionException {
		FutureTask<DataBean> futureTask = new FutureTask<>(new RealData("s"));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.submit(futureTask);
		System.out.println("请求完毕！");
		System.out.println("开始执行其他");
		Thread.sleep(5000);
		System.out.println("其他任务执行完毕，等待realdata数据");
		System.out.println(futureTask.get().getStr());
		System.out.println("其他数据执行完毕");
	}

}
