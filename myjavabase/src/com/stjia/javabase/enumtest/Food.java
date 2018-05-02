package com.stjia.javabase.enumtest;

/**
 * @author stjia
 * @date 2018年5月1日
 */
public interface Food {
	enum Appetizer implements Food {
		SALAD, SOUP, SPRING_ROLLS;
	}

	enum MainCourse implements Food {
		LASAGNE, BURRITO, PAD_THAI, LENTILS,
		HUMMOUS, VINDALOO;
	}
}
