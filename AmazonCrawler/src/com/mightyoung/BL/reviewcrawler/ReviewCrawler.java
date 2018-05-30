package com.mightyoung.BL.reviewcrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.Crawler;
import com.mightyoung.service.task.CrawlProductReviewTask;
import com.mightyoung.service.task.CrawlStoreProductTask;
import com.mightyoung.service.task.GetStoreListTask;

public class ReviewCrawler extends Crawler{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReviewCrawler reviewcrawler = new ReviewCrawler();
		reviewcrawler.init();
		reviewcrawler.run();
		reviewcrawler.shutdown();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		this.crawlername = "�ͻ���������";
		this.excutorservice = Executors.newFixedThreadPool(1);
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
		ARE.getLog().info(this.crawlername+"��ʼִ����ȡ����!!");
		GetStoreListTask t1 = new GetStoreListTask();
		t1.run();
		CrawlStoreProductTask t2 = new CrawlStoreProductTask(t1.storelist);
		t2.run();
		CrawlProductReviewTask t3 = new CrawlProductReviewTask(t2.producturls);
		t3.run();
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		this.excutorservice.shutdown();
		ARE.getLog().info(this.crawlername+"�ѹر�");
	}

}
