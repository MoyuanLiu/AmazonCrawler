package com.mightyoung.service.task;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.service.parser.impl.ListingRankParser;

public class CrawlListingRankTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected ArrayList<String> producturls = new ArrayList<String>();
	public HashMap<String,String> titlerankmap = new HashMap<String,String>();
	protected String titleparsepath = "span[id=productTitle]";
	protected String rankparsepath = "table#productDetails_detailBullets_sections1>th:contains(Best Sellers Rank)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testproducturl = "";
		
	}

	public CrawlListingRankTask() {
		taskid = "CrawlListingRankTask" + System.currentTimeMillis();
	}
	public CrawlListingRankTask(ArrayList<String> producturllist) {
		taskid = "CrawlListingRankTask" + System.currentTimeMillis();
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
			ARE.getLog().error("获取关键词列表失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		// TODO Auto-generated method stub
		for(String producturl : producturls) {
			DefaultDownloader downloader = new DefaultDownloader();
			Document doc = downloader.getPageDocument(producturl);
			String html = StringEscapeUtils.unescapeHtml(doc.toString());
			ListingRankParser listrankparser = new ListingRankParser();
			String title = listrankparser.getProductTitle(html, titleparsepath);
			String rank = listrankparser.getProductRank(html, rankparsepath);
			ARE.getLog().info("title:" + title);
			ARE.getLog().info("rank:" + rank);
			titlerankmap.put(title, rank);
		}
	}

}
