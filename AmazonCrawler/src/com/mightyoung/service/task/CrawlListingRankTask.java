package com.mightyoung.service.task;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.Rank;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.service.parser.impl.ListingRankParser;

public class CrawlListingRankTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected ArrayList<String> producturls = new ArrayList<String>();
	public HashMap<String,ArrayList<String>> titlerankmap = new HashMap<String,ArrayList<String>>();
	protected String titleparsepath = "span[id=productTitle]";
	protected String rankparsepath = "table[id=productDetails_detailBullets_sections1]>tbody>tr>th";
	protected String rankparsepathbackup = "tr[id=SalesRank]>td[class=value]";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testproducturl = "https://www.amazon.com/NPK-collection-Silicone-Lifelike-Children/dp/B00ZR52VQO/ref=sr_1_28?s=toys-and-games&ie=UTF8&qid=1528278569&sr=1-28&keywords=reborn+baby+dolls";
//		https://www.amazon.com/Yesteria-Silicone-Reborn-Elephant-Outfit/dp/B077HNP6TL/ref=sr_1_27?s=toys-and-games&ie=UTF8&qid=1528272947&sr=1-27&keywords=reborn%2Bbaby%2Bdolls&th=1
//		https://www.amazon.com/iCradle-Realistic-Lifelike-Silicone-Christmas/dp/B0725Z182Z/ref=sr_1_7?s=toys-and-games&ie=UTF8&qid=1528269017&sr=1-7&keywords=reborn+baby+dolls
		CrawlListingRankTask testtask = new CrawlListingRankTask();
		testtask.producturls.add(testproducturl);
		testtask.run();
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
		ARE.getLog().info("共有" + producturls.size() + "件商品");
		for(String producturl : producturls) {
			DefaultDownloader downloader = new DefaultDownloader();
			Document doc = downloader.getPageDocument(producturl);
			String html = StringEscapeUtils.unescapeHtml(doc.toString());
			ListingRankParser listrankparser = new ListingRankParser();
			String title = listrankparser.getProductTitle(html, titleparsepath);
			ArrayList<String> ranks = listrankparser.getProductRank(html, rankparsepath);
			if(ranks != null && !ranks.isEmpty()) {
				titlerankmap.put(title,ranks);
				ARE.getLog().info("title:" + title);
				ARE.getLog().info("rank:" + ranks.toString());
				ARE.getLog().info("本条记录共有" + ranks.size()+"评级");
			}else {
				ArrayList<String> rankbackup = listrankparser.getProductRankBackup(html, rankparsepathbackup);
				titlerankmap.put(title,rankbackup);
				ARE.getLog().info("title:" + title);
				ARE.getLog().info("rankbackup:" + rankbackup.toString());
				ARE.getLog().info("本条记录共有" + rankbackup.size()+"评级");
			}
		}
	}

}
