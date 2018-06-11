package com.mightyoung.test;

import org.jsoup.nodes.Document;

import com.mightyoung.service.downloader.impl.DefaultDownloader;
import com.mightyoung.util.FileIOUtil;

public class DownloadUTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testurl = "https://www.google.com.hk/search?q=site:amazon.com+slippers+currently+unavailable";
		DefaultDownloader downloader = new DefaultDownloader();
		Document doc = downloader.getPageDocument(testurl);
		FileIOUtil.WriteStringToFile("/data/code.html", doc.html());
	}

}
