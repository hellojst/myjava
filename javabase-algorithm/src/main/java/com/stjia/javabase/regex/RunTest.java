package com.stjia.javabase.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author stjia
 * @date 2018年5月25日
 */
public class RunTest {
	
	public static void main(String[] args) {
		RegexUtils.simpleRegexTest();
		RegexUtils.matcherWithRegex("\\w+", "Mastering Regular Expressions");
		System.out.println("12345=".matches("\\w"));
		RegexUtils.matcherAndLookingAt();
		RegexUtils.matchURL("http://regex.info/blog");
		RegexUtils.matchURL("http://regex.info/blog:8081");
		System.out.println(RegexUtils.replacement("java\\s*1\\.5", "java 10.0", "现在jdk的版本是java  1.5好开心"));
		System.out.println(RegexUtils.appendReplace("\\w+", "XXX", "---->abindg++jing<----"));
	}

}
