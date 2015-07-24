package api.ret.obj;

import java.util.ArrayList;

public class MinisiteCommentList extends RetObjBase {

	ArrayList<MinisiteComment> minisiteCommentList = new ArrayList<MinisiteComment>();

	public ArrayList<MinisiteComment> getMinisiteCommentList() {
		return minisiteCommentList;
	}

	public void setMinisiteCommentList(ArrayList<MinisiteComment> minisiteCommentList) {
		this.minisiteCommentList = minisiteCommentList;
	}
	
	public void addMinisiteComment(MinisiteComment comment) {
		this.minisiteCommentList.add(comment);
	}
}
