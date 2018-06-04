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
		System.out.println("��ʼ��ȡ");
		reviewcrawler.init();
		reviewcrawler.run();
		reviewcrawler.shutdown();
		System.out.println("��ȡ����");
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
		long starttime = System.currentTimeMillis();
		ARE.getLog().info(this.crawlername+"��ʼִ����ȡ����!!");
//		boolean flag = true;
//		while(flag) {
//			System.out.println("��ӭʹ������ѷ������Ʒ��������");
//		}
//		
//		System.out.println("��������Ѿ��洢�����ݣ�����ִ�У�");
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
		ARE.getLog().info("��Լ��ʱ��" + time/60000 + "����");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		this.excutorservice.shutdown();
		ARE.getLog().info(this.crawlername+"�ѹر�");
	}

}
