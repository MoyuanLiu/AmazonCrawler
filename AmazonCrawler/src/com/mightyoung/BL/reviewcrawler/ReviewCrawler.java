package com.mightyoung.BL.reviewcrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.Crawler;
import com.mightyoung.service.task.CrawlStoreReviewTask;

public class ReviewCrawler extends Crawler{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		this.crawlername = "客户评价爬虫";
		this.excutorservice = Executors.newFixedThreadPool(1);
		this.tasklist = new ArrayList<Task>();
		ARE.getLog().info(this.crawlername+"初始化完成");
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
		Task t1 = new CrawlStoreReviewTask();
		this.tasklist.add(t1);
		ARE.getLog().info(this.crawlername+"启动!!");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ARE.getLog().info(this.crawlername+"开始执行爬取任务!!");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		this.excutorservice.shutdown();
		ARE.getLog().info(this.crawlername+"已关闭");
	}

}
