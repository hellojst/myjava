package com.stjia.javabase.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式不需要转义的： \b \t \r \n \f \\ \' \" 其余均需要用\\来转义出\预制搭配
 * @author stjia
 * @date 2018年5月25日
 */
public class RunTest {
	
	public static void main(String[] args) {
		String target = "hello world, you are my whole world yingchun!";
		String reg = "(\b\\w+\b)";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(target);
		matcher.find();
		int n = matcher.groupCount();
		if (matcher.find()) {
			
			String string = matcher.group(0);
			StringBuilder sb = new StringBuilder();
			sb.append(string);
			sb.append("<html>").append(matcher.group(0)).append("</html>\n");
			System.out.println(sb.toString());
		}
	}

}
