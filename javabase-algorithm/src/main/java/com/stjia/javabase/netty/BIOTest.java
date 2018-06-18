package com.stjia.javabase.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class BIOTest {
	
	public static void ServerSocketTest(String host, int port) throws IOException {
		//监听指定端口请求
		ServerSocket serverSocket = new ServerSocket(port);
		//对acept的方法调用将被阻塞，直到一个连接建立
		Socket clientSocket = serverSocket.accept();
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), Charset.forName("utf-8")));
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		String request, response;
		while ((request = in.readLine()) != null) {
			if ("Done".equals(request)) {
				break;
			}
			response = processRequest(request);  //请求传递给服务器处理并返回处理结果
			out.print(response); //将处理后的结果返回给客户端
		}
	}
	
	private static String processRequest(String request) {
		return "已处理：" + request;
	} 
}
