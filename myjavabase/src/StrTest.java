import static org.hamcrest.CoreMatchers.instanceOf;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
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
	private static boolean a;
	public static void main(String[] args) {
		String tString = "hh".intern();
		String s1 = "a" + tString;
		s1.intern();
		String s2 = "ahh";
		System.out.println(s1 == s2);
		System.out.println(a);
		 //随便写写
//		Set<Integer> set  = new LinkedHashSet<Integer>();
//		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
//		Collections.emptyList()	;	
//		Map map = new HashMap<>();
//		HashSet<Integer> hashSet = new HashSet<>();
//		TreeSet< Integer> treeSet = new TreeSet<>();
//		try {
//			StrTest.class.getConstructor().newInstance();
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//				| NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
