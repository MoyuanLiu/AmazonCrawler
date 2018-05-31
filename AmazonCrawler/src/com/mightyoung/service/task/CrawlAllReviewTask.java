package com.mightyoung.service.task;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.Product;
import com.mightyoung.model.Review;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.service.parser.impl.ReviewParser;
import com.mightyoung.service.spider.impl.ReviewSpider;

public class CrawlAllReviewTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected HashMap<String,Product> productreviewmap;
	public HashMap<String,ArrayList<Review>> productallreviewmap = new HashMap<String,ArrayList<Review>>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public CrawlAllReviewTask() {
		taskid = "CrawlAllReviewTask" + System.currentTimeMillis();
	}
	public CrawlAllReviewTask(HashMap<String,Product> productreviewmapper) {
		taskid = "CrawlAllReviewTask" + System.currentTimeMillis();
		productreviewmap = productreviewmapper;
	}
	@Override
	public void run() {
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("爬取店铺商品评价任务执行失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		//1.解析评论入口url
		for(String reviewrooturl : productreviewmap.keySet()) {
			String targeturl = reviewrooturl;
			ArrayList<Review> reviewlist = new ArrayList<Review>();
			while(targeturl != null) {
				DefaultDownloader downloader = new DefaultDownloader();
				Document doc = downloader.getPageDocument(targeturl);
				String html = StringEscapeUtils.unescapeHtml(doc.toString());
				ReviewParser reviewparser = new ReviewParser();
				ARE.getLog().info("当前评论页面url:" + targeturl);
				ArrayList<Review> currentreviewlist = reviewparser.getAllReview(html, "div[id=cm_cr-review_list]>div[id]");
				for(Review r : currentreviewlist) {
					reviewlist.add(r);
				}
				ReviewSpider reviewspider = new ReviewSpider();
				String nexttargeturl = reviewspider.getSingalNextPage(targeturl);
				ARE.getLog().info("当前评论下一页:"+nexttargeturl);
				targeturl = nexttargeturl;
			}
			productallreviewmap.put(productreviewmap.get(reviewrooturl).toString(), reviewlist);
		}
	}

}
