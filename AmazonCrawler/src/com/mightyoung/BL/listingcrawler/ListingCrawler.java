package com.mightyoung.BL.listingcrawler;

import com.amarsoft.are.ARE;
import com.mightyoung.model.Crawler;
import com.mightyoung.service.task.ClearListingStorageTask;
import com.mightyoung.service.task.CrawlListingProductURLTask;
import com.mightyoung.service.task.CrawlListingRankTask;
import com.mightyoung.service.task.GetKeyWordsListTask;
import com.mightyoung.service.task.OutputListingRankResultTask;

public class ListingCrawler extends Crawler{

	public static void main(String[] args) {
		ListingCrawler listingcrawler = new ListingCrawler();
		System.out.println("��ʼ��ȡ");
		listingcrawler.init();
		listingcrawler.startup();
		listingcrawler.run();
		listingcrawler.shutdown();
		System.out.println("��ȡ����");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		this.crawlername = "����listing����";
		ARE.getLog().info(this.crawlername+"��ʼ�����");
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		ARE.getLog().info(this.crawlername+"����!!");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long starttime = System.currentTimeMillis();
		ARE.getLog().info(this.crawlername+"��ʼִ����ȡ����!!");
		ClearListingStorageTask t1 = new ClearListingStorageTask();
		t1.run();
		GetKeyWordsListTask t2 = new GetKeyWordsListTask();
		t2.run();
		CrawlListingProductURLTask t3 = new CrawlListingProductURLTask(t2.keywords);
		t3.run();
		CrawlListingRankTask t4 = new CrawlListingRankTask(t3.producturls);
		t4.run();
		OutputListingRankResultTask t5 = new OutputListingRankResultTask(t4.titlerankmap);
		t5.run();
		long endtime = System.currentTimeMillis();
		int time = (int)(endtime-starttime);
		ARE.getLog().info("��Լ��ʱ��" + time/60000 + "����");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		ARE.getLog().info(this.crawlername+"�ѹر�");
	}

}
