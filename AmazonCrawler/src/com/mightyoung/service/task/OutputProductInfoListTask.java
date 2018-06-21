package com.mightyoung.service.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.ProductInfo;
import com.mightyoung.util.ExcelIOUtil;

public class OutputProductInfoListTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected ArrayList<ProductInfo> productinfos = new ArrayList<ProductInfo>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public OutputProductInfoListTask() {
		taskid = "OutputProductInfoListTask" + System.currentTimeMillis();
	}
	public OutputProductInfoListTask(ArrayList<ProductInfo> productinfolist) {
		taskid = "OutputProductInfoListTask" + System.currentTimeMillis();
		productinfos = productinfolist;
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
		// TODO Auto-generated method stub
		ARE.getLog().info("��������");
		String storepath = "data/googlelistingresult.xlsx";
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
        ARE.getLog().info("�ܹ���"+ productinfos.size() + "����¼");
        for(ProductInfo productinfo : productinfos) {
        	Row row = sheet.createRow(rowindex);
        	String title = productinfo.getTitle();
        	String asin = productinfo.getAsin();
        	String num = productinfo.getReviewnum();
        	String star = productinfo.getReviewstar();
        	String producturl = productinfo.getProducturl();
        	
        	ARE.getLog().info("��Ʒasin:" + asin);
        	ARE.getLog().info("��Ʒtitle:" + title);
        	ARE.getLog().info("��Ʒ����:" + star);
        	ARE.getLog().info("��Ʒ������:" + num);
        	ARE.getLog().info("��Ʒurl:" + producturl);
        	row.createCell(0).setCellValue(asin);
        	row.createCell(1).setCellValue(title);
        	row.createCell(2).setCellValue(star);
        	row.createCell(3).setCellValue(num);
        	row.createCell(4).setCellValue(producturl);
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
        	productinfos.clear();
        }
	}
	
}
