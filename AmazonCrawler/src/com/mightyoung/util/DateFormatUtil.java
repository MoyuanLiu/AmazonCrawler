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
		String testdate = "31 de mayo de 2018";
		String standarddatestr = DateFormatUtil.changeEngtoStandardFormat(testdate);
		ARE.getLog().info(standarddatestr);
	}
	/*
	 * 将英语格式日期转换为标准日期格式
	 * */
	public static String changeEngtoStandardFormat(String inputdate) {
		if(inputdate.contains("am ")||inputdate.contains("le ")||inputdate.contains("il ")||inputdate.contains("de ")) {
			inputdate = inputdate.replace("am ", "").replace("le ", "").replace("il ", "").replace("de ", "").trim();
		}
		SimpleDateFormat standardsdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat englishsdf = new SimpleDateFormat("MMM d, yyyy",Locale.ENGLISH);
		SimpleDateFormat desdf = new SimpleDateFormat("dd. MMM yyyy",Locale.GERMAN);
		SimpleDateFormat frsdf = new SimpleDateFormat("dd MMM yyyy",Locale.FRENCH);
		SimpleDateFormat itsdf = new SimpleDateFormat("dd MMM yyyy",Locale.ITALIAN);
		SimpleDateFormat essdf = new SimpleDateFormat("dd MMM yyyy",new Locale("es", "ES"));
		Date targetdate = new Date();
		String result = "";
		try {
			targetdate = englishsdf.parse(inputdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			ARE.getLog().error("不是英文日期");
			try {
				targetdate = desdf.parse(inputdate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				ARE.getLog().error("不是德文日期");
				try {
					targetdate = frsdf.parse(inputdate);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					ARE.getLog().error("不是法文日期");
					try {
						targetdate = itsdf.parse(inputdate);
					} catch (ParseException e3) {
						// TODO Auto-generated catch block
						ARE.getLog().error("不是意大利语日期");
						try {
							targetdate = essdf.parse(inputdate);
						} catch (ParseException e4) {
							// TODO Auto-generated catch block
							ARE.getLog().error("不是西班牙语日期");
						}
					}
				}
			}
		}finally {
			result = standardsdf.format(targetdate);
		}
		
		
		return result;
	}
}
