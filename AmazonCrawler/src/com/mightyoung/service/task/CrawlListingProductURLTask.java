package com.mightyoung.service.task;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.service.spider.impl.ListingProductSpider;

public class CrawlListingProductURLTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected String keywordstr;
	public ArrayList<String> producturls = new ArrayList<String>();
	protected int maxcount=Integer.parseInt(ARE.getProperty("listingmaxpage","0"));
	public static void main(String[] args) {
		ARE.init("etc/are.xml");
		String testkeywords = "reborn+baby+dolls";
		CrawlListingProductURLTask testtask = new CrawlListingProductURLTask();
		testtask.keywordstr = testkeywords;
		testtask.run();
		int count = 0;
		for(String url : testtask.producturls) {
			ARE.getLog().info(url);
			count++;
		}
		ARE.getLog().info("����listing��ѯ����" + count + "����Ʒ");
	}

	public CrawlListingProductURLTask() {
		taskid = "CrawlListingProductURLTask" + System.currentTimeMillis();
	}
	public CrawlListingProductURLTask(String str) {
		taskid = "CrawlListingProductURLTask" + System.currentTimeMillis();
		this.keywordstr = str;
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
		if(this.keywordstr==null || this.keywordstr.isEmpty()) {
			ARE.getLog().info("�ؼ���Ϊ�գ�����");
			return;
		}
		String urlpattern = "https://www.amazon.com/s/?url=search-alias%3Daps&field-keywords=";
		String targeturl = urlpattern + keywordstr;
		ARE.getLog().info("���β�ѯ�����urlΪ:" + targeturl);
		ARE.getLog().info("��ʼ��ȡ���������ؼ��ʵ�'" + keywordstr + "'����Ʒ�б�");
		ARE.getLog().info("���ι���ȡ" + maxcount + "ҳ");
		ListingProductSpider productlistspider = new ListingProductSpider();
		while(targeturl != null && maxcount != 0) {
			ArrayList<String> result = productlistspider.getAllProductUrl(targeturl);
			for(String s : result) {
				producturls.add(s);
			}
			String nexttargetUrl = productlistspider.getSingalNextPage(targeturl);
			ARE.getLog().info("��һҳurl:" + nexttargetUrl);
			maxcount--;
		}
	}

}
