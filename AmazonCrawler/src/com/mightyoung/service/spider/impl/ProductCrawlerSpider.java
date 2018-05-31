package com.mightyoung.service.spider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.spider.Spider;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.util.FileIOUtil;

public class ProductCrawlerSpider implements Spider{
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductCrawlerSpider spider = new ProductCrawlerSpider();
		ArrayList<String> producturls = spider.getAllProductUrl("https://www.amazon.com/s/ref=sr_pg_2/135-1086495-6251643?me=AR7H1RL9GCUCS&rh=i%3Amerchant-items&page=2&ie=UTF8&qid=1527666319");
		ARE.getLog().info("����url������"+producturls.size());
		for(String producturl :producturls) {
			ARE.getLog().info("��ȡ��������");
			ARE.getLog().info(producturl);
		}
		
//		String nexturl = spider.getSingalNextPage("https://www.amazon.com/s?marketplaceID=ATVPDKIKX0DER&me=AR7H1RL9GCUCS&merchant=AR7H1RL9GCUCS");
//		ARE.getLog().info(nexturl);
	}

	@Override
	public HashMap<String, String> getNextPages(String url) {
		return null;
	}

	@Override
	public String getSingalNextPage(String url) {
		DefaultDownloader downloader = new DefaultDownloader();
		Document document = downloader.getPageDocument(url);
		//ARE.getLog().info(document.html());
		FileIOUtil.WriteStringToFile("data/htmlcode.txt", document.html());
		if (document == null) {
			return null;
		}
		//��ȡҳ���е���һҳ������
		Element nextpagelink = document.select("a[id=pagnNextLink]").first();
		if(nextpagelink==null) {
			return null;
		}
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
		//FileIOUtil.WriteStringToFile("data/htmlcode.html", document.html());
		//��ȡҳ���е���Ʒ������
		Elements productlinkelements = document.select("ul.s-result-list > li");
//		String html = productlinkelements.html();
//		ARE.getLog().info(html);
//		Document doc = Jsoup.parse(html);
		
//		Elements productlinks = document.select("ul#s-results-list-atf > li");
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

}
