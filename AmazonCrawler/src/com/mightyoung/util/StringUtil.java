package com.mightyoung.util;

import com.amarsoft.are.ARE;

public class StringUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String teststr = "in&nbsp;Toys & Games";
		ARE.getLog().info(StringUtil.replaceHtmlSpace(teststr));
	}
	public static String replaceHtmlSpace(String str) {
		String htmlSpace = "&nbsp;|&lt;|&gt;|&amp;|&quot;|&apos;|&cent;|&pound;|&yen;|&euro;|&sect;|&copy;|&reg;|&trade;|&times;|&divide;|&ensp;|&emsp;|&thinsp;|&middot;";
		if(null == str){
			return " ";
		}
		return str.replaceAll("\\s|\u0020|\\t|"+htmlSpace, " ");
	}
}
