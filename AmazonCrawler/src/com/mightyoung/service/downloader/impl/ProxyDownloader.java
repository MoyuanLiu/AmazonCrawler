package com.mightyoung.service.downloader.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Document;

import com.mightyoung.common.downloader.Downloader;

public class ProxyDownloader implements Downloader{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProxyDownloader testdownloader = new ProxyDownloader();
		testdownloader.getPageDocument("https://www.google.com.hk/search?q=site:amazon.com+slippers+currently+unavailable");
	}

	@Override
	public Document getPageDocument(String url) {
		
		
		
		return null;
	}
	public void proxydownload(String url) {
		// TODO Auto-generated method stub
				CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpClient实例
				HttpGet httpGet = new HttpGet(url); // 创建httpget实例
				HttpHost proxy = new HttpHost("47.74.178.117",8080,"http");//创建代理ip实例，参数依次为代理ip地址，端口号，协议
				RequestConfig requestConfig = RequestConfig.custom()
						.setSocketTimeout(60000)
						.setConnectTimeout(30000)
						.setProxy(proxy).build();//设置请求，连接超长时间，以及代理ip
				httpGet.setConfig(requestConfig);
				httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");//设置用户代理，浏览器
				try {
					CloseableHttpResponse response = httpClient.execute(httpGet);// 执行http，get请求
					HttpEntity entity = response.getEntity(); // 获取返回实体
					System.out.println("网页内容：" + EntityUtils.toString(entity, "utf-8")); // 获取网页内容
					response.close(); // response关闭
					httpClient.close(); // httpClient关闭
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	}
	public void useproxydownload(String url) {
		CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
		HttpGet httpGet=new HttpGet(url); // 创建httpget实例
		String sProxyIp = "47.74.178.117";//代理ip服务地址
		int iProxyPort = 8123;//端口号（ip地址和端口号商家给的）
		HttpHost proxy = new HttpHost(sProxyIp,1080,"http");//创建代理ip实例
		RequestConfig requestConfig = RequestConfig.custom()
			 .setSocketTimeout(60000)
			 .setConnectTimeout(30000)
			 .setProxy(proxy).build();//设置请求，连接超长时间，以及代理ip
		httpGet.setConfig(requestConfig);
		httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");//设置用户代理，浏览器
				 
		//设置接口信息
		String secret = "b01dc7d0c4fe460f58e2e512ea568bdd";//账号
		String app_key = "abc123456";//密码(商家提供)
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(secret).append("app_key"+app_key).append("timestamp" + format.format(new Date())).append(secret);
		String sign = DigestUtils.md5Hex(stringBuilder.toString()).toUpperCase();
		httpGet.addHeader("Proxy-Authorization","chacha20 sign=" + sign +"&app_key="+app_key + "&timestamp=" + format.format(new Date()));
				 
		CloseableHttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity=response.getEntity(); // 获取返回实体
			System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8")); // 获取网页内容
			response.close(); // response关闭
			httpClient.close(); // httpClient关闭
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 执行http，get请求
		
		
		
	}

}
