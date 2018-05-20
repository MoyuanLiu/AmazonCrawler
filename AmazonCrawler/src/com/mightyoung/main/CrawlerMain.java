package com.mightyoung.main;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.model.CrawlerUrlInfo;

public class CrawlerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		System.out.println("开始爬取");
		CrawlerUrlInfo rooturl = new CrawlerUrlInfo();
		rooturl.setCrawlerUrl("https://www.amazon.com/s/field-keywords=nba+basketball+nike");
		//创建一个默认下载器
		DefaultDownloader downloader = new DefaultDownloader();
		Document doc = downloader.getPageDocument(rooturl.getCrawlerUrl());
		//获取指定document对象的HTML code
		String html = StringEscapeUtils.unescapeHtml(doc.toString());
		System.out.println(html);
		System.out.println("爬取结束");
		
	}

}
