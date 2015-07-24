package entity;

import java.sql.Timestamp;
import java.util.Date;

public class CommentForSite {
	
	private long commentsForDestinationId;
	private long accountUserId;
	private String nickname;
	private long destinationId;
	private String content;
	private int score;
	private String picPath;
	private Timestamp time;
	
	public CommentForSite(){
		this.commentsForDestinationId = -1;
		this.accountUserId = -1;
		this.nickname = "";
		this.destinationId = -1;
		this.content = "";
		this.score = -1;
		this.picPath = "";
		this.time = new Timestamp(0);
	}

	public long getCommentsForDestinationId() {
		return commentsForDestinationId;
	}

	public void setCommentsForDestinationId(long commentsForDestinationId) {
		this.commentsForDestinationId = commentsForDestinationId;
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

	public long getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(long destinationId) {
		this.destinationId = destinationId;
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
