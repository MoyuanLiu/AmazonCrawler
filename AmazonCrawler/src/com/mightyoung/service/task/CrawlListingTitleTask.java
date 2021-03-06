package com.mightyoung.service.task;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.service.parser.impl.ListingRankParser;

public class CrawlListingTitleTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected ArrayList<String> producturls = new ArrayList<String>();
	public HashMap<String,String> titleurlmap = new HashMap<String,String>();
	protected String titleparsepath = "span[id=productTitle]";
	public static void main(String[] args) {
		
	}
	public CrawlListingTitleTask() {
		taskid = "CrawlListingTitleTask" + System.currentTimeMillis();
	}
	public CrawlListingTitleTask(ArrayList<String> producturllist) {
		taskid = "CrawlListingTitleTask" + System.currentTimeMillis();
		this.producturls = producturllist;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("爬取ListingTitle失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		ARE.getLog().info("共有" + producturls.size() + "件商品");
		for(String producturl : producturls) {
			DefaultDownloader downloader = new DefaultDownloader();
			Document doc = downloader.getPageDocument(producturl);
			String html = StringEscapeUtils.unescapeHtml(doc.toString());
			ListingRankParser listrankparser = new ListingRankParser();
			String title = listrankparser.getProductTitle(html, titleparsepath);
			if(title != null && !title.isEmpty()) {
				titleurlmap.put(producturl,title);
				ARE.getLog().info("title:" + title);
			}else {
				ARE.getLog().info("当前商品获取标题失败！！");
				ARE.getLog().info("失败商品url为[" + producturl+"]");
			}
		}
	}

}
