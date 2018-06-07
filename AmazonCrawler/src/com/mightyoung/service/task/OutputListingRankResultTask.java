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
import com.mightyoung.model.Rank;
import com.mightyoung.util.ExcelIOUtil;

public class OutputListingRankResultTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected HashMap<String,ArrayList<String>> titlerankmaplist = new HashMap<String,ArrayList<String>>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public OutputListingRankResultTask() {
		taskid = "OutputListingRankResultTask" + System.currentTimeMillis();
	}
	public OutputListingRankResultTask(HashMap<String,ArrayList<String>> titlerankmap) {
		taskid = "OutputListingRankResultTask" + System.currentTimeMillis();
		this.titlerankmaplist = titlerankmap;
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
		// TODO Auto-generated method stub
		ARE.getLog().info("��������");
		String storepath = "data/listingresult.xlsx";
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
        ARE.getLog().info("�ܹ���"+ titlerankmaplist.keySet().size() + "����¼");
        for(String title : titlerankmaplist.keySet()) {
        	Row row = sheet.createRow(rowindex);
        	ArrayList<String> ranks = titlerankmaplist.get(title);
        	ARE.getLog().info("��Ʒtitle:" + title);
        	
        	row.createCell(0).setCellValue(title);
        	for(int i = 0;i < ranks.size();i++) {
        		row.createCell(i+1).setCellValue(ranks.get(i));
        		ARE.getLog().info("��Ʒrank:" + ranks.get(i));
        	}
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
        	titlerankmaplist.clear();
        }
	}

}
