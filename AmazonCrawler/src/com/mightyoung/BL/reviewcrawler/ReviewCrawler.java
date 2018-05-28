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
		this.crawlername = "�ͻ���������";
		this.excutorservice = Executors.newFixedThreadPool(1);
		this.tasklist = new ArrayList<Task>();
		ARE.getLog().info(this.crawlername+"��ʼ�����");
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
		Task t1 = new CrawlStoreReviewTask();
		this.tasklist.add(t1);
		ARE.getLog().info(this.crawlername+"����!!");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ARE.getLog().info(this.crawlername+"��ʼִ����ȡ����!!");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		this.excutorservice.shutdown();
		ARE.getLog().info(this.crawlername+"�ѹر�");
	}

}
