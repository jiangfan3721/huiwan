package api.ret.obj;

import java.util.ArrayList;

public class SiteInfoList extends RetObjBase {
	
	ArrayList<SiteInfo> siteInfoList = new ArrayList<SiteInfo>();

	public ArrayList<SiteInfo> getSiteInfoList() {
		return siteInfoList;
	}

	public void setSiteInfoList(ArrayList<SiteInfo> siteInfoList) {
		this.siteInfoList = siteInfoList;
	}
	
	public void addSiteInfo(SiteInfo info) {
		this.siteInfoList.add(info);
	}
}
