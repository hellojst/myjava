package com.stjia.javabase.collector;

import java.util.Random;

/**
 * @author stjia
 * @date 2018年4月24日
 */
public class MapDataGenerator implements Generator<Pair<Integer, String>>{
	
	
	private int i;
	private String s = "s";
	
	/**
	 * 
	 */
	public MapDataGenerator() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.stjia.javabase.collector.Generator#next()
	 */
	@Override
	public Pair<Integer, String> next() {
		// TODO Auto-generated method stub
		return new Pair<Integer, String>(i++, s + i);
		
	}

}
