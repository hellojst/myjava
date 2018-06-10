package com.stjia.javabase.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stjia.mvnjava.javabase_algorithm.LeetCodeUtils;

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
		System.out.println(RegexUtils.temperatureTransfer("from 36.3C to 40.1C."));
		System.out.println(RegexUtils.replaceNomove("\\b[\\p{Lu}\\p{Lt}]+\\b", "It's SO very RUDE to shout!"));
		System.out.println(RegexUtils.replaceLenthChange("\\b[\\p{Lu}\\p{Lt}]+\\b", "It's SO very RUDE to shout!"));
		String html = "<html>\n"
				+ "<img alt=path />\n"
				+ "<img size=9 />\n"
				+ "</html>\n";
		RegexUtils.regionRange(html);
		System.out.println(RegexUtils.transBound("\\bcar\\b", "Madagascar is best seen by car or bike!", 7, false));
		System.out.println(RegexUtils.transBound("\\bcar\\b", "Madagascar is best seen by car or bike!", 7, true));
		RegexUtils.printMatcherInfo();
		Matcher matcher = Pattern.compile("\\w+").matcher("123 QWE");
		System.out.println(RegexUtils.getTargetTextFromMatcher(matcher));
		String[] strs = RegexUtils.split2Str("[^\\w]+", "what's up? Doc");
		String[] strs2 = RegexUtils.split2Str("[^\\w]+", "what's up? 小春春，你在干嘛");
		//包含非ASCII字符时用\\w不能匹配，需用\p{L}
		String[] strs3 = RegexUtils.split2Str("[^\\p{L}\\p{N}_]+", "what's up? 小春春；你在干嘛");
		printStrArray(strs);
		printStrArray(strs2);
		printStrArray(strs3);
		RegexUtils.splitWithSpaceAndLimit();
	}
	
	private static void printStrArray(String[] strs) {
		System.out.print("[");
		for(int i = 0; i < strs.length; i++) {
			System.out.print(strs[i]);
			if (i != strs.length - 1) {
				System.out.print(",");
			}
		}
		System.out.println("]");
	}

}
