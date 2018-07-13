package com.mightyoung.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amarsoft.are.ARE;

public class RegexUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pattern = "dp/[^/]+/";
		String teststring = "https://www.amazon.com/MiYang-Striped-Bathing-Swimwear-X-Large/dp/B01DIHNXDW/ref=sr_1_150/142-1939743-0786759?ie=UTF8&qid=1531387698&sr=8-150&keywords=swim+dress";
		ARE.getLog().info(RegexUtil.getRegexStr(pattern, teststring));
	}
	public static String getRegexStr(String patternstr,String targettr) {
		String matchresult = "";
		Pattern r = Pattern.compile(patternstr);
		Matcher m = r.matcher(targettr);
		if(m.find()) {
			matchresult = m.group(0);
		}
		return matchresult;
	}
}
