package api.ret.obj;

import java.util.ArrayList;

public class SiteIdList extends RetObjBase {
	
	ArrayList<Long> siteIdList;

	public ArrayList<Long> getSiteIdList() {
		return siteIdList;
	}

	public void setSiteIdList(ArrayList<Long> siteIdList) {
		this.siteIdList = siteIdList;
	}
}
