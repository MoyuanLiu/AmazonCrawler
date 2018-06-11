package com.mightyoung.service.task;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.enums.ReviewFilterEnum;
import com.mightyoung.enums.ReviewSorterEnum;
import com.mightyoung.model.Product;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.service.parser.impl.ProductASINParser;
import com.mightyoung.service.spider.impl.ReviewRootSpider;

public class CrawlProductReviewTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	public HashMap<String,Product> productreviewmap = new HashMap<String,Product>();
	protected HashMap<String,ArrayList<String>> storeproducturlmap;
	protected String asinpath = "div[id=detailBullets_feature_div]>ul>li>span[class=a-list-item]";
	protected String asinpathbk = "div[id=detail_bullets_id]>table>tbody>tr>td[class=bucket]>div[class=content]>ul>li";
	public static void main(String[] args) {
		ArrayList<String> producturllist = new ArrayList<String>();
		producturllist.add("https://www.amazon.de/Summer-Mae-Streifen-Badekleider-Marineblau/dp/B013OSTFWS/ref=sr_1_1/260-4942877-7555723?m=A5ZLZ1NEB7UOZ&s=merchant-items&ie=UTF8&qid=1528363704&sr=1-1");
		HashMap<String,ArrayList<String>> storeidproducturlmap = new HashMap<String,ArrayList<String>>();
		storeidproducturlmap.put("teststore", producturllist);
		CrawlProductReviewTask testtask = new CrawlProductReviewTask(storeidproducturlmap);
		testtask.run();
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
			ARE.getLog().error("爬取店铺商品评价任务执行失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		for(String storeid : storeproducturlmap.keySet()) {
			// 1.解析传过来的商品链接
			for(String producturl : storeproducturlmap.get(storeid)) {
				Product p = new Product();
				p.setProductstoreid(storeid);
				p.setProducturl(producturl);
				DefaultDownloader downloader = new DefaultDownloader();
				Document doc = downloader.getPageDocument(producturl);
				String html = StringEscapeUtils.unescapeHtml(doc.toString());
				ProductASINParser asinparser = new ProductASINParser();
				String asin
				if(asinparser.getProductASIN(html, asinpath)==null||asinparser.getProductASIN(html, asinpath).isEmpty()) {
					asinparser.getProductASIN(html, asinpathbk);
				}
				String asin = asinparser.getProductASIN(html, asinpath)==null?asinparser.getProductASIN(html, asinpathbk):asinparser.getProductASIN(html, asinpath);
				p.setAsin(asin);
				ReviewRootSpider reviewspider = new ReviewRootSpider();
				String reviewurl = reviewspider.getReviewRootUrl(producturl,ReviewFilterEnum.critical,ReviewSorterEnum.MostRecent);
				if(reviewurl==null) {
					ARE.getLog().info("未获取到评论入口url");
				}else if(p.getAsin()==null || p.getAsin().isEmpty()){
					ARE.getLog().info("未获取到产品asin");
				}
				if(reviewurl!=null && p.getAsin()!=null && !p.getAsin().isEmpty()) {
					productreviewmap.put(reviewurl,p);
					ARE.getLog().info("商品asin:" + p.getAsin());
					ARE.getLog().info("评论入口url:" + reviewurl);
				}
				
			}
		}
	}

}
