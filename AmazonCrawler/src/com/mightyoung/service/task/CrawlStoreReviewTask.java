package com.mightyoung.service.task;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.StoreUrlInfo;


public class CrawlStoreReviewTask implements Task{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskmain();
		}catch(Exception e) {
			ARE.getLog().error("������Ʒ��������ִ��ʧ�ܣ�",e);
		}
	}
	
	@Override
	public void taskmain() {
		// 1.��ȡһ��ҳ�����Ʒurl
		StoreUrlInfo storeurlinfo = new StoreUrlInfo();
		storeurlinfo.setRooturl("https://www.amazon.com/s?marketplaceID=ATVPDKIKX0DER&me=AR7H1RL9GCUCS&merchant=AR7H1RL9GCUCS");
		
		
	}
	public static void main(String[] args) {
		
	}
}
