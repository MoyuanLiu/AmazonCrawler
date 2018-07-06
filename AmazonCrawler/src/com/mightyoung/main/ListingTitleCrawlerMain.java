package com.mightyoung.main;

import com.amarsoft.are.ARE;
import com.mightyoung.BL.listingcrawler.ListingTitleCrawler;
import com.mightyoung.model.Crawler;

public class ListingTitleCrawlerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		System.out.println("开始爬取");
		Crawler listingtitlecrawler = new ListingTitleCrawler();
		listingtitlecrawler.init();
		listingtitlecrawler.startup();
		listingtitlecrawler.run();
		listingtitlecrawler.shutdown();
		System.out.println("爬取结束");
	}

}
