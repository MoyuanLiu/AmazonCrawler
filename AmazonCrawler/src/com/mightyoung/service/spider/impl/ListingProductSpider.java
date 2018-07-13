package com.mightyoung.service.spider.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.util.FileIOUtil;

public class ListingProductSpider implements Spider{

	public static void main(String[] args) {
		String testurl = "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=swim+dress";
		ListingProductSpider testspider = new ListingProductSpider();
//		String testresult = testspider.getSingalNextPage(testurl);
//		ARE.getLog().info(testresult);
		HashMap<String,Boolean> producturlflag = testspider.getAllProductUrlADFlag(testurl);
		
		ARE.getLog().info("链接url个数："+producturlflag.keySet().size());
		for(String producturl : producturlflag.keySet()) {
			ARE.getLog().info("获取店铺链接");
			ARE.getLog().info(producturl);
			ARE.getLog().info(producturlflag.get(producturl));
			//FileIOUtil.WriteStringToFile("data/urllist.txt", producturl);
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
		//FileIOUtil.WriteStringToFile("data/htmlcode.txt", document.html());
		Elements productlinkelements = document.select("li[id^=result_]");
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
	public HashMap<String,Boolean> getAllProductUrlADFlag(String url){
		HashMap<String,Boolean> producturls = new HashMap<String,Boolean>();
		
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		Elements productlinkelements = document.select("li[id^=result_]");
		ARE.getLog().info("获取页面中的商品超链接");
		for(Element e : productlinkelements) {
			Element productlink = e.select("a.s-access-detail-page").first();
			String producturl = productlink.attr("abs:href");
			if(e.text().contains("Sponsored")) {
				producturls.put(producturl, true);
			}else {
				producturls.put(producturl, false);
			}
		}
		ARE.getLog().info("链接元素个数：" + producturls.size());
		return producturls;
	}
}
