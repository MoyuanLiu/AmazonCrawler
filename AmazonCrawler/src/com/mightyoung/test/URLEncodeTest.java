package com.mightyoung.test;

import com.amarsoft.are.ARE;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLEncodeTest {
	public static void main(String[] args) {
		ARE.init("etc/are.xml");
		String teststr = "site:amazon.com slippers currently unavailable";
		ARE.getLog().info(URLEncodeTest.Encode(teststr));
	}

	public static String Encode(String url) {
		String result = "";
		try {
			result = URLEncoder.encode(url,"gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
