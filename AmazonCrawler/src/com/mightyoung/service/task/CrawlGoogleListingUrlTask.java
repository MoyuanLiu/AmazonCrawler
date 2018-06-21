package com.mightyoung.service.task;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.ProductInfo;
import com.mightyoung.service.spider.impl.GoogleListingSpider;
import com.mightyoung.util.FileIOUtil;

public class CrawlGoogleListingUrlTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected String queryurl = "";
	protected String tempfilepath = ARE.getProperty("GoogleListingTaskTempPath");
	public ArrayList<String> producturls = new ArrayList<String>();
	public ArrayList<ProductInfo> allproductinfolist = new ArrayList<ProductInfo>();
	public CrawlGoogleListingUrlTask() {
		taskid = "CrawlGoogleListingUrlTask" + System.currentTimeMillis();
	}
	public CrawlGoogleListingUrlTask(String queryurlstr) {
		taskid = "CrawlGoogleListingUrlTask" + System.currentTimeMillis();
		this.queryurl = queryurlstr;
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
		if(queryurl == null || queryurl.isEmpty()) {
			ARE.getLog().info("��ѯ���urlΪ�գ�����");
			return;
		}
		String targeturl = queryurl;
		GoogleListingSpider googlelistingspider = new GoogleListingSpider();
		while(targeturl != null) {
			ArrayList<String> result = googlelistingspider.getAllProductUrls(targeturl);
			if(result == null) {
				ARE.getLog().info("��ǰ�������ҳ��û�л�ȡ����Ʒ����");
				ARE.getLog().info("��ǰ�������ҳ��:[" + targeturl + "]");
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
			ARE.getLog().info("��һҳurl:" + nexttargetUrl);
			targeturl = nexttargetUrl;
		}
	}

}
