package com.stjia.javabase.lintcode;

/**
 * 
 * @author stjia
 * @time 2018年4月12日
 */
public class Singleton {

    /**
     * @return: The same instance of this class every time
     */
    
    private Singleton(){};
    public static Singleton getInstance() {
        // write your code here
        return InHolder.value;
    }
    private static class InHolder{
        public static final Singleton value = new Singleton();
    };
}
