package api.ret.obj;

public class CommentsNumber extends RetObjBase {
	
	private int commentsNumber = 0;

	public int getCommentsNumber() {
		return commentsNumber;
	}

	public void setCommentsNumber(int commentsNumber) {
		this.commentsNumber = commentsNumber;
	}

	public CommentsNumber(int commentsNumber) {
		super();
		this.commentsNumber = commentsNumber;
	}

	public CommentsNumber() {
		super();
	}
}
