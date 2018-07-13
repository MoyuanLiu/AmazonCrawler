package com.mightyoung.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amarsoft.are.ARE;

public class RegexTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pattern = "dp/(.+)/";
		String teststring = "https://www.amazon.com/Aleumdr-Swimdress-Tankini-Swimwear-Multicoloured/dp/B0725NMFS8/ref=sr_1_1_sspa?ie=UTF8&qid=1531385449&sr=8-1-spons&keywords=swim+dress&psc=1";
		ARE.getLog().info(RegexTest.regexMatchTestMethod(teststring, pattern));
	}
	public static String regexMatchTestMethod(String teststr,String patternstr) {
		Pattern r = Pattern.compile(patternstr);
		Matcher m = r.matcher(teststr);
		m.find();
		return m.group(0);
	}
}
