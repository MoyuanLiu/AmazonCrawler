package com.mightyoung.service.parser.impl;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mightyoung.common.parser.Parser;

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
	public String getProductRank(String pagehtml,String parsepath) {
		if(pagehtml.equals(null) || pagehtml.isEmpty() || parsepath.isEmpty()||parsepath.equals(null)) {
			return null;
		}
		Document doc = Jsoup.parse(pagehtml);
		String result = "";
		Element el = doc.select(parsepath).first();
		if(el != null) {
			result = el.parent().select("td[a-size-base]").first().text();
		}
		return result;
	}
}
