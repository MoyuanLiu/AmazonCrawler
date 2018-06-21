package com.mightyoung.model;

import com.amarsoft.are.ARE;

public class ProductInfo {
	private String asin;
	private String title;
	private String reviewnum;
	private String reviewstar;
	private String producturl;
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReviewnum() {
		return reviewnum;
	}
	public void setReviewnum(String reviewnum) {
		this.reviewnum = reviewnum;
	}
	public String getReviewstar() {
		return reviewstar;
	}
	public void setReviewstar(String reviewstar) {
		this.reviewstar = reviewstar;
	}
	public String getProducturl() {
		return producturl;
	}
	public void setProducturl(String producturl) {
		this.producturl = producturl;
	}
	public String toString() {
//		ARE.getLog().info("当前商品信息为：");
//		ARE.getLog().info("商品asin:" + this.asin);
//		ARE.getLog().info("商品标题:" + this.title);
//		ARE.getLog().info("商品评论数:" + this.reviewnum);
//		ARE.getLog().info("商品评分:" + this.reviewstar);
//		ARE.getLog().info("商品url:" + this.producturl);
		return "当前商品信息为：\n" + "商品asin:" + this.asin + "\n" + "商品标题:" + this.title + "\n" + "商品评论数:" + this.reviewnum + "\n" + "商品评分:" + this.reviewstar + "\n" + "商品url:" + this.producturl;
		
	}
}
