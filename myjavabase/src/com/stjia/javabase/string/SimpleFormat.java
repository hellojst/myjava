package com.stjia.javabase.string;

import java.awt.List;
import java.io.PrintStream;
import java.util.Formatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class SimpleFormat {
	public static void main(String[] args) {
		int x = 5;
		double y = 3.141592653;
		System.out.format("Row 1 :[%d, %f]", x, y);
		
		PrintStream pStream = System.out;
		Turtle turtle = new Turtle("tommy", new Formatter(System.out));
		Turtle tonnyturlt = new Turtle("tonny", new Formatter(pStream));
		tonnyturlt.move(1, 2);
		turtle.move(10, 20);
		tonnyturlt.move(5, 9);
		tonnyturlt.move(12, 11);
		Class<?> anyclass = int.class;
		anyclass = double.class;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		queue.offer(2);
		System.out.println(queue.poll());
		HashSet<Integer> set = new HashSet<>();
		
	}
	
	static class Turtle{
		private String name;
		private Formatter f;
		/**
		 * 
		 */
		public Turtle(String name, Formatter f) {
			// TODO Auto-generated constructor stub
			this.name = name;
			this.f = f;
		}
		public void move(int x, int y) {
			f.format("%s turtle is at (%d, %d)\n", name, x ,y);
		}
		
	}

}
