package com.mightyoung.service.parser.impl;

import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amarsoft.are.ARE;
import com.mightyoung.common.parser.Parser;
import com.mightyoung.model.Review;
import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.util.DateFormatUtil;

public class ReviewParser implements Parser{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testurl = "https://www.amazon.com/MiYang-Winter-Womens-Indoor-Slipper/product-reviews/B01FM0KMFK/ref=cm_cr_dp_d_show_all_top?ie=UTF8&reviewerType=all_reviews";
		DefaultDownloader downloader = new DefaultDownloader();
		Document doc = downloader.getPageDocument(testurl);
		String html = StringEscapeUtils.unescapeHtml(doc.toString());
		ReviewParser reviewparser = new ReviewParser();
		ArrayList<Review> currentreviewlist = reviewparser.getAllReview(html, "div[id=cm_cr-review_list]>div[id]");
		for(Review r : currentreviewlist) {
			ARE.getLog().info(r.toString());
		}
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
	public ArrayList<Review> getAllReview(String pagehtml, String parsepath){
		if(pagehtml.equals(null) || pagehtml.isEmpty() || parsepath.isEmpty()||parsepath.equals(null)) {
			return null;
		}
		Document doc = Jsoup.parse(pagehtml);
		Elements els = doc.select(parsepath);
		ArrayList<Review> reviewlist = new ArrayList<Review>();
		for(Element e : els) {
			Review r = new Review();
			String reviewstar = e.select("div[class=a-row]").first().select("span[class=a-icon-alt]").text().replace("out of 5 stars", "").trim();
			r.setReviewstar(reviewstar);
			String reviewtitle = e.select("a[data-hook=review-title]").text();
			r.setReviewtitle(reviewtitle);
			String reviewdate = e.select("span[data-hook=review-date]").text().replace("on", "").trim();
			reviewdate = DateFormatUtil.changeEngtoStandardFormat(reviewdate);
			r.setReviewdate(reviewdate);
			String[] properties = e.select("a[data-hook=format-strip]").text().split("\\|");
			String productproperty = "";
			for(String property : properties) {
				ARE.getLog().info("product property:" + property);
				if(property.contains(":")) {
					productproperty += property.split(":")[1].trim() + "|";
				}
				
			}
			if(productproperty.endsWith("|")) {
				productproperty = productproperty.substring(0, productproperty.length()-1);
			}
			r.setProductproperty(productproperty);
			String reviewcomment = e.select("span[data-hook=review-body]").text();
			r.setReviewcomment(reviewcomment);
			reviewlist.add(r);
			ARE.getLog().info(r.toString());
		}
		return reviewlist;
	}
}
