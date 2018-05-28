package com.mightyoung.model;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;

public abstract class Crawler {
	protected ExecutorService excutorservice;//ִ�з�����
	protected String crawlername; //��������
	protected List<Task> tasklist;//���������б�
	protected String crawlerid;//������
	/**
	 * �����ʼ��
	 */
	public abstract void init();
	/**
	 * ��������
	 */
	public abstract void startup();
	/**
	 * ��������
	 */
	public abstract void run();
	/**
	 * ����ر�
	 */
	public abstract void shutdown();
}
