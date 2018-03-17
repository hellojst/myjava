package com.stjia.javabase.masterworker;

public class PlusWorker extends Worker{

	@Override
	public Object handle(Object input) {
		// TODO Auto-generated method stub
		Integer i = (Integer) input;
		return i*i*i;
	}
	
}
