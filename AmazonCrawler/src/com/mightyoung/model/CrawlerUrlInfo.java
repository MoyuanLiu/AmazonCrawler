package com.mightyoung.model;

public class CrawlerUrlInfo {
	/**
	 * ≈¿»°urlƒ£–Õ
	 */
	private String crawlerid;
	private String crawlerurl;
	private String[] keywords;
	private int pagenum;
	public String getCrawlerUrl() {
		return crawlerurl;
	}
	public void setCrawlerUrl(String crawlerurl) {
		this.crawlerurl = crawlerurl;
	}
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public String getCrawlerid() {
		return crawlerid;
	}
	public void setCrawlerid(String crawlerid) {
		this.crawlerid = crawlerid;
	}
	
	
}
