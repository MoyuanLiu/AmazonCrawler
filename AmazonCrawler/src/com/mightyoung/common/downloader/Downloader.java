package com.mightyoung.common.downloader;
import org.jsoup.nodes.Document;

public interface Downloader {
	/**
	 * 获取url对应的网页源�?
	 * @param url�?要爬取的url地址信息
	 * @return 网页源码
	 * */
	public Document getPageDocument(String url);
}
