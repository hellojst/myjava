package com.stjia.mvnjava.javabase_algorithm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author stjia
 * @date 2018年6月2日
 */
class LeetCodeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.stjia.mvnjava.javabase_algorithm.LeetCodeUtils#Mi(long, int)}.
	 */
	@Test
	void testMi() {
		assertEquals(2, LeetCodeUtils.Mi(2, 1));
		assertEquals(4, LeetCodeUtils.Mi(2, 2));
		assertEquals(8, LeetCodeUtils.Mi(2, 3));
		assertEquals(1, LeetCodeUtils.Mi(2, 0));
		assertEquals(0.5, LeetCodeUtils.Mi(2, -1));
		assertEquals(0.25, LeetCodeUtils.Mi(2, -2));
	}
	
	@Test
	void testreverseInt() {
		assertEquals(12, LeetCodeUtils.reverseInt(210));
		assertEquals(1205, LeetCodeUtils.reverseInt(5021));
		assertEquals(-12, LeetCodeUtils.reverseInt(-2100));
		assertEquals(0, LeetCodeUtils.reverseInt(1345678903));
	}
	
	@Test
	void testmaxProfit4Once() {
		assertEquals(1, LeetCodeUtils.maxProfit4Once(new int[] {1,2}));
	}

}
