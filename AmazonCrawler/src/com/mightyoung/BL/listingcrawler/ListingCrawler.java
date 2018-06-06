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
		System.out.println("开始爬取");
		listingcrawler.init();
		listingcrawler.startup();
		listingcrawler.run();
		listingcrawler.shutdown();
		System.out.println("爬取结束");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		this.crawlername = "搜索listing爬虫";
		ARE.getLog().info(this.crawlername+"初始化完成");
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		ARE.getLog().info(this.crawlername+"启动!!");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long starttime = System.currentTimeMillis();
		ARE.getLog().info(this.crawlername+"开始执行爬取任务!!");
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
		ARE.getLog().info("大约用时：" + time/60000 + "分钟");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		ARE.getLog().info(this.crawlername+"已关闭");
	}

}
