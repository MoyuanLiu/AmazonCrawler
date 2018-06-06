package com.mightyoung.test;

import com.amarsoft.are.ARE;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String teststr = "#257,082 in Toys & Games (See top 100)" + 
				"#4448 in Toys & Games > Dolls & Accessories > Dolls";
		String[] testresult = StringTest.SplitTest(teststr);
		for(int i= 0 ;i<testresult.length;i++) {
			if(!testresult[i].isEmpty() && testresult[i] != null) {
				ARE.getLog().info(i + "#" + testresult[i]);
			}
		}
//		ARE.getLog().info(testresult);
	}
	public static String ProductProperty(String property) {
		String result = property.replaceFirst("[a-zA-Z]*:", "").trim() + "||";
//		String result = property.replace(".*:", "").trim() + "||";
		return result;
	}
	public static String[] SplitTest(String teststr) {
		String[] splitresult = teststr.split("#");
		return splitresult;
	}
}
