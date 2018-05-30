package com.mightyoung.service.parser.impl;

import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mightyoung.common.parser.Parser;

public class ProductUrlParser implements Parser{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<String> getPageContent(String pagehtml, String parsepath) {
		Document doc = Jsoup.parse(pagehtml);
		ArrayList<String> producturls = new ArrayList<String>();
		
		return null;
	}

	@Override
	public String getPageHtml(Document doc) {
		if(doc.equals(null)) {
			return null;
		}
		String html = StringEscapeUtils.unescapeHtml(doc.toString());
		if(html.equals(null) || html.isEmpty()) {
			return null;
		}else {
			return html;
		}
	}

}
