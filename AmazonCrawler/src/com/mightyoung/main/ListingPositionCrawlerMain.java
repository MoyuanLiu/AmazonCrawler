package com.mightyoung.main;

import com.amarsoft.are.ARE;
import com.mightyoung.BL.listingpositioncrawler.ListingPositionCrawler;

public class ListingPositionCrawlerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		ARE.getLog().info("��ʼ��ȡ");
		ListingPositionCrawler listingpositioncrawler = new ListingPositionCrawler();
		listingpositioncrawler.init();
		listingpositioncrawler.startup();
		listingpositioncrawler.run();
		listingpositioncrawler.shutdown();
		ARE.getLog().info("��ȡ����");
	}

}
