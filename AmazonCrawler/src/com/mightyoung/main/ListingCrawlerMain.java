package com.mightyoung.main;

import com.mightyoung.BL.listingcrawler.ListingCrawler;

public class ListingCrawlerMain {
	public static void main(String[] args) {
		ListingCrawler listingcrawler = new ListingCrawler();
		System.out.println("开始爬取");
		listingcrawler.init();
		listingcrawler.startup();
		listingcrawler.run();
		listingcrawler.shutdown();
		System.out.println("爬取结束");
	}
}
