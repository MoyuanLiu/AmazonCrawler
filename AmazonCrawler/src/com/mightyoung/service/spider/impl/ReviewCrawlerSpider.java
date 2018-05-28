package com.mightyoung.service.spider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;

public class ReviewCrawlerSpider implements Spider{
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReviewCrawlerSpider spider = new ReviewCrawlerSpider();
//		String nexturl = spider.getSingalNextPage("https://www.amazon.com/s?marketplaceID=ATVPDKIKX0DER&me=AR7H1RL9GCUCS&merchant=AR7H1RL9GCUCS");
//		ARE.getLog().info(nexturl);
		ArrayList<String> producturls = spider.getAllProductUrl("https://www.amazon.com/s?marketplaceID=ATVPDKIKX0DER&me=AR7H1RL9GCUCS&merchant=AR7H1RL9GCUCS");
		for(String producturl :producturls) {
			ARE.getLog().info("��ȡ��������");
			ARE.getLog().info(producturl);
		}
	}

	@Override
	public HashMap<String, String> getNextPages(String url) {
		return null;
	}

	@Override
	public String getSingalNextPage(String url) {
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		//��ȡҳ���е���һҳ������
		Element nextpagelink = document.getElementById("pagnNextLink");
		String nextpageurl = nextpagelink.attr("abs:href");
		return nextpageurl;
	}
	/**
	 * �ӵ���ҳ���ȡȫ������Ʒ����
	 * @param url
	 * @return
	 */
	public ArrayList<String> getAllProductUrl(String url){
		ArrayList<String> producturls = new ArrayList<String>();
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		
		//��ȡҳ���е���Ʒ������
		Elements productlinkelements = document.select("ul[id=s-results-list-atf]>li>div.a-spacing-mini>a");
		ARE.getLog().info("��ȡҳ���е���Ʒ������");
		for(int i = 0;i < productlinkelements.size();i++){
			ARE.getLog().info("ѭ��Ԫ���б�");
			String productlink = productlinkelements.get(i).attr("href");
			ARE.getLog().info(productlink);
			producturls.add(productlink);
		}
		return producturls;
	}

}
