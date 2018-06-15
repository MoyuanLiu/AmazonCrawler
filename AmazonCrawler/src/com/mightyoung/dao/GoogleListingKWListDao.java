package com.mightyoung.dao;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.util.FileIOUtil;

public class GoogleListingKWListDao {
	public String getQueryString(String path) {
		if(path.equals(null) || path.isEmpty()) {
			ARE.getLog().error("当前关键词列表路径为空");
			return null;
		}
		ArrayList<String> keywordlist = new ArrayList<String>();
		keywordlist = FileIOUtil.readFilelines(path);
		String prefix = "site:amazon.com ";
		String suffix = "currently unavailable";
		String queryStr = prefix;
		if(keywordlist.isEmpty()) {
			ARE.getLog().error("没有查询的关键词！！！");
			return queryStr += suffix;
		}
		for(String keyword : keywordlist) {
			queryStr += keyword + " ";
		}
		queryStr += suffix;
		return queryStr;
	}
}
