package com.mightyoung.service.task;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.service.spider.impl.ListingProductSpider;

public class CrawlListingCategoryProductUrlTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected String categoryurl;
	public ArrayList<String> producturls = new ArrayList<String>();
	protected int maxcount = Integer.parseInt(ARE.getProperty("listingmaxpage","0"));
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public CrawlListingCategoryProductUrlTask() {
		taskid = "CrawlListingCategoryProductUrlTask" + System.currentTimeMillis();
	}
	public CrawlListingCategoryProductUrlTask(String url) {
		taskid = "CrawlListingCategoryProductUrlTask" + System.currentTimeMillis();
		this.categoryurl = url;
	}
	
	@Override
	public void run() {
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("爬取小类目Listing商品链接失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}
	@Override
	public void taskmain() {
		// TODO Auto-generated method stub
		if(this.categoryurl == null || this.categoryurl.isEmpty()) {
			ARE.getLog().info("没有获取到小类目入口！！！");
			return;
		}
		String targeturl = this.categoryurl;
		ARE.getLog().info("本次查询的小类目入口url为:" + targeturl);
		ListingProductSpider productlistspider = new ListingProductSpider();
		while(targeturl != null && maxcount != 0) {
			ArrayList<String> result = productlistspider.getAllProductUrl(targeturl);
			for(String s : result) {
				producturls.add(s);
			}
			String nexttargetUrl = productlistspider.getSingalNextPage(targeturl);
			ARE.getLog().info("下一页url:" + nexttargetUrl);
			targeturl = nexttargetUrl;
			maxcount--;
		}
	}

}
