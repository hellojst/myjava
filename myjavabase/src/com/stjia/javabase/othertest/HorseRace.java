package com.stjia.javabase.othertest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author stjia
 * @time 2018年3月22日
 */
public class HorseRace {
	static final int FINAL_LINE = 70;
	private List<Horse> horses = new ArrayList<>();
	private ExecutorService executorService = Executors.newCachedThreadPool();
	private CyclicBarrier barrier;
	/**
	 * @param horses
	 * @param barrier
	 */
	public HorseRace(int horseCount, final int pause) {
		barrier = new CyclicBarrier(horseCount, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				StringBuffer stringBuffer = new StringBuffer();
				for(int i = 0; i < FINAL_LINE; i++) {
					stringBuffer.append("=");
				}
				System.out.println(stringBuffer);
				for(Horse horse : horses) {
					System.out.println(horse.tracks());
				}
				for(Horse horse : horses) {
					if (horse.getStrides() > FINAL_LINE) {
						System.out.println(horse + "won!");
						executorService.shutdownNow();
						return;
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		for(int i = 0; i < horseCount; i++) {
			Horse horse = new Horse(barrier);
			horses.add(horse);
			executorService.execute(horse);
		}
	}
	
	public static void main(String[] args) {
		int nHourses = 7;
		int pause = 2000;
//		if (args.length > 0) {
//			int n = new Integer(args[0]);
//			nHourses = n > 0 ? n : nHourses;
//		}
//		if (args.length > 1) {
//			int p = new Integer(args[1]);
//			pause = p > -1 ? p :pause;
//		}
		new HorseRace(nHourses, pause);
	}
	
	
}

