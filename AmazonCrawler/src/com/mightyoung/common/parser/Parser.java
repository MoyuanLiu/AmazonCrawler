package com.mightyoung.common.parser;

import java.util.ArrayList;

import org.jsoup.nodes.Document;

import com.mightyoung.model.ContentInfo;

public interface Parser {
/**
 * 获取对应页面路径和网页正文内容
 */
	public ArrayList<String> getPageContent(String pagehtml,String parsepath);
	/**
	 * 获取对应页面html源码
	 */
	public String getPageHtml(Document doc);
}
