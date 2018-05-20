package com.mightyoung.common.parser;

import com.mightyoung.model.ContentInfo;
import com.mightyoung.model.CrawlerUrlInfo;

public interface Parser {
/**
 * 获取对应页面路径和网页正文内容
 */
	public ContentInfo getPageContent(CrawlerUrlInfo crawlurlinfo);
	/**
	 * 获取对应页面html源码
	 */
		public String getPageHtml(CrawlerUrlInfo crawlurlinfo);
}
