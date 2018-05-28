package com.mightyoung.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.amarsoft.are.ARE;

public class FileIOUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 * 在文本后追加字符串
	 * */
	public static void WriteStringToFile(String filePath,String contentstr) {
		FileWriter fw = null;
		try {
		//如果文件存在，则追加内容；如果文件不存在，则创建文件
		File f=new File(filePath);
		fw = new FileWriter(f, true);
		} catch (IOException e) {
		e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(contentstr);
		pw.flush();
		try {
		fw.flush();
		pw.close();
		fw.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
    }
	/*
	 * 读取文本全部内容
	 * */
	public static String readFileContent(String filepath){
		File testData = new File(filepath);
		FileInputStream fi = null;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		String fileContent = "";
		try {
			fi = new FileInputStream(testData);

			byte[] rawBuf = new byte[1024];
			int len = 0;
			while (true) {
				try {
					len = fi.read(rawBuf, 0, rawBuf.length);
					if (len > 0) {
						bo.write(rawBuf, 0, len);
						bo.flush();
					} else if (len == 0) {
						continue;
					} else {
						break;
					}
				} catch (IOException e) {
					break;
				}
				len = 0;
			}
			fileContent = new String(bo.toByteArray(), "utf-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			} finally {
				if (null != fi) {
					try {
						fi.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					bo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return fileContent;
	}
	/*
	 * 按行读取文本
	 * */
	public static ArrayList<String> readFilelines(String filepath) {
		if(filepath==null || filepath.equals(null)) {
			ARE.getLog().error("读取文件路径为空！！");
			return null;
		}
		ArrayList<String> lines = new ArrayList<String>();
		try {
			
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath)),
                                                                         "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
            	lines.add(lineTxt);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("文件读取异常:" + e);
        }
		return lines;
	}
}
