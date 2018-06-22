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
			ARE.getLog().error("��ȡ�ؼ����б�ʧ�ܣ�",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}
	@Override
	public void taskmain() {
		// 1.��ȡ���������б�Ĵ洢λ��
		String path = ARE.getProperty("QueryListTaskPath");
		ARE.getLog().info("��������path:" + path);
		// 2.��ȡ���������б�
		QueryListDao querylistdao = new QueryListDao();
		tasklist = querylistdao.getQueryList(path);
	}

}
