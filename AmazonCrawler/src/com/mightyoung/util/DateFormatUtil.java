package com.mightyoung.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.amarsoft.are.ARE;

/**
 * ���ڸ�ʽ������
 * @author hz
 *
 */
public class DateFormatUtil {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testdate = "October 21, 2016";
		String standarddatestr = DateFormatUtil.changeEngtoStandardFormat(testdate);
		ARE.getLog().info(standarddatestr);
	}
	/*
	 * ��Ӣ���ʽ����ת��Ϊ��׼���ڸ�ʽ
	 * */
	public static String changeEngtoStandardFormat(String inputdate) {
		if(inputdate.contains("am ")) {
			inputdate = inputdate.replace("am ", "").trim();
		}
		SimpleDateFormat standardsdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat englishsdf = new SimpleDateFormat("MMM d, yyyy",Locale.ENGLISH);
		SimpleDateFormat desdf = new SimpleDateFormat("dd. MMM yyyy",Locale.GERMAN);
		Date targetdate = new Date();
		String result = "";
		try {
			targetdate = englishsdf.parse(inputdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			ARE.getLog().error("����Ӣ������");
			try {
				targetdate = desdf.parse(inputdate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				ARE.getLog().error("���ǵ�������");
			}
		}finally {
			result = standardsdf.format(targetdate);
		}
		
		
		return result;
	}
}
