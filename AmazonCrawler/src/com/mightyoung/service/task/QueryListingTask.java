package com.mightyoung.service.task;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.ProductInfo;
import com.mightyoung.service.task.childtask.QueryListingChildTask;

public class QueryListingTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected ArrayList<String> tasks = new ArrayList<String>();
	public ArrayList<ProductInfo> productresult = new ArrayList<ProductInfo>();
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
	public QueryListingTask() {
		taskid = "QueryListingTask" + System.currentTimeMillis();
	}
	public QueryListingTask(ArrayList<String> tasklist) {
		taskid = "QueryListingTask" + System.currentTimeMillis();
		this.tasks = tasklist;
	}
	@Override
	public void taskmain() {
		// TODO Auto-generated method stub
		if(tasks == null) {
			ARE.getLog().info("没有获取到任务");
			return;
		}
		for(String taskstr : tasks) {
			QueryListingChildTask childtask = new QueryListingChildTask(taskstr);
			childtask.run();
			for(ProductInfo p : childtask.productinfos) {
				productresult.add(p);
			}
			//每次查询要间隔一段时间
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
