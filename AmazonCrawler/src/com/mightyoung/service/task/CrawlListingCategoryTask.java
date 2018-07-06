package com.mightyoung.service.task;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.service.spider.impl.ListingCategorySpider;

public class CrawlListingCategoryTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected String keywordstr;
	public String categoryurl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public CrawlListingCategoryTask() {
		taskid = "CrawlListingCategoryTask" + System.currentTimeMillis();
	}
	public CrawlListingCategoryTask(String str) {
		taskid = "CrawlListingCategoryTask" + System.currentTimeMillis();
		this.keywordstr = str;
	}
	@Override
	public void run() {
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("爬取小类目链接任务失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		// TODO Auto-generated method stub
		if(this.keywordstr == null || this.keywordstr.isEmpty()) {
			ARE.getLog().info("关键词为空！！！");
			return;
		}
		String urlpattern = "https://www.amazon.com/s/?url=search-alias%3Daps&field-keywords=";
		String targeturl = urlpattern + keywordstr;
		ARE.getLog().info("本次查询的入口url为:" + targeturl);
		ARE.getLog().info("本次搜索关键词为[" + keywordstr + "]");
		ListingCategorySpider categoryspider = new ListingCategorySpider();
		categoryurl = categoryspider.getCategoryUrl(targeturl);
	}

}
