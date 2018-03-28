package com.stjia.javabase.lamda;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型处理类
 * @author stjia
 * @time 2018年3月28日
 */
public class MyFilter {
	/**
	 * lamda函数测试
	 * @param list 待排序数组
	 * @param  函数，其test方法对该函数有用，所以使用时可用lamada表达式直接将test方法所需参数->该方法的实现 传入即可
	 * @return
	 */
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
    	List<T> result = new ArrayList<>();
    	for (T t : list) {
			if (p.test(t)) {
				result.add(t);
			}
		}
    	return result;
    }
    
    
}
