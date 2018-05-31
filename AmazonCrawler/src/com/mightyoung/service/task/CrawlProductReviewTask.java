package com.mightyoung.service.task;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.Product;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.service.parser.impl.ProductASINParser;
import com.mightyoung.service.spider.impl.ReviewRootSpider;

public class CrawlProductReviewTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	public HashMap<String,Product> productreviewmap = new HashMap<String,Product>();
	protected HashMap<String,ArrayList<String>> storeproducturlmap;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> producturltestlist = new ArrayList<String>();
		producturltestlist.add("https://www.amazon.com/MiYang-Winter-Womens-Indoor-Slipper/dp/B01M5925MU/ref=sr_1_14/131-5359031-1404252?m=AR7H1RL9GCUCS&s=merchant-items&ie=UTF8&qid=1527666645&sr=1-14");
//		CrawlProductReviewTask testTask = new CrawlProductReviewTask(producturltestlist);
//		testTask.run();
//		for(String s : testTask.productreviewmap.keySet()) {
//			ARE.getLog().info("������ڣ�" + s);
//			ARE.getLog().info("��Ʒasin:" + testTask.productreviewmap.get(s).getAsin());
//		}
//		
	}
	public CrawlProductReviewTask() {
		taskid = "CrawlStoreProductTask" + System.currentTimeMillis();
	}
	
	public CrawlProductReviewTask(HashMap<String,ArrayList<String>> storeidproducturlmap) {
		taskid = "CrawlStoreProductTask" + System.currentTimeMillis();
		this.storeproducturlmap = storeidproducturlmap;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("��ȡ������Ʒ��������ִ��ʧ�ܣ�",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		for(String storeid : storeproducturlmap.keySet()) {
			// 1.��������������Ʒ����
			for(String producturl : storeproducturlmap.get(storeid)) {
				Product p = new Product();
				p.setProductstoreid(storeid);
				p.setProducturl(producturl);
				DefaultDownloader downloader = new DefaultDownloader();
				Document doc = downloader.getPageDocument(producturl);
				String html = StringEscapeUtils.unescapeHtml(doc.toString());
				ProductASINParser asinparser = new ProductASINParser();
				p.setAsin(asinparser.getProductASIN(html, "div[id=detailBullets_feature_div]>ul>li>span[class=a-list-item]"));
				ReviewRootSpider reviewspider = new ReviewRootSpider();
				String reviewurl = reviewspider.getReviewRootUrl(producturl);
				if(reviewurl!=null && p.getAsin()!=null && !p.getAsin().isEmpty()) {
					productreviewmap.put(reviewurl,p);
					ARE.getLog().info("��Ʒasin:" + p.getAsin());
					ARE.getLog().info("�������url:" + reviewurl);
				}
				
			}
		}
		
		
	}

}
