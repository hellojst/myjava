package com.stjia.javabase.guardedsuspension;

public class ClientThread extends Thread{
    private RequsetQueue requsetQueue; 
    
    public ClientThread(RequsetQueue queue, String name) {
		// TODO Auto-generated constructor stub
    	super(name);
    	this.requsetQueue = queue;
    	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			Request request = new Request("RequestID:" + i + ",RequestThreadName: " + Thread.currentThread().getName());
			System.out.println("Thread " + Thread.currentThread().getName() + request);
			
			requsetQueue.addRequest(request);
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
    
}
