package com.stjia.javabase.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author stjia
 * @time 2018年3月25日
 */
public class JDObserver extends Observable {
	
	

	@Override
	public synchronized void addObserver(Observer o) {
		// TODO Auto-generated method stub
		super.addObserver(o);
	}

	@Override
	public synchronized void deleteObserver(Observer o) {
		// TODO Auto-generated method stub
		super.deleteObserver(o);
	}

	@Override
	public synchronized void deleteObservers() {
		// TODO Auto-generated method stub
		super.deleteObservers();
	}
	

}
