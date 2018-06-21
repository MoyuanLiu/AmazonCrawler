package com.mightyoung.service.parser.impl;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.parser.Parser;
import com.mightyoung.model.ProductInfo;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.util.FileIOUtil;

public class GoogleListingParser implements Parser{

	public static void main(String[] args) {
		ARE.init("etc/are.xml");
		String testurl = "http://www.baidu.com/link?url=G-P3JMSkqnNip3kzXR6_DWo8wD7lUQcn3CS0IZ5--nNh4gRngc-Hlng0biWq015270PGi92hXkoICNtVjPpZDsAMUieY16s50RfE5A94d1Pie0h_gKLPY_0lrhLdIvr2Ep4Lu-vsbEH-SzKyZ0HdCBMBUwyEqgvo-T3tCb-CZERb7cttYOVNzudbZae975rHph7Ua-SNnDCdya71BdWgF41yHO_uxMa0Dmg-iB57MvoBtW6-2BTahT8_2vcfixpl8H1lbNeCiV0elxX8rjVebhO-btCxF1qrIXLJrgmS9J4EYtEiO1wDXref0voz5ftT";
		DefaultDownloader downloader = new DefaultDownloader();
		Document doc = downloader.getPageDocument(testurl);
		GoogleListingParser testparser = new GoogleListingParser();
		ProductInfo testproductinfo = testparser.parseProductInfo(doc);
		ARE.getLog().info(testproductinfo.toString());
	}

	@Override
	public ArrayList<String> getPageContent(String pagehtml, String parsepath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPageHtml(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}
	public ProductInfo parseProductInfo(Document doc) {
		if(doc == null || doc.equals(null)) {
			ARE.getLog().info("解析文档对象为空!!!");
			return null;
		}
		FileIOUtil.WriteStringToFile("data/code.html", doc.html());
		ProductInfo currentproductinfo = new ProductInfo();
		String title = doc.select("span[id=productTitle]").text().trim();
		String numregexstr = "\\d+";
		Pattern r = Pattern.compile(numregexstr);
		String reviewnumstr = doc.select("span[id=acrCustomerReviewText]").text().trim();
		Matcher m = r.matcher(reviewnumstr);
		String reviewnum = "0";
		if(m.find()) {
			reviewnum = m.group(0);
		}
		String reviewstar = "0";
		String reviewstarstr = doc.select("span[id=acrPopover]>span[class=a-declarative]>a>i>span[class=a-icon-alt]").text().trim();
		String starregexstr = "(\\d+[\\.\\d+]?)";
		Pattern r1 = Pattern.compile(starregexstr);
		Matcher m1 = r1.matcher(reviewstarstr);
		if(m1.find()) {
			reviewstar = m.group(0);
		}
		String asin = "";
		Elements els = doc.select("div[id=detailBullets_feature_div]>ul>li");
		if(els != null && !els.isEmpty()) {
			for(Element e : els) {
				if(e.text().contains("ASIN:")) {
					asin = e.text().replace("ASIN:", "").trim();
					ARE.getLog().info("第一种asin:" + asin);
				}
			}
		}else {
			ARE.getLog().info("没有找到第一种asin元素");
		}
		
		if(asin.isEmpty()) {
			ARE.getLog().info("此时asin:" + asin);
			if(doc.select("div[id=productDetails]>div.wrapper>div.col2>div").first() != null) {
				Elements elsbackup = doc.select("div[id=productDetails]>div.wrapper>div.col2>div").first().select("div[class=content]>table>tbody>tr");
				for(Element eb : elsbackup) {
					if(eb.text().contains("ASIN")) {
						asin = eb.text().replace("ASIN", "").trim();
					}	
				}
			}
			if(doc.select("div[id=prodDetails]>div.wrapper>div.col2>div").first() != null) {
				Element top = doc.select("div[id=prodDetails]>div.wrapper>div.col2>div").first();
				Elements elsbackup2 = top.select("div.pdClearfix>div.attrG>div.pdTab>table>tbody>tr");
				for(Element eb : elsbackup2) {
					if(eb.text().contains("ASIN")) {
						asin = eb.text().replace("ASIN", "").trim();
					}	
				}
			}
			if(doc.getElementById("productDetails_detailBullets_sections1") != null) {
				Elements asinregion = doc.getElementById("productDetails_detailBullets_sections1").select("tbody>tr");
				for(Element item : asinregion) {
					if(item.text().contains("ASIN")) {
						asin = item.text().replace("ASIN", "").trim();
					}
				}
			}
			if(doc.getElementById("detail-bullets") != null) {
				Elements asinregion = doc.getElementById("detail-bullets").select("table>tbody>tr>td.bucket>div.content>ul>li");
				for(Element item : asinregion) {
					if(item.text().contains("ASIN:")) {
						asin = item.text().replace("ASIN:", "").trim();
					}
				}
			}
		}
		currentproductinfo.setAsin(asin);
		currentproductinfo.setReviewnum(reviewnum);
		currentproductinfo.setReviewstar(reviewstar);
		currentproductinfo.setTitle(title);
		
		return currentproductinfo;
	}

}
