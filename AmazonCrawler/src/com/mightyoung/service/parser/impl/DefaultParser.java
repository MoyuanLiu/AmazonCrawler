package com.mightyoung.service.parser.impl;

import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mightyoung.common.parser.Parser;

public class DefaultParser implements Parser{

	@Override
	public ArrayList<String> getPageContent(String pagehtml, String parsepath) {
		if(pagehtml.equals(null) || pagehtml.isEmpty() || parsepath.isEmpty()||parsepath.equals(null)) {
			return null;
		}
		Document doc = Jsoup.parse(pagehtml);
		Elements els = doc.select(parsepath);
		ArrayList<String> contents = new ArrayList<String>();
		for(Element el : els) {
			String content = el.text();
			contents.add(content);
		}
		return contents;
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
