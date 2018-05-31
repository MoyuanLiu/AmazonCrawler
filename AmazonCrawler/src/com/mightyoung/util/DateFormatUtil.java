package com.mightyoung.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.amarsoft.are.ARE;

/**
 * 日期格式工具类
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
	 * 将英语格式日期转换为标准日期格式
	 * */
	public static String changeEngtoStandardFormat(String engdate) {
		SimpleDateFormat standardsdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat englishsdf = new SimpleDateFormat("MMM d, yyyy",Locale.ENGLISH);
		Date targetdate;
		String result = "";
		try {
			targetdate = englishsdf.parse(engdate);
			result = standardsdf.format(targetdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			ARE.getLog().info("日期转换错误");
			e.printStackTrace();
		}
		return result;
	}
}
