package com.stjia.javabase.stream;

import java.awt.image.ByteLookupTable;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

import javax.xml.parsers.FactoryConfigurationError;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * @author stjia
 * @date 2018年4月28日
 */
public class StreamUtils {

	private static final int BSIZE = 1024;

	/**
	 * 缓冲输入文件
	 * 
	 * @param name
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String useBufferdInputFile(String name) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(name))) {
			StringBuilder stringBuilder = new StringBuilder();
			String s;
			while ((s = br.readLine()) != null) {
				stringBuilder.append("\n"); // readline 遇到\n,\r就会停止，所以readline中不会包含\n \r
				stringBuilder.append(s);
			}
			System.out.println(stringBuilder.toString());
			return stringBuilder.toString();
		}
	}

	/**
	 * 从内存输入 实现回显功能，system.in: 一个空行或ctrl-z结束程序
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void useMemoryInput(String name) throws FileNotFoundException, IOException {
		try (StringReader sr = new StringReader(useBufferdInputFile(name))) {
			int c;
			while ((c = sr.read()) != -1) {
				System.out.print((char) c);

			}
		}
	}

	/**
	 * 格式化的内存输入
	 * 
	 * @param name
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void useFormattedMemory(String name) throws FileNotFoundException, IOException {
		try (DataInputStream dis = new DataInputStream(
				new ByteArrayInputStream(useBufferdInputFile(name).getBytes()))) {
			while (dis.available() > 0) {
				char c = dis.readChar();
				System.out.println(c);
			}
		}
	}

	/**
	 * DataInputStream 可以写入读取大部分格式字符
	 * 
	 * @param name
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void dataFromatterInput(String name) throws FileNotFoundException, IOException {
		try (DataInputStream dis = new DataInputStream(new FileInputStream(name));
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(name))) {
			dos.writeInt(10);
			dos.writeByte('a');
			dos.writeBytes("shi界赛");
			dos.writeUTF("世界赛");
			dos.writeChars("字符");

			System.out.println(dis.readInt());
			System.out.println((char) dis.readByte());
			// 读字符串
			byte[] bs = new byte[5];
			dis.read(bs);
			System.out.println(new String(bs, 0, 5));
			System.out.println(dis.readUTF());
			char[] ch = new char[2];
			for (int i = 0; i < 2; i++) {
				ch[i] = dis.readChar();
			}
			System.out.println(new String(ch, 0, 2));
		}

	}

	public static void echo() throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String s;
			while ((s = br.readLine()) != null && s.length() != 0) {
				System.out.println(s);
			}
		}
	}

	public static void channelTest() throws FileNotFoundException, IOException {
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		try (FileChannel fc = new FileOutputStream("channel.txt").getChannel()) {
			fc.write(ByteBuffer.wrap("some text..".getBytes()));
			fc.close();
		}
		try (FileChannel fc2 = new RandomAccessFile("channel.txt", "rw").getChannel();) {
			fc2.position(fc2.size()); /// move to the end
			fc2.write(ByteBuffer.wrap("next more;".getBytes()));
			fc2.close();
		}
		try (FileChannel fc3 = new FileInputStream("channel.txt").getChannel()) {
			fc3.read(buffer);
			buffer.flip(); // 准备写
			while (buffer.hasRemaining()) {
				System.out.print((char) buffer.get());
			}
		}
		try (FileChannel fc4 = new FileOutputStream("channel2.txt").getChannel()) {
			buffer.clear();
//			buffer.asCharBuffer().put("abc字符测试");
			buffer.asCharBuffer().put("abc字符测试");
			CharBuffer cb = buffer.asCharBuffer();
			cb.put("abc字符测试");
			fc4.write(buffer);
			fc4.close();
		}
		try (FileChannel fc5 = new FileInputStream("channel2.txt").getChannel()) {
			CharBuffer cb2 = buffer.asCharBuffer();
			buffer.clear();
			fc5.read(buffer);
			buffer.flip();
			System.out.println(buffer.asCharBuffer());

		}

	}
}
