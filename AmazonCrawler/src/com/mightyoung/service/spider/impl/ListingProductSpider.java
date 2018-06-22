package com.mightyoung.service.spider.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;

public class ListingProductSpider implements Spider{

	public static void main(String[] args) {
		String testurl = "https://www.amazon.com/s/?url=search-alias%3Daps&field-keywords=reborn+baby+dolls";
		ListingProductSpider testspider = new ListingProductSpider();
//		String testresult = testspider.getSingalNextPage(testurl);
//		ARE.getLog().info(testresult);
		ArrayList<String> producturls = testspider.getAllProductUrl(testurl);
		ARE.getLog().info("链接url个数："+producturls.size());
		for(String producturl : producturls) {
			ARE.getLog().info("获取店铺链接");
			ARE.getLog().info(producturl);
		}
	}

	@Override
	public HashMap<String, String> getNextPages(String url) {
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
		Element nextpagelink = document.getElementById("pagnNextLink");
		if(nextpagelink==null) {
			return null;
		}
		String nextpageurl = nextpagelink.attr("abs:href");
		return nextpageurl;
	}
	/*
	 * 获取所有的商品链接
	 */
	public ArrayList<String> getAllProductUrl(String url){
		ArrayList<String> producturls = new ArrayList<String>();
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		Elements productlinkelements = document.select("ul.s-result-list > li");
		Elements productlinks = productlinkelements.select("a.s-access-detail-page");
		ARE.getLog().info("获取页面中的商品超链接");
		if(productlinks==null) {
			ARE.getLog().info("没有获取到超链接元素");
			return null;
		}
		ARE.getLog().info("链接元素个数：" + productlinks.size());
		for(int i = 0;i < productlinks.size();i++){
			String productlink = productlinks.get(i).attr("abs:href");
			producturls.add(productlink);
		}
		return producturls;
	}
	/*
	 * 获取所有的商品链接
	 */
	public HashMap<String,String> getAllProductUrlADFlag(String url){
		HashMap<String,String> producturls = new HashMap<String,String>();
		
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		Elements productlinkelements = document.select("ul.s-result-list > li");
		Elements productlinks = productlinkelements.select("a.s-access-detail-page");
		for(Element e : productlinkelements) {
			Element productlink = e.select("a.s-access-detail-page").first();
		}
		ARE.getLog().info("获取页面中的商品超链接");
		if(productlinks == null) {
			ARE.getLog().info("没有获取到超链接元素");
			return null;
		}
		ARE.getLog().info("链接元素个数：" + productlinks.size());
		for(int i = 0;i < productlinks.size();i++){
			String productlink = productlinks.get(i).attr("abs:href");
//			producturls.add(productlink);
		}
		return producturls;
	}
}
