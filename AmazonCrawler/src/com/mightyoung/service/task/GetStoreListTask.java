package com.mightyoung.service.task;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.dao.StoreListDao;
import com.mightyoung.model.Store;

public class GetStoreListTask implements Task{
	protected ArrayList<Store> storelist;
	protected String taskstatus = "undo";
	protected String taskid;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public GetStoreListTask() {
		taskid = "GetStoreList" + System.currentTimeMillis();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
				try {
					taskstatus = "todo";
					taskmain();
					taskstatus = "success";
				}catch(Exception e) {
					ARE.getLog().error("获取店铺名单失败！",e);
					taskstatus = "fail";
				}finally {
					ARE.getLog().info("current taskid:" + taskid);
					ARE.getLog().info("status:" + taskstatus);
				}
	}

	@Override
	public void taskmain() {
		// 1.获取店铺名单路径
		String path = ARE.getProperty("StoreListPath");
		//2.获取全部店铺列表
		StoreListDao storelistdao = new StoreListDao();
		storelist = storelistdao.getStoreList(path);
	}

}
