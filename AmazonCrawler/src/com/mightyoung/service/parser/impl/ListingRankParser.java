package com.mightyoung.service.parser.impl;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.parser.Parser;
import com.mightyoung.util.FileIOUtil;
import com.mightyoung.util.StringUtil;

public class ListingRankParser implements Parser{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> getPageContent(String pagehtml, String parsepath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPageHtml(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getProductTitle(String pagehtml,String parsepath) {
		if(pagehtml.equals(null) || pagehtml.isEmpty() || parsepath.isEmpty()||parsepath.equals(null)) {
			return null;
		}
		Document doc = Jsoup.parse(pagehtml);
		String result = "";
		Elements els = doc.select(parsepath);
		result = els.text();
		return result;
	}
	public ArrayList<String> getProductRank(String pagehtml,String parsepath) {
		if(pagehtml.equals(null) || pagehtml.isEmpty() || parsepath.isEmpty()||parsepath.equals(null)) {
			return null;
		}
		Document doc = Jsoup.parse(pagehtml);
		ArrayList<String> result = new ArrayList<String>();
		Elements els = doc.select(parsepath);
//		FileIOUtil.WriteStringToFile("data/code.html", pagehtml);
		for(Element el : els) {
			if(el.text().contains("Best Sellers Rank")) {
				Elements rankels = el.nextElementSibling().select("span>span");
				for(Element e : rankels) {
					result.add(StringUtil.replaceHtmlSpace(e.text()));
				}
			}
		}
//		ARE.getLog().info("result:"+result);
		return result;
	}
	public ArrayList<String> getProductRankBackup(String pagehtml,String parsepath) {
		if(pagehtml.equals(null) || pagehtml.isEmpty() || parsepath.isEmpty()||parsepath.equals(null)) {
			return null;
		}
		Document doc = Jsoup.parse(pagehtml);
		ArrayList<String> result = new ArrayList<String>();
		Element rankbackup = doc.select(parsepath).first();
		String temp = rankbackup.text().trim();
		String[] items = temp.split("#");
		for(String item : items) {
			if(item != null && !item.isEmpty()) {
				result.add("#" + StringUtil.replaceHtmlSpace(item));
			}
		}
		return result;
	}
}
