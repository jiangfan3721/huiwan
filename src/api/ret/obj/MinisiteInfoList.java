package api.ret.obj;

import java.util.ArrayList;

public class MinisiteInfoList extends RetObjBase {
	
	ArrayList<MinisiteInfo> minisiteInfoList = new ArrayList<MinisiteInfo>();

	public ArrayList<MinisiteInfo> getMinisiteInfoList() {
		return minisiteInfoList;
	}

	public void setMinisiteInfoList(ArrayList<MinisiteInfo> minisiteInfoList) {
		this.minisiteInfoList = minisiteInfoList;
	}
	
	public void addMinisiteInfo(MinisiteInfo info) {
		this.minisiteInfoList.add(info);
	}
}
