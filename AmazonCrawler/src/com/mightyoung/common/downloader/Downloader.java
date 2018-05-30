package com.mightyoung.common.downloader;
import org.jsoup.nodes.Document;

public interface Downloader {
	/**
	 * 获取url对应的网页源码?
	 * @param 要爬取的url地址信息
	 * @return 网页文档对象
	 * */
	public Document getPageDocument(String url);
}
