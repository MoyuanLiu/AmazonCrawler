package com.mightyoung.dao;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.util.FileIOUtil;

public class KeyWordListDao {
	public String getKeywordList(String path){
		if(path.equals(null) || path.isEmpty()) {
			ARE.getLog().error("��ǰ�ؼ����б�·��Ϊ��");
			return null;
		}
		ArrayList<String> keywordlist = new ArrayList<String>();
		keywordlist = FileIOUtil.readFilelines(path);
		String keywordliststr = "";
		for(String s : keywordlist) {
			keywordliststr += s + "+";
		}
		if(keywordliststr.endsWith("+")) {
			return keywordliststr.substring(0,keywordliststr.length()-1);
		}else {
			return keywordliststr;
		}
	}
}
