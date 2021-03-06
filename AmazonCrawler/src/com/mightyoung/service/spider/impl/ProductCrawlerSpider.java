package com.mightyoung.service.spider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.util.FileIOUtil;

public class ProductCrawlerSpider implements Spider{
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductCrawlerSpider spider = new ProductCrawlerSpider();
//		ArrayList<String> producturls = spider.getAllProductUrl("https://www.amazon.fr/s?marketplaceID=A13V1IB3VIYZZH&me=A5ZLZ1NEB7UOZ&merchant=A5ZLZ1NEB7UOZ");
//		ARE.getLog().info("链接url个数："+producturls.size());
//		for(String producturl :producturls) {
//			ARE.getLog().info("获取店铺链接");
//			ARE.getLog().info(producturl);
//		}
		
		String nexturl = spider.getSingalNextPage("https://www.amazon.fr/s?marketplaceID=A13V1IB3VIYZZH&me=A5ZLZ1NEB7UOZ&merchant=A5ZLZ1NEB7UOZ");
		ARE.getLog().info(nexturl);
	}

	@Override
	public HashMap<String, String> getNextPages(String url) {
		return null;
	}

	@Override
	public String getSingalNextPage(String url) {
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		//ARE.getLog().info(document.html());
		FileIOUtil.WriteStringToFile("data/htmlcode.txt", document.html());
		if (document == null) {
			return null;
		}
		//获取页面中的下一页超链接
		Element nextpagelink = document.select("a[id=pagnNextLink]").first();
		if(nextpagelink==null) {
			return null;
		}
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
		//FileIOUtil.WriteStringToFile("data/htmlcode.html", document.html());
		//获取页面中的商品超链接
		Elements productlinkelements = document.select("ul.s-result-list > li");
//		String html = productlinkelements.html();
//		ARE.getLog().info(html);
//		Document doc = Jsoup.parse(html);
		
//		Elements productlinks = document.select("ul#s-results-list-atf > li");
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

}
