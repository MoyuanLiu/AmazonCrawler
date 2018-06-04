package com.mightyoung.service.task;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.service.spider.impl.ListingProductSpider;

public class CrawlListingProductURLTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected String keywordstr;
	public ArrayList<String> producturls = new ArrayList<String>();
	protected int maxcount=Integer.parseInt(ARE.getProperty("listingmaxpage","0"));
	public static void main(String[] args) {
		ARE.init("etc/are.xml");
		String testkeywords = "reborn+baby+dolls";
		CrawlListingProductURLTask testtask = new CrawlListingProductURLTask();
		testtask.keywordstr = testkeywords;
		testtask.run();
		int count = 0;
		for(String url : testtask.producturls) {
			ARE.getLog().info(url);
			count++;
		}
		ARE.getLog().info("本次listing查询共有" + count + "件商品");
	}

	public CrawlListingProductURLTask() {
		taskid = "CrawlListingProductURLTask" + System.currentTimeMillis();
	}
	public CrawlListingProductURLTask(String str) {
		taskid = "CrawlListingProductURLTask" + System.currentTimeMillis();
		this.keywordstr = str;
	}
	@Override
	public void run() {
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("获取关键词列表失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
		
	}

	@Override
	public void taskmain() {
		if(this.keywordstr==null || this.keywordstr.isEmpty()) {
			ARE.getLog().info("关键词为空！！！");
			return;
		}
		String urlpattern = "https://www.amazon.com/s/?url=search-alias%3Daps&field-keywords=";
		String targeturl = urlpattern + keywordstr;
		ARE.getLog().info("本次查询的入口url为:" + targeturl);
		ARE.getLog().info("开始爬取本次搜索关键词的'" + keywordstr + "'的商品列表");
		ARE.getLog().info("本次共爬取" + maxcount + "页");
		ListingProductSpider productlistspider = new ListingProductSpider();
		while(targeturl != null && maxcount != 0) {
			ArrayList<String> result = productlistspider.getAllProductUrl(targeturl);
			for(String s : result) {
				producturls.add(s);
			}
			String nexttargetUrl = productlistspider.getSingalNextPage(targeturl);
			ARE.getLog().info("下一页url:" + nexttargetUrl);
			maxcount--;
		}
	}

}
