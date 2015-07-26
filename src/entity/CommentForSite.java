package entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CommentForSite {
	
	private long commentsForSiteId;
	private long accountUserId;
	private String nickname;
	private String userIcon;
	private long siteId;
	private String content;
	private int score;
	private ArrayList<String> picPath;
	private Timestamp time;
	
	public CommentForSite(){
		this.commentsForSiteId = -1;
		this.accountUserId = -1;
		this.nickname = "";
		this.userIcon = "";
		this.siteId = -1;
		this.content = "";
		this.score = -1;
		this.time = new Timestamp(0);
		this.picPath = new ArrayList<String>();
	}

	public long getCommentsForSiteId() {
		return commentsForSiteId;
	}

	public void setCommentsForSiteId(long commentsForSiteId) {
		this.commentsForSiteId = commentsForSiteId;
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

	public long getSiteId() {
		return siteId;
	}

	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
