package com.mightyoung.main;

import com.mightyoung.BL.listingcrawler.ListingCrawler;

public class ListingCrawlerMain {
	public static void main(String[] args) {
		ListingCrawler listingcrawler = new ListingCrawler();
		System.out.println("��ʼ��ȡ");
		listingcrawler.init();
		listingcrawler.startup();
		listingcrawler.run();
		listingcrawler.shutdown();
		System.out.println("��ȡ����");
	}
}
