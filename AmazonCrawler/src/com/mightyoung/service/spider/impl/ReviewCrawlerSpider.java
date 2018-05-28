package com.mightyoung.service.spider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;

public class ReviewCrawlerSpider implements Spider{
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReviewCrawlerSpider spider = new ReviewCrawlerSpider();
//		String nexturl = spider.getSingalNextPage("https://www.amazon.com/s?marketplaceID=ATVPDKIKX0DER&me=AR7H1RL9GCUCS&merchant=AR7H1RL9GCUCS");
//		ARE.getLog().info(nexturl);
		ArrayList<String> producturls = spider.getAllProductUrl("https://www.amazon.com/s?marketplaceID=ATVPDKIKX0DER&me=AR7H1RL9GCUCS&merchant=AR7H1RL9GCUCS");
		for(String producturl :producturls) {
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
		String nextpageurl = nextpagelink.attr("abs:href");
		return nextpageurl;
	}
	/**
	 * 从店铺页面获取全部的商品链接
	 * @param url
	 * @return
	 */
	public ArrayList<String> getAllProductUrl(String url){
		ArrayList<String> producturls = new ArrayList<String>();
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		
		//获取页面中的商品超链接
		Elements productlinkelements = document.select("ul[id=s-results-list-atf]>li>div.a-spacing-mini>a");
		ARE.getLog().info("获取页面中的商品超链接");
		for(int i = 0;i < productlinkelements.size();i++){
			ARE.getLog().info("循环元素列表");
			String productlink = productlinkelements.get(i).attr("href");
			ARE.getLog().info(productlink);
			producturls.add(productlink);
		}
		return producturls;
	}

}
