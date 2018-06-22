package com.mightyoung.service.task;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.dao.QueryListDao;

public class GetQueryListTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	public ArrayList<String> tasklist = new ArrayList<String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public GetQueryListTask() {
		taskid = "GetQueryListTask" + System.currentTimeMillis();
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
		// 1.获取检索任务列表的存储位置
		String path = ARE.getProperty("QueryListTaskPath");
		ARE.getLog().info("检索任务path:" + path);
		// 2.获取检索任务列表
		QueryListDao querylistdao = new QueryListDao();
		tasklist = querylistdao.getQueryList(path);
	}

}
