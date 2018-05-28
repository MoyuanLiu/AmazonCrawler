package com.mightyoung.model;
/**
 * 评论实体模型
 * @author hz
 *
 */
public class Review {
	private String reviewid;
	private String reviewstar;
	private String reviewdate;
	private String productproperty;
	private String reviewcomment;
	private String reviewproductid;
	public String getReviewid() {
		return reviewid;
	}
	public void setReviewid(String reviewid) {
		this.reviewid = reviewid;
	}
	public String getReviewstar() {
		return reviewstar;
	}
	public void setReviewstar(String reviewstar) {
		this.reviewstar = reviewstar;
	}
	public String getReviewdate() {
		return reviewdate;
	}
	public void setReviewdate(String reviewdate) {
		this.reviewdate = reviewdate;
	}
	public String getProductproperty() {
		return productproperty;
	}
	public void setProductproperty(String productproperty) {
		this.productproperty = productproperty;
	}
	public String getReviewcomment() {
		return reviewcomment;
	}
	public void setReviewcomment(String reviewcomment) {
		this.reviewcomment = reviewcomment;
	}
	public String getReviewproductid() {
		return reviewproductid;
	}
	public void setReviewproductid(String reviewproductid) {
		this.reviewproductid = reviewproductid;
	}
	
	
}
