package com.mightyoung.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amarsoft.are.ARE;

public class RegexTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pattern = "[0-9]+";
		String teststring = "111 customer review";
		ARE.getLog().info(RegexTest.regexMatchTestMethod(teststring, pattern));
	}
	public static String regexMatchTestMethod(String teststr,String patternstr) {
		Pattern r = Pattern.compile(patternstr);
		Matcher m = r.matcher(teststr);
		m.find();
		return m.group(0);
	}
}
