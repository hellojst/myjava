import java.io.IOException;
import java.util.Scanner;

/**
 * 守护线程
 * @author stjia
 * @time 2018年3月18日
 */
public class ResponsiveUI extends Thread {
	private static  double d = 1;
	public ResponsiveUI () {
		setDaemon(true);
		start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			d = d + (Math.PI + Math.E);
		}
	}
	
	public static void main(String[] args) throws IOException {
		new ResponsiveUI();
		System.in.read();
		System.out.println(d);
	}

}
