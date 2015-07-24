package entity;

import java.sql.Timestamp;
import java.util.Date;

public class Account {
	
	private long userId;
	private String realName;
	private String telephone;
	private String password;
	private String nickname;
	private String userIcon;
	private long regTime;
	private String sex;
	private String state;
	private Timestamp birthday;
	private String selfIntroduction;
	
	public Account() {
		this.userId = -1;
		this.realName = "";
		this.telephone = "";
		this.password = "";
		this.nickname = "";
		this.userIcon = "";
		this.regTime = 0;
		this.sex = "";
		this.state = "";
		this.birthday = new Timestamp(0);
		this.selfIntroduction = "";
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickName) {
		this.nickname = nickName;
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
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getSelfIntroduction() {
		return selfIntroduction;
	}
	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}
	
	
}
