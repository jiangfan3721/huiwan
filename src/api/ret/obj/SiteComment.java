package api.ret.obj;

import java.sql.Timestamp;

public class SiteComment extends RetObjBase {
	
	private long accountUserId;
	private String nickname;
	private String content;
	private int score;
	private String picPath;
	private Timestamp time;
	
	public SiteComment(long accountUserId, String nickname, String content, int score, String picPath, Timestamp time) {
		super();
		this.accountUserId = accountUserId;
		this.nickname = nickname;
		this.content = content;
		this.score = score;
		this.picPath = picPath;
		this.time = time;
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
