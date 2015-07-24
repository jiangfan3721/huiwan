package api.ret.obj;

import java.util.ArrayList;

public class SiteCommentList extends RetObjBase {

	ArrayList<SiteComment> siteCommentList = new ArrayList<SiteComment>();

	public ArrayList<SiteComment> getSiteCommentList() {
		return siteCommentList;
	}

	public void setSiteCommentList(ArrayList<SiteComment> siteCommentList) {
		this.siteCommentList = siteCommentList;
	}
	
	public void addSiteCommnet(SiteComment comment) {
		this.siteCommentList.add(comment);
	}
}
