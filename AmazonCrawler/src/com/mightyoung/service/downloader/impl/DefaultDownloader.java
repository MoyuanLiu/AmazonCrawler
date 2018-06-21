package com.mightyoung.service.downloader.impl;

import java.net.SocketTimeoutException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.amarsoft.are.ARE;
import com.mightyoung.common.downloader.Downloader;

/**
 * 默认页面下载器
 * @author myliu
 *
 */
public class DefaultDownloader implements Downloader{
	private int visitCount = 0;
	private static final int MAX_COUNT = 3;
	
	/**
	 * 默认获取页面的文档对象
	 */
	@Override
	public Document getPageDocument(String url) {
				Document document = null;
				try {
					document = Jsoup
					.connect(url)
					//Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/47.0.2526.73 Chrome/47.0.2526.73 Safari/537.36
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.62 Safari/537.36")
					.timeout(90000).get();
				}catch(SocketTimeoutException e){//网页读取超时异常
					visitCount++;
					if(visitCount <= MAX_COUNT){
						ARE.getLog().error("url["+url+"]网页读取超时,重试"+visitCount+"次:"+e.toString(),e);
						return getPageDocument(url);
					} else {
						ARE.getLog().error("url["+url+"]重试依旧出错:" + e.toString(), e);
					}
				}catch(HttpStatusException e1){
					ARE.getLog().error("url["+url+"]网页获取错误，状态码:" + e1.getStatusCode());
				}catch(Exception e2){
					ARE.getLog().error("url["+url+"]访问失败!" + e2.getMessage());
				}
				return document;
	}

}
