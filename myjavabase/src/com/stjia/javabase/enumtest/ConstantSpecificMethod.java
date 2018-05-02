package com.stjia.javabase.enumtest;

import java.util.EnumSet;

/**
 * @author stjia
 * @date 2018年5月1日
 */
public enum ConstantSpecificMethod {
	
	DATE_TIME{

		@Override
		String getInfo() {
			// TODO Auto-generated method stub
			return "this is date time!";
		}
		
	},
	CLASSPATH{

		@Override
		String getInfo() {
			// TODO Auto-generated method stub
			return "this is classpath string!";
		}
		
	};

	
	abstract String getInfo();
	
	public static void main(String[] args) {
		for (ConstantSpecificMethod csm : ConstantSpecificMethod.values()) {
			System.out.println(csm.getInfo());
		}
	}
}
