package com.user.stjia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;

public class NIOtest {
	public void makeFile() {
		String path = "D:\\test\\ss\\a.txt";
		File file = new File(path);
		if (!file.exists()) {
			try {
				makeParFile(file.getParentFile());
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("创建文件成功");
		}
		
		writeStr2File(file, path);
		readFileStr(file);
	}
	
	public void makeParFile(File dir) {
		Stack<File> dirStack = new Stack<>();
		
		if (!dir.exists()) {
			dirStack.push(dir);
			dir = dir.getParentFile();
		}
		
		if (!dirStack.empty()) {
			dirStack.pop().mkdirs();
		}
	}
	
	public void writeStr2File(File file, String str) {
		try {
			str = str + "\n\n" + "i love chun" + "\n\r\t春春";
			FileWriter fWriter = new FileWriter(file);
			fWriter.write(str);
			fWriter.flush();
			fWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Autowired
	public void readFileStr(File file) {
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			StringBuffer string = new StringBuffer();
			if (bReader.readLine() != null) {
				string.append(bReader.readLine());
				System.out.println(string);
			}
			bReader.close();
			System.out.println(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
