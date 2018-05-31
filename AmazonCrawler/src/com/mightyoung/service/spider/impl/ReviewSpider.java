package com.mightyoung.service.spider.impl;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;

public class ReviewSpider implements Spider{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testurl = "https://www.amazon.com/MiYang-Winter-Womens-Indoor-Slipper/product-reviews/B01FM0KMFK/ref=cm_cr_dp_d_show_all_top?ie=UTF8&reviewerType=all_reviews";
		ReviewSpider reviewspider = new ReviewSpider();
		String testresult = reviewspider.getSingalNextPage(testurl);
		ARE.getLog().info(testresult);
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
		Element nextpagelink = document.select("div[id=cm_cr-pagination_bar]>ul[class=a-pagination]>li[class=a-last]>a").first();
		if(nextpagelink==null) {
			return null;
		}
		String nextpageurl = nextpagelink.attr("abs:href");
		return nextpageurl;
	}

}
