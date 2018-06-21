package com.mightyoung.service.task;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.ProductInfo;
import com.mightyoung.service.spider.impl.BaiduListingSpider;
import com.mightyoung.service.spider.impl.GoogleListingSpider;
import com.mightyoung.util.FileIOUtil;

public class CrawlGoogleListingUrlTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected String queryurl = "";
	protected String tempfilepath = ARE.getProperty("GoogleListingTaskTempPath");
	protected boolean searchsource = true;
	public ArrayList<String> producturls = new ArrayList<String>();
	public ArrayList<ProductInfo> allproductinfolist = new ArrayList<ProductInfo>();
	
	public static void main(String[] args) {
		
	}
	
	public CrawlGoogleListingUrlTask() {
		taskid = "CrawlGoogleListingUrlTask" + System.currentTimeMillis();
	}
	public CrawlGoogleListingUrlTask(String queryurlstr,boolean flag) {
		taskid = "CrawlGoogleListingUrlTask" + System.currentTimeMillis();
		this.searchsource = flag;
		this.queryurl = queryurlstr;
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
		if(queryurl == null || queryurl.isEmpty()) {
			ARE.getLog().info("查询入口url为空！！！");
			return;
		}
		String targeturl = queryurl;
		if(searchsource == true) {
			//如果检索源为谷歌
			GoogleListingSpider googlelistingspider = new GoogleListingSpider();
			while(targeturl != null) {
				ArrayList<String> result = googlelistingspider.getAllProductUrls(targeturl);
				if(result == null) {
					ARE.getLog().info("当前搜索结果页面没有获取的商品链接");
					ARE.getLog().info("当前搜索结果页面:[" + targeturl + "]");
					return;
				}
				CrawlProductInfoTask childtask = new CrawlProductInfoTask(result);
				childtask.run();
				if(childtask.productinfolist != null) {
					for(ProductInfo productinfo : childtask.productinfolist) {
						allproductinfolist.add(productinfo);
					}
				}
				String nexttargetUrl = googlelistingspider.getSingalNextPage(targeturl);
				ARE.getLog().info("下一页url:" + nexttargetUrl);
				targeturl = nexttargetUrl;
			}
		}else{
			//如果检索源为百度
			BaiduListingSpider baidulistingspider = new BaiduListingSpider();
			while(targeturl != null) {
				ArrayList<String> result = baidulistingspider.getAllProductUrls(targeturl);
				if(result == null) {
					ARE.getLog().info("当前搜索结果页面没有获取的商品链接");
					ARE.getLog().info("当前搜索结果页面:[" + targeturl + "]");
					return;
				}
				CrawlProductInfoTask childtask = new CrawlProductInfoTask(result);
				childtask.run();
				if(childtask.productinfolist != null) {
					for(ProductInfo productinfo : childtask.productinfolist) {
						allproductinfolist.add(productinfo);
					}
				}
				String nexttargetUrl = baidulistingspider.getSingalNextPage(targeturl);
				ARE.getLog().info("下一页url:" + nexttargetUrl);
				targeturl = nexttargetUrl;
			}
		}
		
	}

}
