package com.mightyoung.common.task;

import com.mightyoung.model.SeedUrlInfo;

public interface Task extends Runnable{
	/**
	 * 任务入口
	 */
	public void taskmain();
}
