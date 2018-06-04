package com.mightyoung.BL.reviewcrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.Crawler;
import com.mightyoung.service.task.ClearReviewStorageTask;
import com.mightyoung.service.task.CrawlAllReviewTask;
import com.mightyoung.service.task.CrawlProductReviewTask;
import com.mightyoung.service.task.CrawlStoreProductTask;
import com.mightyoung.service.task.GetStoreListTask;
import com.mightyoung.service.task.OutputAllReviewTask;

public class ReviewCrawler extends Crawler{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReviewCrawler reviewcrawler = new ReviewCrawler();
		System.out.println("开始爬取");
		reviewcrawler.init();
		reviewcrawler.run();
		reviewcrawler.shutdown();
		System.out.println("爬取结束");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		this.crawlername = "客户评价爬虫";
		this.excutorservice = Executors.newFixedThreadPool(1);
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
//		boolean flag = true;
//		while(flag) {
//			System.out.println("欢迎使用亚马逊店铺商品评价爬虫");
//		}
//		
//		System.out.println("即将清空已经存储的数据，继续执行？");
		ClearReviewStorageTask t1 = new ClearReviewStorageTask();
		t1.run();
		GetStoreListTask t2 = new GetStoreListTask();
		t2.run();
		CrawlStoreProductTask t3 = new CrawlStoreProductTask(t2.storelist);
		t3.run();
		CrawlProductReviewTask t4 = new CrawlProductReviewTask(t3.storeidproducturlmap);
		t4.run();
		CrawlAllReviewTask t5 = new CrawlAllReviewTask(t4.productreviewmap);
		t5.run();
		OutputAllReviewTask t6 = new OutputAllReviewTask(t5.productallreviewmap);
		t6.run();
		long endtime = System.currentTimeMillis();
		int time = (int)(endtime-starttime);
		ARE.getLog().info("大约用时：" + time/60000 + "分钟");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		this.excutorservice.shutdown();
		ARE.getLog().info(this.crawlername+"已关闭");
	}

}
