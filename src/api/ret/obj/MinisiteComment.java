package api.ret.obj;

import java.sql.Timestamp;

public class MinisiteComment extends RetObjBase {

	private long accountUserId;
	private String nickname;
	private String content;
	private Timestamp time;
	
	public MinisiteComment(long accountUserId, String nickname, String content, Timestamp time) {
		super();
		this.accountUserId = accountUserId;
		this.nickname = nickname;
		this.content = content;
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
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}

}
