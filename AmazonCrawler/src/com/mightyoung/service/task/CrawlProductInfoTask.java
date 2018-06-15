package com.mightyoung.service.task;

import java.util.ArrayList;

import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.ProductInfo;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.service.parser.impl.GoogleListingParser;

public class CrawlProductInfoTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected ArrayList<String> producturls = new ArrayList<String>();
	protected String taskpath = ARE.getProperty("GoogleListingTaskTempPath");
	public ArrayList<ProductInfo> productinfolist = new ArrayList<ProductInfo>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public CrawlProductInfoTask() {
		taskid = "CrawlProductInfoTask" + System.currentTimeMillis();
	}
	public CrawlProductInfoTask(ArrayList<String> producturllist) {
		taskid = "CrawlProductInfoTask" + System.currentTimeMillis();
		this.producturls = producturllist;
	}
	@Override
	public void run() {
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("��ȡ�ؼ����б�ʧ�ܣ�",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}
	@Override
	public void taskmain() {
		for(String url : producturls) {
			DefaultDownloader downloader = new DefaultDownloader();
			Document doc = downloader.getPageDocument(url);
			if(doc.select("div[id=availability_feature_div]>div[id=availability]")==null) {
				break;
			}
			GoogleListingParser parser = new GoogleListingParser();
			ProductInfo currentproduct = parser.parseProductInfo(doc);
			currentproduct.setProducturl(url);
			productinfolist.add(currentproduct);
		}
			
	}
}