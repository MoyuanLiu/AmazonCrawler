package com.mightyoung.model;
/**
 * 商品实体模型
 * @author hz
 *
 */
public class Product {
	private String productid;
	private String asin;
	private String producturl;
	private String productstoreid;
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
	public String getProducturl() {
		return producturl;
	}
	public void setProducturl(String producturl) {
		this.producturl = producturl;
	}
	public String getProductstoreid() {
		return productstoreid;
	}
	public void setProductstoreid(String productstoreid) {
		this.productstoreid = productstoreid;
	}
}
