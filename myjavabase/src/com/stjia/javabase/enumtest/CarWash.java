package com.stjia.javabase.enumtest;

import java.util.EnumSet;

/**
 * @author stjia
 * @date 2018年5月1日
 */
public class CarWash {
	enum Cycle {
		UNDER_BODY {

			@Override
			void action() {
				// TODO Auto-generated method stub
				System.out.println("underboey worked");
			}
		},
		BASIC{

			@Override
			void action() {
				// TODO Auto-generated method stub
				System.out.println("basic work");
			}
			
		},
		HOT_WAX{

			@Override
			void action() {
				// TODO Auto-generated method stub
				System.out.println("hot wax worked");
			}

			/* (non-Javadoc)
			 * @see com.stjia.javabase.enumtest.CarWash.Cycle#defaultBehavior()
			 */
			@Override
			void defaultBehavior() {
				// TODO Auto-generated method stub
				System.out.println("这是一个特殊行为！");
			}
			
			
			
		},
		BLOWDRY{

			@Override
			void action() {
				// TODO Auto-generated method stub
				System.out.println("bolwdry worked!");
			}
			
		};
		
		abstract void action();
		void defaultBehavior() {
			System.out.println("默认行为！");
		}
		
		public static Cycle valueof(int i) {
			Cycle[] cycles = values();
			if (i < 0 || i >= cycles.length) {
				throw new IndexOutOfBoundsException("invalid index!");
			}
			return cycles[i];
		}
	}
	
	EnumSet<Cycle> sets = EnumSet.of(Cycle.BASIC);
	
	public void add(Cycle... cycle) {
		for (Cycle subcycle : cycle) {
			sets.add(subcycle);
		}
	}
	
	public void washCar() {
		for (Cycle cycle : sets) {
			cycle.action();
		}
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return sets.toString();
	}

	public static void main(String[] args) {
		CarWash carWash = new CarWash();
		carWash.add(Cycle.BLOWDRY);
		carWash.add(Cycle.UNDER_BODY);
		carWash.add(Cycle.BASIC, Cycle.BLOWDRY, Cycle.BLOWDRY, Cycle.HOT_WAX);
		carWash.washCar();
		System.out.println(carWash);
		int i = Cycle.UNDER_BODY.ordinal();
		System.out.println(Cycle.UNDER_BODY.ordinal());
		Cycle cycle = Cycle.valueof(i);
		System.out.println(cycle);
		System.out.println(Cycle.values()[Cycle.HOT_WAX.ordinal()]);
	}
}
