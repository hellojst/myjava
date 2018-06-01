package com.stjia.javabase.stream;

import java.io.IOException;

/**
 * @author stjia
 * @date 2018年4月28日
 */
public class MyStreamTest {
	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String path = "src\\com\\stjia\\javabase\\stream\\testText.txt";
			String path2 = "src/com/stjia/javabase/stream/MyStreamTest.java";
			String path3 = "classpathtext";
			StreamUtils.useBufferdInputFile(path);
			StreamUtils.useMemoryInput(path2);
			StreamUtils.dataFromatterInput(path3);
//			StreamUtils.echo();
			StreamUtils.channelTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
