package com.mightyoung.BL.listingpositioncrawler;

import com.amarsoft.are.ARE;
import com.mightyoung.model.Crawler;
import com.mightyoung.service.task.ClearXLSXBookTask;
import com.mightyoung.service.task.GetQueryListTask;

public class ListingPositionCrawler extends Crawler{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {
		this.crawlername = "listing��λ����";
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
		String storagepath = ARE.getProperty("ListingPositionStoragePath");
		ClearXLSXBookTask t1 = new ClearXLSXBookTask(storagepath);
		t1.run();
		GetQueryListTask t2 = new GetQueryListTask();
		t2.run();
		
		long endtime = System.currentTimeMillis();
		int time = (int)(endtime-starttime);
		ARE.getLog().info("��Լ��ʱ��" + time/60000 + "����");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
