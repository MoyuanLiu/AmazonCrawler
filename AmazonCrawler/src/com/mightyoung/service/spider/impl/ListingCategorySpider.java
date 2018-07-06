package com.mightyoung.service.spider.impl;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;

public class ListingCategorySpider implements Spider{
	
	public static void main(String[] args) {
		String testurl = "https://www.amazon.com/s/?url=search-alias%3Daps&field-keywords=pet+blanket";
		ListingCategorySpider testspider = new ListingCategorySpider();
		String testresult = testspider.getCategoryUrl(testurl);
		System.out.println(testresult);
	}
	@Override
	public HashMap<String, String> getNextPages(String url) {
		return null;
	}

	@Override
	public String getSingalNextPage(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getCategoryUrl(String url) {
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		//获取搜索页面中的小类目链接
		Element categorylink = document.select("div[id=leftNavContainer]>ul>div>li").first().select("span>a").first();
		if(categorylink == null) {
			return null;
		}
		
		String categoryurl = categorylink.attr("abs:href");
		return categoryurl;
	}
}
