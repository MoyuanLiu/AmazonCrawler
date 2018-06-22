package com.mightyoung.service.spider.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;

public class ListingProductSpider implements Spider{

	public static void main(String[] args) {
		String testurl = "https://www.amazon.com/s/?url=search-alias%3Daps&field-keywords=reborn+baby+dolls";
		ListingProductSpider testspider = new ListingProductSpider();
//		String testresult = testspider.getSingalNextPage(testurl);
//		ARE.getLog().info(testresult);
		ArrayList<String> producturls = testspider.getAllProductUrl(testurl);
		ARE.getLog().info("����url������"+producturls.size());
		for(String producturl : producturls) {
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
		if(nextpagelink==null) {
			return null;
		}
		String nextpageurl = nextpagelink.attr("abs:href");
		return nextpageurl;
	}
	/*
	 * ��ȡ���е���Ʒ����
	 */
	public ArrayList<String> getAllProductUrl(String url){
		ArrayList<String> producturls = new ArrayList<String>();
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		Elements productlinkelements = document.select("ul.s-result-list > li");
		Elements productlinks = productlinkelements.select("a.s-access-detail-page");
		ARE.getLog().info("��ȡҳ���е���Ʒ������");
		if(productlinks==null) {
			ARE.getLog().info("û�л�ȡ��������Ԫ��");
			return null;
		}
		ARE.getLog().info("����Ԫ�ظ�����" + productlinks.size());
		for(int i = 0;i < productlinks.size();i++){
			String productlink = productlinks.get(i).attr("abs:href");
			producturls.add(productlink);
		}
		return producturls;
	}
	/*
	 * ��ȡ���е���Ʒ����
	 */
	public HashMap<String,String> getAllProductUrlADFlag(String url){
		HashMap<String,String> producturls = new HashMap<String,String>();
		
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		if (document == null) {
			return null;
		}
		Elements productlinkelements = document.select("ul.s-result-list > li");
		Elements productlinks = productlinkelements.select("a.s-access-detail-page");
		for(Element e : productlinkelements) {
			Element productlink = e.select("a.s-access-detail-page").first();
		}
		ARE.getLog().info("��ȡҳ���е���Ʒ������");
		if(productlinks == null) {
			ARE.getLog().info("û�л�ȡ��������Ԫ��");
			return null;
		}
		ARE.getLog().info("����Ԫ�ظ�����" + productlinks.size());
		for(int i = 0;i < productlinks.size();i++){
			String productlink = productlinks.get(i).attr("abs:href");
//			producturls.add(productlink);
		}
		return producturls;
	}
}
