package com.mightyoung.model;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;

public abstract class Crawler {
	protected ExecutorService excutorservice;//执行服务器
	protected String crawlername; //爬虫名称
	protected List<Task> tasklist;//爬虫任务列表
	protected String crawlerid;//爬虫编号
	/**
	 * 爬虫初始化
	 */
	public abstract void init();
	/**
	 * 爬虫启动
	 */
	public abstract void startup();
	/**
	 * 爬虫运行
	 */
	public abstract void run();
	/**
	 * 爬虫关闭
	 */
	public abstract void shutdown();
}
