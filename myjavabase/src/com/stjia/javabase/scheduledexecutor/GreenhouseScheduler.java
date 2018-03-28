package com.stjia.javabase.scheduledexecutor;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 调度线程
 * @author stjia
 * @time 2018年3月21日
 */
public class GreenhouseScheduler {

	private volatile boolean light = false;
	private volatile boolean water = false;
	//恒温器
	private String thermostat = "day";
	ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);
	private Calendar lasttime = Calendar.getInstance();
	{
		lasttime.set(Calendar.MINUTE, 30);
		lasttime.set(Calendar.SECOND, 00);
	}
 	private float lastTemp = 65.0f;
 	private int tempDirection = +1;
 	private float lastHumidity = 50.0f;
 	private int humidityDirection = +1;
 	private Random random = new Random();
 	List<DataPoint> data = Collections.synchronizedList(new ArrayList<>());
 	
 	public static void main(String[] args) {
 		GreenhouseScheduler gh = new GreenhouseScheduler();
 		gh.scheduled(gh.new TerMinate(), 5000);
 		gh.repeat(gh.new Bell(), 0, 1000);
 		gh.repeat(gh.new ThermostatNight(), 0, 2000);
 		gh.repeat(gh.new LightOn(), 0, 200);
 		gh.repeat(gh.new LightOff(), 0, 400);
 		gh.repeat(gh.new WaterOn(), 0, 600);
 		gh.repeat(gh.new waterOff(), 0, 800);
 		gh.repeat(gh.new ThermostatDay(), 0, 1400);
 		gh.repeat(gh.new CollectData(), 500, 500);
 	}
	
	public synchronized String getThermostat() {
		return thermostat;
	}
	public synchronized void setThermostat(String thermostat) {
		this.thermostat = thermostat;
	}
	
	public void scheduled(Runnable runnable, long delay) {
		scheduler.schedule(runnable, delay, TimeUnit.MILLISECONDS);
	}
	
	public void repeat(Runnable runnable, long initialDelay, long period) {
		scheduler.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.MILLISECONDS);
	}
	
	static class DataPoint{
		final Calendar time;
		final float temperature;
		final float humidity;
		/**
		 * @param time
		 * @param temperature
		 * @param humidity
		 */
		public DataPoint(Calendar time, float temperature, float humidity) {
			super();
			this.time = time;
			this.temperature = temperature;
			this.humidity = humidity;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return time.getTime() + String.format("temperature: %1$.1f humidity : %2$.2f", temperature, humidity);
		}
		
	}
	
	class LightOn implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Turning on light!");
			light = true;
		}
	}
	
	class LightOff implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("turn off light!");
			light = false;
		}
		
	}

	class WaterOn implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("water on!");
			water = true;
		}
		
	}
	
	class waterOff  implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("water off!");
			water = false;
		}
		
	}
	
	class ThermostatNight implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Thermostat turn night setting");
			setThermostat("night");
		}
		
	}
		
	class ThermostatDay implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Thermostat turn Day setting");
			setThermostat("day");
		}
		
	}
	
	class Bell implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Alm : bing!");
		}
		
	}
	
	class TerMinate implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Terminating!");
			scheduler.shutdownNow();
			new Thread() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					for (DataPoint dataPoint : data) {
						System.out.println(dataPoint);
					}
				}
				
			}.start();
		}
		
	}
	
 	class CollectData implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("collecting data");
			synchronized (GreenhouseScheduler.this) {
				lasttime.set(Calendar.MINUTE, lasttime.get(Calendar.MINUTE) + 30);
				if (random.nextInt(5) == 4) {
					 tempDirection = - tempDirection;
					 humidityDirection = - humidityDirection;
				}
				lastTemp = lastTemp + tempDirection;
				lastHumidity = lastHumidity + humidityDirection*random.nextFloat();
				data.add(new DataPoint((Calendar)lasttime.clone(), lastTemp, lastHumidity));
			}
		}
 		
 	}
}
