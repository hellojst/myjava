package com.stjia.javabase.threadpool;

/**
 * 定义自己的target线程
 * @author stjia
 * @time 2018年3月11日
 */
public class MyThread implements Runnable {
	
	private String name;
	
	/**
	 * 
	 */
	public MyThread(String name) {
		// TODO Auto-generated constructor stub
		
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//用sleep代替一个功能的知性
			Thread.sleep(1);
			System.out.println(name + " :执行完毕");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
