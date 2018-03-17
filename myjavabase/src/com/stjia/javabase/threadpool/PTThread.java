package com.stjia.javabase.threadpool;

/**
 * 代理线程，该线程启动后为死循环会一直等待有任务加入被唤醒
 * @author Administrator
 *
 */
public class PTThread extends Thread {
	
	private ThreadPool threadPool;
	private Runnable target;
	//标识位：标识线程为空闲还是工作状态
	private boolean isIdle = false;
	private boolean isShutdown = false;
	

	public PTThread(Runnable target, String name, ThreadPool threadPool) {
		// TODO Auto-generated constructor stub
		super(name);
		this.target = target;
		this.threadPool = threadPool;
	}
	
	public synchronized void setTarget(Runnable newTarget) {
		target = newTarget;
		notifyAll();
	}
	
	public synchronized void shutdown() {
		isShutdown = true;
		notifyAll();
	}

	/**
	 * @return the isIdle
	 */
	public boolean isIdle() {
		return isIdle;
	}
	
	/**
	 * @return the target
	 */
	public Runnable getTarget() {
		return target;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!isShutdown) {
			isIdle = false;
			if (target != null) {
				target.run();
			}
			//任务结束后状态设为闲置
			isIdle = true;
			//将闲置的线程放入线程池中,而非关闭
			threadPool.repool(this);
			synchronized (this) {
				try {
					//等待新任务加入再被setTarget()中的notifyAll唤醒
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//当wait状态被打破，即被唤醒时将状态标识位设为false
			isIdle = false;
			
		}
	}
	
}
