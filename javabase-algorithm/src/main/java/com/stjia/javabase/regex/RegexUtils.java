package com.stjia.javabase.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式不需要转义\的有： \b \t \r \n \f \\ \' \" 其余均需要用\\来转义出\预制搭配
 * @author stjia
 * @date 2018年5月25日
 */
public class RegexUtils {
	
	/**
	 * 一个简单的正则匹配测试
	 */
	public static void simpleRegexTest() {
		String mytext = "this is my 2st test string";
		String myRegex = "\\d+\\w+"; 
		Pattern pattern = Pattern.compile(myRegex);
		Matcher matcher = pattern.matcher(mytext);
//		matcher.usePattern(pattern);
		if (matcher.find()) {
			String matchedText = matcher.group();
			int matchedFrom = matcher.start(); //包括改索引
			int matchedTo = matcher.end(); //不包括该索引   匹配长度则为 matchedTo-matchedFrom
			System.out.println("matched [" + matchedText + "]" + 
					" from " + matchedFrom + " to " + matchedTo);
		} else {
			System.out.println("don't match");
		}
	}
	
	/**
	 * 匹配
	 * @param regex
	 * @param content
	 */
	public static void matcherWithRegex(String regex, String content) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()) {
			System.out.println("match [" + matcher.group() + "]");
		}
	}
	
	public static void matcherAndLookingAt() {
		String regex = "\\w+";
		String context = "this is my 2st test string";
//		boolean matcherResult = Pattern.matches(regex, context);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(context);
		boolean matcherResult = matcher.matches();
		boolean lookingatResult = matcher.lookingAt();
		System.out.println("matcher result: " + matcherResult + "; looking at result : " + lookingatResult + ";");
	}
	
	public static void matchURL(String url) {
		String regex = "(?x) ^(https?)://([^/:]+)(?::(\\d+))?"; // 类似 https://www.baidu.com/baidu?tn=monline_3_dg&ie=utf-8&wd=菜鸟教程
		Matcher matcher = Pattern.compile(regex).matcher(url);
		
		if (matcher.find()) {
			System.out.println(
					"Overall [" + matcher.group() + "]" + 
					" (from " + matcher.start() + " to " + matcher.end() + ")\n" + 
					"Protocol [" + matcher.group(1) + "]" +
					" (from " + matcher.start() + " to " + matcher.end(1) + ")\n" + 
					"Hostname [" + matcher.group(2) + "]" +
					" (from " + matcher.start(2) + " to " + matcher.end(2) + ")\n"
					);
			// group(3)可能未参与匹配， 应小心对待
			if (matcher.group(3) == null) {
				System.out.println("No port; default of '80' is assumed!");
			} else {
				System.out.println("Port is [" + matcher.group(3) + "]" + "(from " + matcher.start(3) + " to " + matcher.end(3) + ")\n");
			}
				
		}
	}
	
}
