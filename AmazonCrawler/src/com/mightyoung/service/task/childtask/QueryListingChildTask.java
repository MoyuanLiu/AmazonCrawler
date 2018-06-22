package com.mightyoung.service.task.childtask;

import java.util.ArrayList;

import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.ProductInfo;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.service.spider.impl.ListingProductSpider;

public class QueryListingChildTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected String taskstr = "";
	public QueryListingChildTask() {
		taskid = "QueryListingChildTask" + System.currentTimeMillis();
	}
	public QueryListingChildTask(String taskstring) {
		taskid = "QueryListingChildTask" + System.currentTimeMillis();
		this.taskstr = taskstring;
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
		String keyword = taskstr.split("\\|")[0].trim();
		String brandstr = taskstr.split("\\|")[1].trim(); 
		String maxpage = ARE.getProperty("listingmaxpage");
		if(taskstr.split("\\|")[2] != null) {
			maxpage = taskstr.split("\\|")[2].trim();
		}
		int maxcount = Integer.parseInt(maxpage);
		ArrayList<String> brandlist = new ArrayList<String>();
		if(brandstr.contains(",")) {
			String[] temparray = brandstr.split(",");
			for(String tempstr : temparray) {
				brandlist.add(tempstr);
			}
		}else {
			brandlist.add(brandstr);
		}
		String prefix = "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=";
		String queryurl = prefix + keyword;
		DefaultDownloader downloader = new DefaultDownloader();
		Document doc = downloader.getPageDocument(queryurl);
		if(doc == null) {
			ARE.getLog().info("没有下载到对应的查询页面");
			return;
		}
		int pagenum = 1;
		ListingProductSpider productlistspider = new ListingProductSpider();
		while(queryurl != null && pagenum <= maxcount) {
			ArrayList<String> result = productlistspider.getAllProductUrl(queryurl);
			for(int i = 0;i < result.size();i++) {
				ProductInfo product = new ProductInfo();
				product.setProducturl(result.get(i));
				String brand = result.get(i).replace("https://www.amazon.com/", "").split("\\-")[0];
				product.setBrand(brand);
				product.setPagenum(pagenum + "");
				product.setPosition((i + 1) + "");
				
			}
			pagenum++;
		}
		
	}

}
