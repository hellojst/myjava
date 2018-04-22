import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author stjia
 * @time 2018年4月4日
 */
public class StrTest {
	public static void main(String[] args) {
		String tString = "hh".intern();
		String s1 = "a" + tString;
		s1.intern();
		String s2 = "ahh";
		System.out.println(s1 == s2);
	}
}
