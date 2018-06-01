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
			ARE.getLog().error("输出爬虫评价任务执行失败！",e);
			taskstatus = "fail";
		}finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		ARE.getLog().info("输出结果！");
		String storepath = "data/reviewresult.xlsx";
		//新建文件
		File finalXlsxFile = new File(storepath);
		//获取工作簿
		Workbook workbook = null;
		try {
			workbook = ExcelIOUtil.getWorkbook(finalXlsxFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//获取工作表
        int rowindex=0;
        Sheet sheet = workbook.getSheet("爬取结果表"); 
		for(String resultinfo : reviewresult.keySet()) {
			ARE.getLog().info("resultinfo："+resultinfo);
			
			if(resultinfo==null) {
				break;
			}
			for(Review reviewresult : reviewresult.get(resultinfo)) {
				Row row = sheet.createRow(rowindex);
				String storeid = resultinfo.split("_")[0];
				String productasin = resultinfo.split("_")[1];
				String reviewtitle = reviewresult.getReviewtitle();
				String reviewstar = reviewresult.getReviewstar();
				String reviewdate = reviewresult.getReviewdate();
				String[] productpropertys= new String[1];
				if(reviewresult.getProductproperty().contains("|")) {
					productpropertys = reviewresult.getProductproperty().split("\\|\\|");
				}else {
					productpropertys[0]= reviewresult.getProductproperty();
				}
				
				String reviewcomment = reviewresult.getReviewcomment();
				
				ARE.getLog().info("店铺id:" + storeid);
				ARE.getLog().info("productasin:" + productasin);
				ARE.getLog().info("reviewtitle:" + reviewtitle);
				ARE.getLog().info("reviewstar:" + reviewstar);
				ARE.getLog().info("reviewdate:" + reviewdate);
				ARE.getLog().info("productpropertys:" + productpropertys.toString());
				ARE.getLog().info("reviewcomment:" + reviewcomment.toString());
				row.createCell(0).setCellValue(storeid);//店铺id
				row.createCell(1).setCellValue(productasin);//产品asin
				row.createCell(2).setCellValue(reviewtitle);//评论标题
				row.createCell(3).setCellValue(reviewstar);//评论星级
				int count = 0;
				for(int i = 4;i<4+productpropertys.length;i++) {
					row.createCell(i).setCellValue(productpropertys[i-4]);//评论产品属性
					count = i;
				}
				row.createCell(count+1).setCellValue(reviewdate);//评论日期
				row.createCell(count+2).setCellValue(reviewcomment);//评论内容
				rowindex++;
			}
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
