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

public class GoogleListingParser implements Parser{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		ProductInfo currentproductinfo = new ProductInfo();
		String title = doc.select("span[id=productTitle]").text().trim();
		String numregexstr = "\\d+";
		Pattern r = Pattern.compile(numregexstr);
		String reviewnumstr = doc.select("span[id=acrCustomerReviewText]").text().trim();
		Matcher m = r.matcher(reviewnumstr);
		m.find();
		String reviewnum = m.group(0);
		String reviewstar = doc.select("span[id=acrPopover]>span[class=a-declarative]>a>i>span[class=a-icon-alt]").text().trim();
		String asin = "";
		Elements els = doc.select("div[id=detailBullets_feature_div]>ul>li");
		for(Element e : els) {
			if(e.text().contains("ASIN:")) {
				asin = e.text().replace("ASIN:", "").trim();
			}
		}
		currentproductinfo.setAsin(asin);
		currentproductinfo.setReviewnum(reviewnum);
		currentproductinfo.setReviewstar(reviewstar);
		currentproductinfo.setTitle(title);
		return currentproductinfo;
	}

}
