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

public class OutputListingPositionTask implements Task {
	protected String taskstatus = "undo";
	protected String taskid = "";
	protected ArrayList<ProductInfo> products = new ArrayList<ProductInfo>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public OutputListingPositionTask() {
		taskid = "OutputListingPositionTask" + System.currentTimeMillis();
	}
	public OutputListingPositionTask(ArrayList<ProductInfo> productresult) {
		taskid = "OutputListingPositionTask" + System.currentTimeMillis();
		this.products = productresult;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskstatus = "todo";
			taskmain();
			taskstatus = "success";
		} catch (Exception e) {
			ARE.getLog().error("获取关键词列表失败！", e);
			taskstatus = "fail";
		} finally {
			ARE.getLog().info("current taskid:" + taskid);
			ARE.getLog().info("status:" + taskstatus);
		}
	}

	@Override
	public void taskmain() {
		// TODO Auto-generated method stub
		ARE.getLog().info("输出结果！");
		String storepath = "data/googlelistingresult.xlsx";
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
        int rowindex = 0;
        Sheet sheet = workbook.getSheet("爬取结果表");
        ARE.getLog().info("总共有"+ products.size() + "条记录");
        for(ProductInfo productinfo : products) {
        	Row row = sheet.createRow(rowindex);
        	String keyword = productinfo.getKeywordstr();
        	String brand = productinfo.getBrand();
        	String asin = productinfo.getAsin();
        	String pagenum = productinfo.getPagenum();
        	String position = productinfo.getPosition();
        	String adflag = "common";
        	if(productinfo.isAd()) {
        		adflag = "Sponsored";
        	}
        	String producturl = productinfo.getProducturl();
        	
        	ARE.getLog().info("商品检索关键词:" + keyword);
        	ARE.getLog().info("商品品牌:" + brand);
        	ARE.getLog().info("商品asin:" + asin);
        	ARE.getLog().info("商品所在页数:" + pagenum);
        	ARE.getLog().info("商品所在位置:" + position);
        	ARE.getLog().info("商品广告位:" + adflag);
        	ARE.getLog().info("商品url:" + producturl);
        	row.createCell(0).setCellValue(keyword);
        	row.createCell(1).setCellValue(brand);
        	row.createCell(2).setCellValue(asin);
        	row.createCell(3).setCellValue(pagenum);
        	row.createCell(4).setCellValue(position);
        	row.createCell(5).setCellValue(adflag);
        	row.createCell(6).setCellValue(producturl);
        	rowindex++;
        }
        try {  
            FileOutputStream fileoutputStream = new FileOutputStream(finalXlsxFile);  
            workbook.write(fileoutputStream);  
            fileoutputStream.close();  
            workbook.close();
        } catch (IOException e) {
        	ARE.getLog().error("保存结果失败！！");
        	ARE.getLog().error(e);
        } finally {
        	products.clear();
        }
	}

}
