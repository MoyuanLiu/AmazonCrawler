package com.mightyoung.model;

import com.amarsoft.are.ARE;

public class ProductInfo {
	private String asin;
	private String title;
	private String reviewnum;
	private String reviewstar;
	private String producturl;
	private String brand;
	private String pagenum;
	private String position;
	private String keywordstr;
	private boolean ad;
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
//		ARE.getLog().info("��ǰ��Ʒ��ϢΪ��");
//		ARE.getLog().info("��Ʒasin:" + this.asin);
//		ARE.getLog().info("��Ʒ����:" + this.title);
//		ARE.getLog().info("��Ʒ������:" + this.reviewnum);
//		ARE.getLog().info("��Ʒ����:" + this.reviewstar);
//		ARE.getLog().info("��Ʒurl:" + this.producturl);
		return "��ǰ��Ʒ��ϢΪ��\n" + "��Ʒasin:" + this.asin + "\n" + "��Ʒ����:" + this.title + "\n" + "��Ʒ������:" + this.reviewnum + "\n" + "��Ʒ����:" + this.reviewstar + "\n" + "��Ʒurl:" + this.producturl;
		
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPagenum() {
		return pagenum;
	}
	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public boolean isAd() {
		return ad;
	}
	public void setAd(boolean ad) {
		this.ad = ad;
	}
	public String getKeywordstr() {
		return keywordstr;
	}
	public void setKeywordstr(String keywordstr) {
		this.keywordstr = keywordstr;
	}
}
