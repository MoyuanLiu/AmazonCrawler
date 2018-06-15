package com.mightyoung.BL.googlelistingcrawler;

import com.amarsoft.are.ARE;
import com.mightyoung.model.Crawler;
import com.mightyoung.service.task.ClearXLSXStorageTask;
import com.mightyoung.service.task.GetGoogleListingQueryStrTask;

public class GoogleListingCrawler extends Crawler{
	protected String storagepath = ARE.getProperty("GoogleListingStoragePath","data/googlelistingresult.xlsx");
	protected String resultsheet = ARE.getProperty("GoogleListingSheetName","��ȡ�����");
	public static void main(String[] args) {
		GoogleListingCrawler googlelistingcrawler = new GoogleListingCrawler();
		System.out.println("��ʼ��ȡ");
		googlelistingcrawler.init();
		googlelistingcrawler.startup();
		googlelistingcrawler.run();
		googlelistingcrawler.shutdown();
		System.out.println("��ȡ����");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		this.crawlername = "�ȸ轩ʬlisting����";
		ARE.getLog().info(this.crawlername+"��ʼ�����");
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		ARE.getLog().info(this.crawlername + "����!!");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long starttime = System.currentTimeMillis();
		ARE.getLog().info(this.crawlername + "��ʼִ����ȡ����!!");
		ClearXLSXStorageTask t1 = new ClearXLSXStorageTask(storagepath,resultsheet);
		t1.run();
		GetGoogleListingQueryStrTask t2 = new GetGoogleListingQueryStrTask();
		t2.run();
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
