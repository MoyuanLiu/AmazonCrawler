package com.mightyoung.service.spider.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;

public class BaiduListingSpider implements Spider{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testurl = "https://www.baidu.com/s?ie=utf-8&wd=site:amazon.com slippers currently unavailable";
		BaiduListingSpider testspider = new BaiduListingSpider();
		//String testresult = testspider.getSingalNextPage(testurl);
		//ARE.getLog().info(testresult);
		testspider.getAllProductUrls(testurl);
	}

	@Override
	public HashMap<String, String> getNextPages(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSingalNextPage(String url) {
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		//获取页面中的下一页超链接
		Element nextpagelink = document.select("div[id=page]>a").last();
		if(nextpagelink == null) {
			return null;
		}
		String nextpageurl = nextpagelink.attr("abs:href");
		return nextpageurl;
	}
	/*
	 * 获取查询结果页面所有的商品链接
	 * */
	public ArrayList<String> getAllProductUrls(String url){
		ArrayList<String> producturls = new ArrayList<String>();
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		Elements productlinkelements = document.select("div[id=content_left]>div.c-container.result>h3>a");
		ARE.getLog().info("获取页面中的商品超链接");
		if(productlinkelements == null) {
			ARE.getLog().info("没有获取到超链接元素");
			return null;
		}
		ARE.getLog().info("链接元素个数：" + productlinkelements.size());
		for(int i = 0;i < productlinkelements.size();i++){
			String productlink = productlinkelements.get(i).attr("abs:href");
			ARE.getLog().info("获取到商品链接:[" + productlink + "]");
			producturls.add(productlink);
		}
		return producturls;
	}
}
