package com.mightyoung.model;
/**
 * 种子url抽象模型
 * @author hz
 *
 */
public abstract class SeedUrlInfo {
	private String seedurl;

	public String getRooturl() {
		return seedurl;
	}

	public void setRooturl(String seedurl) {
		this.seedurl = seedurl;
	}
}
