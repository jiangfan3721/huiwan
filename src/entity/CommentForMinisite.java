package entity;

import java.sql.Timestamp;
import java.util.Date;

public class CommentForMinisite {
	
	private long commentsForSiteId;
	private long accountUserId;
	private String nickname;
	private long siteId;
	private String content;
	private int score;
	private String picPath;
	private Timestamp time;
	
	public CommentForMinisite(){
		this.commentsForSiteId = -1;
		this.accountUserId = -1;
		this.nickname = "";
		this.siteId = -1;
		this.content = "";
		this.score = -1;
		this.picPath = "";
		this.time = new Timestamp(0);
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
	
	public String getNickName(){
		return nickname;
	}
	
	public void setNickName(String nickname){
		this.nickname = nickname;
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

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
