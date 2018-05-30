package com.mightyoung.service.task;

import java.util.ArrayList;

import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.Store;
import com.mightyoung.model.StoreUrlInfo;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.service.spider.impl.ProductCrawlerSpider;


public class CrawlStoreProductTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected ArrayList<Store> stores;
	public ArrayList<String> producturls = new ArrayList<String>();
	public CrawlStoreProductTask() {
		
	}
	public CrawlStoreProductTask(ArrayList<Store> storelist) {
		taskid = "CrawlStoreProductTask" + System.currentTimeMillis();
		stores = storelist;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("爬取店铺商品链接任务执行失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}
	
	@Override
	public void taskmain() {
		// 1.解析传过来的店铺列表
		for(Store s : stores) {
			ARE.getLog().info("开始爬取店铺" + s.getStoreid() + "商品列表");
			//开始爬取商品列表，并翻页
			ProductCrawlerSpider reviewSpider = new ProductCrawlerSpider();
			String targetUrl = s.getStoreurl();
			while(targetUrl != null) {
				ArrayList<String> result = reviewSpider.getAllProductUrl(targetUrl);
				for(String producturl : result) {
					producturls.add(producturl);
				}
				String nexttargetUrl = reviewSpider.getSingalNextPage(targetUrl);
				ARE.getLog().info("下一页url:" + nexttargetUrl);
				targetUrl = nexttargetUrl;
			}
		}
	}
	public static void main(String[] args) {
		Store testStore = new Store();
		testStore.setStoreid("StoreTest01");
		testStore.setStorename("TestShop");
		testStore.setStoreurl("https://www.amazon.com/s?marketplaceID=ATVPDKIKX0DER&me=AR7H1RL9GCUCS&merchant=AR7H1RL9GCUCS");
		ArrayList<Store> teststores = new ArrayList<Store>();
		teststores.add(testStore);
		CrawlStoreProductTask testtask = new CrawlStoreProductTask(teststores);
		int count = 0;
		testtask.run();
		for(String url : testtask.producturls) {
			ARE.getLog().info(url);
			count++;
		}
		ARE.getLog().info("本店铺共有" + count + "件商品");
	}
}
