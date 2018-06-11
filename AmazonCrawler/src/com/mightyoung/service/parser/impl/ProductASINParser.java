package com.mightyoung.service.parser.impl;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mightyoung.common.parser.Parser;

public class ProductASINParser implements Parser{

	@Override
	public ArrayList<String> getPageContent(String pagehtml, String parsepath) {
		return null;
	}

	@Override
	public String getPageHtml(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getProductASIN(String pagehtml, String parsepath) {
		if(pagehtml.equals(null) || pagehtml.isEmpty() || parsepath.isEmpty()||parsepath.equals(null)) {
			return null;
		}
		Document doc = Jsoup.parse(pagehtml);
		Elements els = doc.select(parsepath);
		String result = "";
		for(Element el : els) {
			String content = el.text();
			if(content.contains("ASIN")) {
				result = content.replace("ASIN:", "").trim();
			}
		}
		return result;
	}
//	public String getProductAsinBackup(String pagehtml,String parsepath) {
//		if(pagehtml.equals(null) || pagehtml.isEmpty() || parsepath.isEmpty()||parsepath.equals(null)) {
//			return null;
//		}
//		Document doc = Jsoup.parse(pagehtml);
//		Elements els = doc.select(parsepath);
//		String result = "";
//		for(Element el : els) {
//			String content = el.text();
//			if(content.contains("ASIN")) {
//				result = content.replace("ASIN:", "").trim();
//			}
//		}
//		return result;
//	}
}
