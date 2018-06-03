package com.stjia.javabase.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author stjia
 * @date 2018年5月25日
 */
public class RegexUtils {
	
	public static void simpleRegexTest() {
		String mytext = "this is my 2st test string";
		String myRegex = "\\d+\\w+"; 
		Pattern pattern = Pattern.compile(myRegex);
		Matcher matcher = pattern.matcher(mytext);
		
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
	
	
}
