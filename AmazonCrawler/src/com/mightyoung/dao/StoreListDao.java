package com.mightyoung.dao;

import java.util.ArrayList;

import com.amarsoft.are.ARE;
import com.mightyoung.model.Store;
import com.mightyoung.util.FileIOUtil;

public class StoreListDao {
	public ArrayList<Store> getStoreList(String path) {
		if(path.equals(null) || path.isEmpty()) {
			ARE.getLog().error("当前店铺列表路径为空");
			return null;
		}
		ArrayList<String> storeinfolist = new ArrayList<String>();
		storeinfolist = FileIOUtil.readFilelines(path);
		ArrayList<Store> storelist = new ArrayList<Store>();
		for(String storeinfo : storeinfolist) {
			String[] info = storeinfo.split("\\|");
			Store newstore = new Store();
			newstore.setStoreid(info[0]);
			newstore.setStorename(info[1]);
			newstore.setStoreurl(info[2]);
			storelist.add(newstore);
		}
		return storelist;
	}
}
