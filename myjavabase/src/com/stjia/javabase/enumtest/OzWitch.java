package com.stjia.javabase.enumtest;

import com.stjia.javabase.enumtest.Food.Appetizer;
import com.stjia.javabase.enumtest.Food.MainCourse;

/**
 * @author stjia
 * @date 2018年5月1日
 */
public enum OzWitch {
	WEST("this is west"),
	NORTH("this is north"),
	EAST("this is east"),
	SOUTH("this is south");
	
	private String des;
	
	private OzWitch (String des) {
		this.des = des;
	}
	
	public String getDescription() {
		return des;
	};
	public static void main(String[] args) {
		for (OzWitch ozWitch : OzWitch.values()) {
			System.out.println(ozWitch + ": " + ozWitch.getDescription());
		}
		
		Food food = Appetizer.SALAD;
		System.out.println(food);
		food = MainCourse.BURRITO;
		System.out.println(food);
	}
}
