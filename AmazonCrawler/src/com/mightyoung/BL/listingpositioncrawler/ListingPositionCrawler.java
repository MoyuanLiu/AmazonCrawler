package com.mightyoung.BL.listingpositioncrawler;

import com.amarsoft.are.ARE;
import com.mightyoung.model.Crawler;
import com.mightyoung.service.task.ClearXLSXBookTask;
import com.mightyoung.service.task.ClearXLSXStorageTask;
import com.mightyoung.service.task.GetQueryListTask;
import com.mightyoung.service.task.OutputListingPositionTask;
import com.mightyoung.service.task.QueryListingTask;

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
		String resultsheet = ARE.getProperty("GoogleListingSheetName");
		ClearXLSXStorageTask t1 = new ClearXLSXStorageTask(storagepath,resultsheet);
		t1.run();
		GetQueryListTask t2 = new GetQueryListTask();
		t2.run();
		QueryListingTask t3 = new QueryListingTask(t2.tasklist);
		t3.run();
		OutputListingPositionTask t4 = new OutputListingPositionTask(t3.productresult);
		t4.run();
		
		long endtime = System.currentTimeMillis();
		int time = (int)(endtime-starttime);
		ARE.getLog().info("��Լ��ʱ��" + time/60000 + "����");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
