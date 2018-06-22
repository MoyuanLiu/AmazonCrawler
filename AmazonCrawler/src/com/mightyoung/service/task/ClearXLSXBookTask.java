package com.mightyoung.service.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.util.ExcelIOUtil;

public class ClearXLSXBookTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected String storagefilepath = "";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public ClearXLSXBookTask() {
		taskid = "ClearXLSXBookTask" + System.currentTimeMillis();
	}
	public ClearXLSXBookTask(String filepath) {
		taskid = "ClearXLSXBookTask" + System.currentTimeMillis();
		this.storagefilepath = filepath;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("清空存储工作簿失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		ARE.getLog().info("清空存储数据！！");
		String storagepath = this.storagefilepath;
		//新建文件
		File finalXlsxFile = new File(storagepath);
		//获取工作簿
		Workbook workbook = null;
		try {
			workbook = ExcelIOUtil.getWorkbook(finalXlsxFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int sheetnum = workbook.getNumberOfSheets();
		ARE.getLog().info("当前工作簿有"+sheetnum+"张工作表");
		for(int i = 0;i < sheetnum-1;i++) {
			workbook.removeSheetAt(i);
		}
		try {  
	           FileOutputStream fileoutputStream = new FileOutputStream(finalXlsxFile);  
	           workbook.write(fileoutputStream);  
	           fileoutputStream.close();  
		       workbook.close();
			} catch (IOException e) {  
		        e.printStackTrace();  
	        } 
	}

}
