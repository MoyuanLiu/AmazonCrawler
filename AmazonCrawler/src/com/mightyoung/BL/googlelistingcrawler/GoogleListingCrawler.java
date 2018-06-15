package com.mightyoung.BL.googlelistingcrawler;

import com.amarsoft.are.ARE;
import com.mightyoung.model.Crawler;
import com.mightyoung.service.task.ClearXLSXStorageTask;
import com.mightyoung.service.task.GetGoogleListingQueryStrTask;

public class GoogleListingCrawler extends Crawler{
	protected String storagepath = ARE.getProperty("GoogleListingStoragePath","data/googlelistingresult.xlsx");
	protected String resultsheet = ARE.getProperty("GoogleListingSheetName","爬取结果表");
	public static void main(String[] args) {
		GoogleListingCrawler googlelistingcrawler = new GoogleListingCrawler();
		System.out.println("开始爬取");
		googlelistingcrawler.init();
		googlelistingcrawler.startup();
		googlelistingcrawler.run();
		googlelistingcrawler.shutdown();
		System.out.println("爬取结束");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ARE.init("etc/are.xml");
		this.crawlername = "谷歌僵尸listing爬虫";
		ARE.getLog().info(this.crawlername+"初始化完成");
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		ARE.getLog().info(this.crawlername + "启动!!");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long starttime = System.currentTimeMillis();
		ARE.getLog().info(this.crawlername + "开始执行爬取任务!!");
		ClearXLSXStorageTask t1 = new ClearXLSXStorageTask(storagepath,resultsheet);
		t1.run();
		GetGoogleListingQueryStrTask t2 = new GetGoogleListingQueryStrTask();
		t2.run();
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
