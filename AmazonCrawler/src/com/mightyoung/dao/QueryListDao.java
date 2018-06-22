package com.mightyoung.dao;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.util.FileIOUtil;

public class QueryListDao {
	public ArrayList<String> getQueryList(String path){
		if(path.equals(null) || path.isEmpty()) {
			ARE.getLog().error("��ǰ��ѯ�����б�·��Ϊ��");
			return null;
		}
		ArrayList<String> querylist = new ArrayList<String>();
		querylist = FileIOUtil.readFilelines(path);
		return querylist;
	}
}
