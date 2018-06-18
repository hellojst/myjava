package com.stjia.javabase.regex;

import static org.hamcrest.CoreMatchers.containsString;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.w3c.dom.Text;

/**
 * 正则表达式不需要转义\的有： \b \t \r \n \f \\ \' \" 其余均需要用\\来转义出\预制搭配
 * @author stjia
 * @date 2018年5月25日
 */
public class RegexUtils {
	private static String numberRegex = "(\\d+(?:\\.\\d*)?)\\s*C";
	private static Pattern numberPattern = Pattern.compile(numberRegex);
	
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
	
	/**
	 * 带捕获组的匹配
	 * @param url
	 */
	public static void matchURL(String url) {
		//?x: 宽松排列和注释模式: 忽略空白之类的   （？：）：非捕获型括号   匹配的前提试试模式要相符
		String regex = "(?x) ^(https?):// ([^/:]+) (?:[-=&/.\\w]*:(\\d+))?"; // 类似 https://www.baidu.com/baidu?tn=monline_3_dg&ie=utf-8&wd=菜鸟教程
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
	
	/**
	 * 字符串的replace等方法用的就是正则表达式的匹配
	 * @param regex
	 * @param newText
	 * @param context
	 * @return
	 */
	public static String replacement(String regex, String newText, String context) {
		//Matcher.quoteReplacement(newText) 对文本中包含的\ 或 $ 进行处理为文本
		return Pattern.compile(regex).matcher(context).replaceAll(Matcher.quoteReplacement(newText));
	} 
	
	/**
	 * 高级查找替换  不受检索范围影响，查找替换前先重置检索范围
	 * @param regex
	 * @param newtext
	 * @param context
	 * @return
	 */
	public static String appendReplace(String regex, String newtext, String  context) {
		StringBuffer sb = new StringBuffer();
		Matcher matcher = Pattern.compile(regex).matcher(context);
		Integer start = matcher.regionStart();
		Integer end = matcher.regionEnd();
		matcher.reset().region(start, end); //因为一定要reset ，要想仍用之前的检索范围就 重置后再加上
//		matcher.reset();
		while (matcher.find()) {
			matcher.appendReplacement(sb, newtext);
			System.out.println(sb);
		}
		//找到所有匹配或期望匹配后用以下方法将剩余文本赋到stringbuffer中
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * 将字符串中的摄氏温度转为华氏温度
	 * @return
	 */
	public static String temperatureTransfer(String content) {
		Matcher matcher = numberPattern.matcher(content); //使用捕获组捕获数字; 
		StringBuffer result = new StringBuffer(); //生成用于替换的中间副本
		while(matcher.find()) {
			float celsius = Float.parseFloat(matcher.group(1)); //将匹配到的值转为浮点数
//			String two = matcher.group(2); // ?:为非捕获组，将？：去掉才能匹配点号及其后数字
			int fahrenheit = (int) (celsius * 9 / 5 + 32);
			matcher.appendReplacement(result, fahrenheit + "F");
		}
		matcher.appendTail(result);
		return result.toString();
	}
	
	/**
	 * 原地 查找-替换
	 */
	public static String replaceNomove(String regex, String context) {
		StringBuilder stringBuilder = new StringBuilder(context); // 使用stringbuilder的原因是其实可变的，而string是final的，要用新string去接受返回值
		//而stringbuilder是可变的，可不用设接收值，就在本身修改
		Matcher matcher = Pattern.compile(regex).matcher(stringBuilder);
		while (matcher.find()) {
			stringBuilder.replace(matcher.start(), matcher.end(), matcher.group().toLowerCase()); // 进行新值换旧值
		}
		return stringBuilder.toString();
	}
	
	/**
	 *  长度变化的替换
	 * @param regex
	 * @param context
	 * @return
	 */
	public static String replaceLenthChange(String regex, String context) {
		StringBuilder stringBuilder = new StringBuilder(context);
		Matcher matcher = Pattern.compile("\\b[\\p{Lu}\\p{Lt}]+\\b").matcher(stringBuilder);
		int matchPosition = 0;
		while (matcher.find(matchPosition)) {
			matchPosition = matcher.end();
			stringBuilder.replace(matcher.start(), matcher.end(), "<b>" +matcher.group().toLowerCase() + "</b>");
			matchPosition += 7;
		}
		return stringBuilder.toString();
	}
	
	/**
	 * matcher 的检索范围
	 */
	public static void regionRange(String html) {
		//匹配模式为 忽略大小写，.为传统模式  *？:为忽略优先
		Matcher mImg = Pattern.compile("(?id)<IMG\\s+(.*?)/?>").matcher(html);
		//x为松散模式
		//查找alt属性的matcher （应用于刚刚找到的IMG tag中）
		Matcher mAlt = Pattern.compile("(?ix)\\b ALT \\s* =").matcher(html);
		//查找换行符
		Matcher mLine = Pattern.compile("\\n").matcher(html);
		//对html中的每个image tag
		while(mImg.find()){
			//把查找范围局限在刚刚找到的tag中  捕获组1在字符串中的绝对偏移值
			mAlt.region(mImg.start(1), mImg.end(1));
			if (!mAlt.find()) {
				//计算当前image tag之前的换行符数量
				mLine.region(0, mImg.end());
				int line = 1;
				while(mLine.find()) {
					line++;
				}
				//没找过就报错，输出找到的整个image tag
				System.out.println("Missing ALT attribute in: " + mImg.group() + " in line nmber: " + line);
			}
		}
	}
	
	/**
	 * 关于单词边界  用transBound设定检索范围之外的透明性
	 * 另一个边界：AnchoringBound 默认为true， 能匹配检索范围的边界，即使检索范围不是整个字符串， 禁用则锚点只能匹配检索范围内符合
	 * 规定的位置
	 * @return
	 */
	public static String transBound(String regex, String text, int startPosition, boolean useTransbound) {
		StringBuffer result = new StringBuffer();
		Matcher matcher = Pattern.compile(regex).matcher(text);
		//默认为false 检索范围之外不透明
		matcher.useTransparentBounds(useTransbound);
		matcher.region(startPosition, matcher.regionEnd());
		while(matcher.find()) {
			matcher.appendReplacement(result, "-" + matcher.group().toUpperCase());
		}
		matcher.appendTail(result);
		return result.toString();
	}
	
	/**
	 * 方法链  -- 构建器模式  //构造如下
	 */
	public static void MethodChaining(String regex, String text,int position, boolean transBound, boolean anchoringBound){
		Matcher matcher = Pattern.compile(regex).matcher(text)
				.region(position, text.length()) //重置检索范围
				.useTransparentBounds(transBound) //检索范围边界
				.useAnchoringBounds(anchoringBound); //锚点范围边界
	}
	
	/**
	 * 打印matcher的信息
	 */
	public static void printMatcherInfo() {
		Matcher matcher = Pattern.compile("(\\w+)").matcher("ABC 123");
		System.out.println(matcher.toString());
		while(matcher.find()) {
			System.out.println(matcher.toString());
		}
	}
	
	/**
	 * 查询Matcher的目标字符串
	 * @param matcher
	 * @return
	 */
	public static String getTargetTextFromMatcher(Matcher matcher) {
		//可作为类属性加载时预编译来提高效率，不用等每次调用才编译
		Pattern separatorPattern = Pattern.compile("^"); 
		//记住这些位置，以备备份之后恢复
		int regionStart = matcher.regionStart();
		int regionEnd = matcher.regionEnd();
		Pattern pattern = matcher.pattern();
		//只有这样才能返回字符串
		String text = matcher.usePattern(separatorPattern).replaceFirst("");
		
		//恢复之前的记录 操作完成后不能改变原matcher的值
		matcher.usePattern(pattern).region(regionStart, regionEnd);
		//返回文本
		return text;
	}
	
	/**
	 * 分割
	 * @param regex
	 * @param text
	 * @return
	 */
	public static String[] split2Str(String regex, String text) {
		String[] result = Pattern.compile(regex).split(text);
		return result;
	}
	
	/**
	 * 带空格和有limit限制的分隔
	 */
	public static void splitWithSpaceAndLimit() {
		String[] result1 = Pattern.compile("\\s*,\\s*").split(" , one, two,  ,, 3");
		//默认limit=0 会忽略尾部空字符串 所以result2 为 空字符串和 "xx"
		String[] result2 = Pattern.compile(":").split(":xx:");
		//limit 默认为0， limit < 0 会保留数组结尾的空字符
		String[] result3 = Pattern.compile(":").split(":xx:", -1);
		String str = "ch, ja, un, un am, ls, cd";
		//limit > 0 最多匹配limit个元素 即 正则表达式最多应用limit-1次
		String[] result4 = Pattern.compile(",").split(str, 3);
	}
	
	public static void addImgTag(String htmltext) throws MalformedURLException, IOException {
		StringBuilder html = new StringBuilder(htmltext);
		//匹配独立的<img> tags
		Matcher mImg = Pattern.compile("(?id)<IMG\\s+(.*?)/?>").matcher(html);
		Matcher mSrc = Pattern.compile("(?ix)\\bSRC     =(\\S+)").matcher(html);
		Matcher mWidth = Pattern.compile("(?ix)\\bWIDTH = (\\S+)").matcher(html);
		Matcher mHeight = Pattern.compile("(?ix)\\bHEIGHT= (\\S+)").matcher(html);
		int imgMatchPointer = 0; //第一次搜索从字符串起始位置开始
		while(mImg.find(imgMatchPointer)){
			imgMatchPointer = mImg.end(); //下一次查找从这里开始
			//从刚刚查找到的mimg中查找各字段
			boolean hasSRC = mSrc.region(mImg.start(1), mImg.end(1)).find();
			boolean hasWidth = mWidth.region(mImg.start(1), mImg.end(1)).find();
			boolean hasHeight = mHeight.region(mImg.start(1), mImg.end(1)).find();
			
			if (hasSRC && (!hasHeight || !hasWidth)) {
				BufferedImage image = ImageIO.read(new URL(mSrc.group(1)));
				String size; //存放未提供 WIDTH，HEIGHT
				if (hasWidth) {
					size = "height = '" + Integer.parseInt(mWidth.group(1)) * image.getHeight() / image.getWidth() + "' ";
				} else if (hasHeight) {
					size = "width ='" + Integer.parseInt(mHeight.group(1)) * image.getWidth() / image.getHeight() + "' ";
				} else {
					size = "width=' " + image.getWidth() + "'" + 
						   "height=' " + image.getHeight() + "' ";
				}
				html.indexOf(size, mImg.start(1)); //原地修改html
				imgMatchPointer += size.length(); //更新匹配指针
			}
		}
	}
	
	/**
	 * 多验证
	 * @param context
	 * @param patterns
	 * @return
	 */
	public static boolean volidateUseMorePattern(String context, Pattern... patterns) {
		Matcher matcher = Pattern.compile("").matcher(context);
		for(Pattern pattern : patterns) {
			matcher.usePattern(pattern);
			if (!matcher.find()) {
				return false;
			}
		}
		return true;
	}
	
	public static void volidatePatternChain(String context) {
		Pattern pAtEnd = Pattern.compile("\\G\\z");
		Pattern pWord = Pattern.compile("\\G\\w+");
		Pattern pNonHtml = Pattern.compile("\\G[^\\w<>&]+");
		Pattern pImgTag = Pattern.compile("\\G(?i)<img\\s+([^>]+)>");
		Pattern pLink = Pattern.compile("(\\G(?i)<A\\s+([^>]+)>)");
		Pattern pLinkX = Pattern.compile("\\G(?i)</A>");
		Pattern pEntity = Pattern.compile("\\G&(#\\d+;\\w+);");
		
		boolean needClose = false;
		Matcher matcher = pAtEnd.matcher(context); //每个pattern都可生成matcher
		while (!matcher.usePattern(pAtEnd).find()) {
			if(matcher.usePattern(pWord).find()){
				//.....
			} else if (matcher.usePattern(pNonHtml).find()) {
				//.....
			}
			//.......
		}
	}
}
