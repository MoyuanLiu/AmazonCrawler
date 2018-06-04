package com.mightyoung.service.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.util.ExcelIOUtil;

public class ClearReviewStorageTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClearReviewStorageTask testtask = new ClearReviewStorageTask();
		testtask.run();
	}
	public ClearReviewStorageTask() {
		taskid = "ClearStorageTask" + System.currentTimeMillis();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("清空存储数据任务执行失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		// TODO Auto-generated method stub
		ARE.getLog().info("清空存储数据！！");
		String storagepath = "data/reviewresult.xlsx";
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
		Sheet sheet = workbook.getSheet("爬取结果表"); 
		int rownum = sheet.getPhysicalNumberOfRows();
		for(int i = 0;i < rownum;i++) {
			Row currentrow = sheet.getRow(i);
			sheet.removeRow(currentrow);
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
