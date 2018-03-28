package com.stjia.javabase.lamda;

/**
 * 
 * @author stjia
 * @time 2018年3月28日
 */
public class Apple {
	private int weight;
	private String color;
	/**
	 * @param weight
	 * @param color
	 */
	public Apple(int weight, String color) {
		super();
		this.weight = weight;
		this.color = color;
	}
	public int getWeight() {
		return weight;
	}
	public String getColor() {
		return color;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "this apple weight:" + weight + ",color:" + color;
	}
	
	
}
