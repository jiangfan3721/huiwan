package api.ret.obj;

import java.util.ArrayList;

import entity.CommentForSite;

public class SiteCommentList extends RetObjBase {

	ArrayList<CommentForSite> siteCommentList = new ArrayList<CommentForSite>();

	public ArrayList<CommentForSite> getSiteCommentList() {
		return siteCommentList;
	}

	public void setSiteCommentList(ArrayList<CommentForSite> siteCommentList) {
		this.siteCommentList = siteCommentList;
	}
	
	public void addSiteCommnet(CommentForSite comment) {
		this.siteCommentList.add(comment);
	}
}
