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
//		ARE.getLog().info("��ǰ��Ʒ��ϢΪ��");
//		ARE.getLog().info("��Ʒasin:" + this.asin);
//		ARE.getLog().info("��Ʒ����:" + this.title);
//		ARE.getLog().info("��Ʒ������:" + this.reviewnum);
//		ARE.getLog().info("��Ʒ����:" + this.reviewstar);
//		ARE.getLog().info("��Ʒurl:" + this.producturl);
		return "��ǰ��Ʒ��ϢΪ��\n" + "��Ʒasin:" + this.asin + "\n" + "��Ʒ����:" + this.title + "\n" + "��Ʒ������:" + this.reviewnum + "\n" + "��Ʒ����:" + this.reviewstar + "\n" + "��Ʒurl:" + this.producturl;
		
	}
}
