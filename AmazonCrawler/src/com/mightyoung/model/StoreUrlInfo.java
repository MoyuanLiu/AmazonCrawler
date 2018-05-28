package com.mightyoung.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺入口url模型
 * @author hz
 *
 */
public class StoreUrlInfo extends SeedUrlInfo{
	private String nexturl;
	private List<String> producturllist = new ArrayList<String>();
	public String getNexturl() {
		return nexturl;
	}

	public void setNexturl(String nexturl) {
		this.nexturl = nexturl;
	}

	public List<String> getProducturllist() {
		return producturllist;
	}

	public void setProducturllist(List<String> producturllist) {
		this.producturllist = producturllist;
	}
}
