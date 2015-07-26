package api.ret.obj;

import java.util.ArrayList;

import entity.Minisite;

public class MinisiteInfoList extends RetObjBase {
	
	ArrayList<Minisite> minisiteInfoList = new ArrayList<Minisite>();

	public ArrayList<Minisite> getMinisiteInfoList() {
		return minisiteInfoList;
	}

	public void setMinisiteInfoList(ArrayList<Minisite> minisiteInfoList) {
		this.minisiteInfoList = minisiteInfoList;
	}
	
	public void addMinisiteInfo(Minisite minisite) {
		this.minisiteInfoList.add(minisite);
	}
}
