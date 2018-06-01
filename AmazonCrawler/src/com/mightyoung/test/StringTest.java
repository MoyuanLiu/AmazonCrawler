package com.mightyoung.test;

import com.amarsoft.are.ARE;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String teststr = "Size: 7-8.5 B(M) US|Color: Green";
		String testresult = StringTest.ProductProperty(teststr);
		ARE.getLog().info(testresult);
	}
	public static String ProductProperty(String property) {
		String result = property.replaceFirst("[a-zA-Z]*:", "").trim() + "||";
//		String result = property.replace(".*:", "").trim() + "||";
		return result;
	}

}
