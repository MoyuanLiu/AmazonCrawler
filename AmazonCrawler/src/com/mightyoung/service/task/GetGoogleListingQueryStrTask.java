package com.mightyoung.service.task;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.dao.GoogleListingKWListDao;

public class GetGoogleListingQueryStrTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	public String queryurlofbaidu = "";
	public String queryurlofgoogle = "";
	public static void main(String[] args) {
		ARE.init("etc/are.xml");
		GetGoogleListingQueryStrTask testtask = new GetGoogleListingQueryStrTask();
		testtask.run();
		
	}
	public GetGoogleListingQueryStrTask() {
		taskid = "GetGoogleListingQueryStrTask" + System.currentTimeMillis();
	}
	@Override
	public void run() {
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
		// 1.��ȡ�ؼ����б�·��
		String path = ARE.getProperty("GoogleListingKeyWordPath");
		ARE.getLog().info("store list path:" + path);
		//2.��ȡȫ���ؼ����б�
		GoogleListingKWListDao googlelistingkwlistdao = new GoogleListingKWListDao();
		String querystr = "";
		querystr = googlelistingkwlistdao.getQueryString(path);
		queryurlofbaidu = "https://www.baidu.com/s?ie=utf-8&wd=" + querystr;
		queryurlofgoogle = "https://www.google.com.hk/search?q=" + querystr.replace(" ", "+");
		ARE.getLog().info("���β�ѯ�Ĳ�ѯ���Ϊ:" + querystr);
		ARE.getLog().info("���β�ѯ�İٶȲ�ѯ����Ϊ:" + queryurlofbaidu);
		ARE.getLog().info("���β�ѯ�Ĺȸ��ѯ����Ϊ:" + queryurlofgoogle);
	}

}
