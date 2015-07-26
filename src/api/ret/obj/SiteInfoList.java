package api.ret.obj;

import java.util.ArrayList;

import entity.Site;

public class SiteInfoList extends RetObjBase {
	
	ArrayList<Site> siteInfoList = new ArrayList<Site>();

	public ArrayList<Site> getSiteInfoList() {
		return siteInfoList;
	}

	public void setSiteInfoList(ArrayList<Site> siteInfoList) {
		this.siteInfoList = siteInfoList;
	}
	
	public void addSiteInfo(Site site) {
		this.siteInfoList.add(site);
	}
}
