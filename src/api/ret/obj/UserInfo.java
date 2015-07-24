package api.ret.obj;

import java.sql.Timestamp;

public class UserInfo extends RetObjBase {
	
	private long userId;
	private String telephone;
	private String nickname;
	private String userIcon;
	private long regTime;
	private String sex;
	private String state;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public long getRegTime() {
		return regTime;
	}
	public void setRegTime(long regTime) {
		this.regTime = regTime;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public UserInfo(long userId, String telephone, String nickname, String userIcon, long regTime, String sex,
			String state) {
		super();
		this.userId = userId;
		this.telephone = telephone;
		this.nickname = nickname;
		this.userIcon = userIcon;
		this.regTime = regTime;
		this.sex = sex;
		this.state = state;
	}
}
