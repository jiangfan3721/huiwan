package api.ret.obj;

import java.util.ArrayList;

import entity.CommentForMinisite;

public class MinisiteCommentList extends RetObjBase {

	ArrayList<CommentForMinisite> minisiteCommentList = new ArrayList<CommentForMinisite>();

	public ArrayList<CommentForMinisite> getMinisiteCommentList() {
		return minisiteCommentList;
	}

	public void setMinisiteCommentList(ArrayList<CommentForMinisite> minisiteCommentList) {
		this.minisiteCommentList = minisiteCommentList;
	}
	
	public void addMinisiteComment(CommentForMinisite comment) {
		this.minisiteCommentList.add(comment);
	}
}
