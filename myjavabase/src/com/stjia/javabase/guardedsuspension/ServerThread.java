package com.stjia.javabase.guardedsuspension;

public class ServerThread extends Thread {
	private RequsetQueue requestQueue;
	
	public ServerThread(RequsetQueue queue, String name) {
		// TODO Auto-generated constructor stub
		super(name);
		this.requestQueue = queue;
	}
	
	private void handle(Request request) {
		System.out.println("Thread:" + Thread.currentThread().getName() + "-handle request" + request);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Request request = requestQueue.getRequest();
			handle(request);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
