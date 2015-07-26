package entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CommentForMinisite {
	
	private long commentsForMinisiteId;
	private long accountUserId;
	private String nickname;
	private String userIcon;
	private long minisiteId;
	private String content;
	private ArrayList<String> picPath;
	private Timestamp time;
	
	public CommentForMinisite(){
		this.commentsForMinisiteId = -1;
		this.accountUserId = -1;
		this.nickname = "";
		this.userIcon = "";
		this.minisiteId = -1;
		this.content = "";
		this.time = new Timestamp(0);
		this.picPath = new ArrayList<String>();
	}

	public long getCommentsForMinisiteId() {
		return commentsForMinisiteId;
	}

	public void setCommentsForMinisiteId(long commentsForMinisiteId) {
		this.commentsForMinisiteId = commentsForMinisiteId;
	}

	public long getAccountUserId() {
		return accountUserId;
	}

	public void setAccountUserId(long accountUserId) {
		this.accountUserId = accountUserId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public long getMinisiteId() {
		return minisiteId;
	}

	public void setMinisiteId(long minisiteId) {
		this.minisiteId = minisiteId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<String> getPicPath() {
		return picPath;
	}

	public void setPicPath(ArrayList<String> picPath) {
		this.picPath = picPath;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
