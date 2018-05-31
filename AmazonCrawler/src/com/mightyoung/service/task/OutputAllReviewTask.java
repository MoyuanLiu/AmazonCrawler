package com.mightyoung.service.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.amarsoft.are.ARE;
import com.mightyoung.common.task.Task;
import com.mightyoung.model.Review;
import com.mightyoung.util.ExcelIOUtil;

public class OutputAllReviewTask implements Task{
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected HashMap<String,ArrayList<Review>> reviewresult;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public OutputAllReviewTask() {
		taskid = "OutputAllReviewTask" + System.currentTimeMillis();
	}
	public OutputAllReviewTask(HashMap<String,ArrayList<Review>> productallreviewmap) {
		taskid = "OutputAllReviewTask" + System.currentTimeMillis();
		reviewresult = productallreviewmap;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		}catch(Exception e) {
			ARE.getLog().error("���������������ִ��ʧ�ܣ�",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		String storepath = "data/reviewresult.xlsx";
		//�½�������
		File finalXlsxFile = new File(storepath);
		
		Workbook workbook = null;
		try {
			workbook = ExcelIOUtil.getWorkbook(finalXlsxFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//�½�������
        Sheet sheet = workbook.createSheet("0"); 
		for(String resultinfo : reviewresult.keySet()) {
			ARE.getLog().info(""+resultinfo);
			if(resultinfo==null) {
				break;
			}
			for(Review reviewresult : reviewresult.get(resultinfo)) {
				Row row = sheet.createRow(0);
				String storeid = resultinfo.split("_")[0];
				String productasin = resultinfo.split("_")[1];
				String reviewtitle = reviewresult.getReviewtitle();
				String reviewstar = reviewresult.getReviewstar();
				String reviewdate = reviewresult.getReviewdate();
				String[] productpropertys = reviewresult.getProductproperty().split("\\|");
				String reviewcomment = reviewresult.getReviewcomment();
				row.createCell(0).setCellValue(storeid);//����id
				row.createCell(1).setCellValue(productasin);//��Ʒasin
				row.createCell(2).setCellValue(reviewtitle);//���۱���
				row.createCell(3).setCellValue(reviewstar);//�����Ǽ�
				int count = 0;
				for(int i = 4;i<4+productpropertys.length;i++) {
					row.createCell(i).setCellValue(productpropertys[i-4]);//���۲�Ʒ����
					count = i;
				}
				row.createCell(count+1).setCellValue(reviewdate);//��������
				row.createCell(count+2).setCellValue(reviewcomment);//��������
				//ARE.getLog().info();
			}
		}
		try {  
            File file = new File("data/reviewresult.xlsx");  
            FileOutputStream fileoutputStream = new FileOutputStream(file);  
            workbook.write(fileoutputStream);  
            fileoutputStream.close();  
            workbook.close();
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
		
	}
}
