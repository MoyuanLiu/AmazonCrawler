package com.mightyoung.service.task;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.dao.KeyWordListDao;

/**
 * 获取搜索关键词任务
 * @author hz
 *
 */
public class GetKeyWordsListTask implements Task{
	public String keywords;
	protected String taskstatus = "undo";
	protected String taskid = "";
	public static void main(String[] args) {
		ARE.init("etc/are.xml");
		GetKeyWordsListTask testtask = new GetKeyWordsListTask();
		testtask.run();
		ARE.getLog().info(testtask.keywords);
	}
	public GetKeyWordsListTask() {
		taskid = "GetKeyWordsListTask" + System.currentTimeMillis();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("获取关键词列表失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		// 1.获取关键词列表的存储位置
		String path = ARE.getProperty("KeyWordListPath");
		ARE.getLog().info("key word list path:" + path);
		// 2.获取关键词列表
		KeyWordListDao keyworddao = new KeyWordListDao();
		keywords = keyworddao.getKeywordList(path);
	}

}
