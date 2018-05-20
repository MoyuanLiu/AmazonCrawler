package com.mightyoung.common.spider;

import java.util.HashMap;



/**
 * 页面爬取
 * @author myliu
 *
 */
public interface Spider {
	/**
	 * 获取url对应的网页源码中分页超链(下级url)
	 * @param url要爬取的url地址信息
	 * @return 分页链接列表
	 * */
	public HashMap<String,String> getNextPage(String url);
}
