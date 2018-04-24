package com.stjia.javabase.collector;

import java.util.LinkedHashMap;

/**
 * @author stjia
 * @date 2018年4月24日
 */
public class MapData<K, V> extends LinkedHashMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7261893316582095327L;

	/**
	 * 
	 */
	public MapData(Generator<Pair<K, V>> gen, int quantity) {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < quantity; i++) {
			Pair<K, V> pair = gen.next();
			put(pair.key, pair.value);
		}
	}
	
	public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), genV.next());
		}
	}
	
	/**
	 * 
	 */
	public MapData(Generator<K> genk, V value, int quantity) {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < quantity; i++) {
			put(genk.next(), value);
		}
	}
	
}
