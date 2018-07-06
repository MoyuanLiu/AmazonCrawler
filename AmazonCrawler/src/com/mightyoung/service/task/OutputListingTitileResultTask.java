package com.mightyoung.service.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.util.ExcelIOUtil;

public class OutputListingTitileResultTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected HashMap<String,String> titleurlmap = new HashMap<String,String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public OutputListingTitileResultTask() {
		taskid = "OutputListingTitileResultTask" + System.currentTimeMillis();
	}
	public OutputListingTitileResultTask(HashMap<String,String> titleurlmaplist) {
		taskid = "OutputListingTitileResultTask" + System.currentTimeMillis();
		this.titleurlmap = titleurlmaplist;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("�����ȡ�������ʧ�ܣ�",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		// TODO Auto-generated method stub
		ARE.getLog().info("��������");
		String storepath = ARE.getProperty("ListingTitleStoragePath");
		//�½��ļ�
		File finalXlsxFile = new File(storepath);
		//��ȡ������
		Workbook workbook = null;
		try {
			workbook = ExcelIOUtil.getWorkbook(finalXlsxFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//��ȡ������
        int rowindex = 0;
        Sheet sheet = workbook.getSheet("��ȡ�����");
        ARE.getLog().info("�ܹ���"+ titleurlmap.keySet().size() + "����¼");
        for(String producturl : titleurlmap.keySet()) {
        	Row row = sheet.createRow(rowindex);
        	String title = titleurlmap.get(producturl);
        	ARE.getLog().info("��Ʒtitle:" + title);
        	
        	row.createCell(0).setCellValue(title);
        	row.createCell(1).setCellValue(producturl);
        	rowindex++;
        }
        try {  
            FileOutputStream fileoutputStream = new FileOutputStream(finalXlsxFile);  
            workbook.write(fileoutputStream);  
            fileoutputStream.close();  
            workbook.close();
        } catch (IOException e) {
        	ARE.getLog().error("������ʧ�ܣ���");
        	ARE.getLog().error(e);
        } finally {
        	titleurlmap.clear();
        }
	}

}