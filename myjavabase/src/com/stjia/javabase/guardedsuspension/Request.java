package com.stjia.javabase.guardedsuspension;
/**
 * 发送请求的数据封装对象
 * @author stjia
 *
 */
public class Request {
     private String name;
     
     public Request() {};
     
     public Request(String name) {
    	 this.name = name;
     }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
     
    public String toString() {
    	return "[Request " + name + "]";
    }
     
     
}
