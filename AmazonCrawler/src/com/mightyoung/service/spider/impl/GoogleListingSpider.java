package com.mightyoung.service.spider.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;

public class GoogleListingSpider implements Spider{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		Element nextpagelink = document.getElementById("pnnext");
		if(nextpagelink == null) {
			return null;
		}
		String nextpageurl = nextpagelink.attr("abs:href");
		return nextpageurl;
	}
	public ArrayList<String> getAllProductUrls(String url){
		ArrayList<String> producturls = new ArrayList<String>();
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		Elements productlinkelements = document.select("div[id=ires]>div[rso]>div[class=bkwMgd]>div[class=srg]>div[class=g]>h3[class=r]>a");
		ARE.getLog().info("获取页面中的商品超链接");
		if(productlinkelements == null) {
			ARE.getLog().info("没有获取到超链接元素");
			return null;
		}
		ARE.getLog().info("链接元素个数：" + productlinkelements.size());
		for(int i = 0;i < productlinkelements.size();i++){
			String productlink = productlinkelements.get(i).attr("abs:href");
			producturls.add(productlink);
		}
		return producturls;
	}
}
