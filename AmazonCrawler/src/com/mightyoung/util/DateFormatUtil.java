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
		String testdate = "19. April 2017";
		String standarddatestr = DateFormatUtil.changeEngtoStandardFormat(testdate);
		ARE.getLog().info(standarddatestr);
	}
	/*
	 * 将英语格式日期转换为标准日期格式
	 * */
	public static String changeEngtoStandardFormat(String inputdate) {
		String usedate = inputdate;
		if(usedate.contains("am ")||usedate.contains("le ")||usedate.contains("il ")||usedate.contains("de ")) {
			usedate = usedate.replace("am ", "").replace("le ", "").replace("de ", "").trim();
			if(!usedate.contains("ril ")) {
				usedate = usedate.replace("il ", "").trim();
			}
		}
		SimpleDateFormat standardsdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat englishsdf = new SimpleDateFormat("MMM d, yyyy",Locale.ENGLISH);
		SimpleDateFormat englishsdf1 = new SimpleDateFormat("dd MMM yyyy",Locale.ENGLISH);
		SimpleDateFormat englishsdf2 = new SimpleDateFormat("dd-MMM-yy",Locale.ENGLISH);
		SimpleDateFormat englishsdf3 = new SimpleDateFormat("dd. MMM yyyy",Locale.ENGLISH);
		SimpleDateFormat desdf = new SimpleDateFormat("dd. MMM yyyy",Locale.GERMAN);
		SimpleDateFormat frsdf = new SimpleDateFormat("dd MMM yyyy",Locale.FRENCH);
		SimpleDateFormat itsdf = new SimpleDateFormat("dd MMM yyyy",Locale.ITALIAN);
		SimpleDateFormat essdf = new SimpleDateFormat("dd MMM yyyy",new Locale("es", "ES"));
		Date targetdate = new Date();
		String judgestr = standardsdf.format(targetdate);
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
							try {
								targetdate = englishsdf1.parse(inputdate);
							}catch(ParseException e5) {
								ARE.getLog().error("不是英文日期1");
								try {
									targetdate = englishsdf2.parse(inputdate);
								}catch(ParseException e6) {
									ARE.getLog().error("不是英文日期2");
									try {
										targetdate = englishsdf3.parse(inputdate);
									}catch(ParseException e7) {
										ARE.getLog().error("不是英文日期3");
										
									}
								}
							}
						}
					}
				}
			}
		}finally {
			result = standardsdf.format(targetdate);
		}
		
		if(result.equals(judgestr)) {
			result = inputdate;
			ARE.getLog().error("没有正确匹配");
		}
		return result;
	}
}
