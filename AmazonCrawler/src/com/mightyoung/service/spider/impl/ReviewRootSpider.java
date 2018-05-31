package com.mightyoung.service.spider.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.service.parser.impl.DefaultParser;

public class ReviewRootSpider implements Spider{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testproducturl = "https://www.amazon.com/MiYang-Winter-Womens-Indoor-Slipper/dp/B01M5925MU/ref=sr_1_14/131-5359031-1404252?m=AR7H1RL9GCUCS&s=merchant-items&ie=UTF8&qid=1527666645&sr=1-14";
		DefaultDownloader downloader = new DefaultDownloader();
		Document doc = downloader.getPageDocument(testproducturl);
		String html = StringEscapeUtils.unescapeHtml(doc.toString());
		DefaultParser parser = new DefaultParser();
		ArrayList<String> contents = parser.getPageContent(html, "div[id=detailBullets_feature_div]>ul>li>span[class=a-list-item]");
		String[] content = contents.toString().split(",");
		for(String item : content) {
			if(item.contains("ASIN")) {
				ARE.getLog().info(item.trim());
			}
		}
		//ARE.getLog().info(contents.toString());
//		ReviewSpider spider = new ReviewSpider();
//		String reviewrooturl = spider.getReviewRootUrl(testproducturl);
//		ARE.getLog().info(reviewrooturl);
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
		Element nextpagelink = document.getElementById("pagnNextLink");
		if(nextpagelink.equals(null)) {
			return null;
		}
		String nextpageurl = nextpagelink.attr("abs:href");
		return nextpageurl;
	}
	public String getReviewRootUrl(String producturl) {
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(producturl);
		if (document == null) {
			return null;
		}
		//获取页面中的下一页超链接
		Element nextpagelink = document.getElementById("dp-summary-see-all-reviews");
		if(nextpagelink==null) {
			return null;
		}
		String nextpageurl = nextpagelink.attr("abs:href");
		return nextpageurl;
	}

}
