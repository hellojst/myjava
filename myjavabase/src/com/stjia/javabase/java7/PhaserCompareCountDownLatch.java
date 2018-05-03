package com.stjia.javabase.java7;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 同步工具Phaser代替countdownlatch实例 Phaser类同步工具，可替代倒数闸门和循环屏蔽
 * 
 * @author stjia
 * @date 2018年5月3日
 */
public class PhaserCompareCountDownLatch {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(1); // 此处可使用countDownLatch(1);
		for (int i = 0; i < 3; i++) {
			new MyThread((char) (97 + i), phaser).start();
		}
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			phaser.arrive();
	}

	static class MyThread extends Thread {

		private char c;
		private Phaser phase;

		/**
		 * 
		 */
		public MyThread(char c, Phaser phaser) {
			// TODO Auto-generated constructor stub
			this.c = c;
			this.phase = phaser;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			phase.awaitAdvance(phase.getPhase()); // 此处可使用latch.await
			for (int i = 0; i < 100; i++) {
				System.out.print(c + "");
				if (i % 10 == 9) {
					System.out.println();
				}
			}
		}

	}
}
