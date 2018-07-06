package com.mightyoung.BL.listingcrawler;

import java.util.HashMap;

import com.amarsoft.are.ARE;
import com.mightyoung.model.Crawler;
import com.mightyoung.service.task.ClearListingStorageTask;
import com.mightyoung.service.task.ClearXLSXStorageTask;
import com.mightyoung.service.task.CrawlListingCategoryProductUrlTask;
import com.mightyoung.service.task.CrawlListingProductURLTask;
import com.mightyoung.service.task.CrawlListingRankTask;
import com.mightyoung.service.task.CrawlListingTitleTask;
import com.mightyoung.service.task.CrawlListingCategoryTask;
import com.mightyoung.service.task.GetKeyWordsListTask;
import com.mightyoung.service.task.OutputListingRankResultTask;
import com.mightyoung.service.task.OutputListingTitileResultTask;

public class ListingTitleCrawler extends Crawler{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		this.crawlername = "listing标题爬虫";
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
		String storagepath = ARE.getProperty("ListingTitleStoragePath");
		String resultsheet = ARE.getProperty("ResultSheetName");
		
		ClearXLSXStorageTask t1 = new ClearXLSXStorageTask(storagepath,resultsheet);
		t1.run();
		GetKeyWordsListTask t2 = new GetKeyWordsListTask();
		t2.run();
		CrawlListingCategoryTask t3 = new CrawlListingCategoryTask(t2.keywords);
		t3.run();
		CrawlListingCategoryProductUrlTask t4 = new CrawlListingCategoryProductUrlTask(t3.categoryurl);
		t4.run();
		CrawlListingTitleTask t5 = new CrawlListingTitleTask(t4.producturls);
		t5.run();
		OutputListingTitileResultTask t6 = new OutputListingTitileResultTask(t5.titleurlmap);
		t6.run();
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
