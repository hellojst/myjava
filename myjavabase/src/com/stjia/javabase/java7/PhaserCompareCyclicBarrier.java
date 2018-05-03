package com.stjia.javabase.java7;

import java.util.concurrent.Phaser;

/**
 * @author stjia
 * @date 2018年5月3日
 */
public class PhaserCompareCyclicBarrier {

	public static void main(String[] args) {

		Phaser phaser = new Phaser(3) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.concurrent.Phaser#onAdvance(int, int)
			 */
			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				// TODO Auto-generated method stub
				System.out.println("\n=========华丽的分割线===========");
				return registeredParties == 1;
			}

		};
		System.out.println("程序开始执行"); //创建并启动三个线程
		for (int i = 0; i < 3; i++) { //只要phaser不终结，主线程就循环等待
			new MyTestThread((char) (97 + i), phaser).start();
		}
		
		phaser.register(); //将主线程主动增加到phaser中，此语句执行后phasr共管理4个线程
		while (!phaser.isTerminated()) {
			int n = phaser.arriveAndAwaitAdvance();
		}
		//跳出上方循环后就意味着phaser终结，即三个工作线程已经结束
		System.out.println("程序结束");
	}

	static class MyTestThread extends Thread {
		private char c;
		private Phaser phaser;

		/**
		 * 
		 */
		public MyTestThread(char c, Phaser phaser) {
			// TODO Auto-generated constructor stub
			this.c = c;
			this.phaser = phaser;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!phaser.isTerminated()) { // 将当前字母打印10次
				for (int i = 0; i < 10; i++) {
					System.out.print(c + "");
				}
				// 打印完当前字母后，将其更新为其后第三个字母，
				c = (char) (c + 3);
				if (c > 'z') {
					// 如果超出了字母z,则在phaser中动态减少一个线程，并退出循环结束本线程
					// 当三个工作线程都执行完此语句，phaser就只剩一个主线程
					phaser.arriveAndDeregister();
					break;
				} else {
					// 反之，等待其他线程到达终点，再一起进入下个阶段
					phaser.arriveAndAwaitAdvance();
				}
			}
		}

	}
}
