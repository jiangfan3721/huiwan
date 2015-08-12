package api.ret.obj;

import java.sql.Timestamp;

public class UserInfo extends RetObjBase {
	
	private long userId;
	private String realName;
	private String telephone;
	private String password;
	private String nickname;
	private String userIcon;
	private String background;
	private Timestamp regTime;
	private String sex;
	private String state;
	private Timestamp birthday;
	private String selfIntroduction;
	
	public UserInfo() {
		this.userId = -1;
		this.realName = "";
		this.telephone = "";
		this.password = "";
		this.nickname = "";
		this.userIcon = "";
		this.background = "";
		this.regTime = new Timestamp(0);
		this.sex = "";
		this.state = "";
		this.birthday = new Timestamp(0);
		this.selfIntroduction = "";
	}
	
	public UserInfo(long userId, String realName, String telephone, String password, String nickname, String userIcon, String background,
			Timestamp regTime, String sex, String state, Timestamp birthday, String selfIntroduction) {
		super();
		this.userId = userId;
		this.realName = realName;
		this.telephone = telephone;
		this.password = password;
		this.nickname = nickname;
		this.userIcon = userIcon;
		this.background = background;
		this.regTime = regTime;
		this.sex = sex;
		this.state = state;
		this.birthday = birthday;
		this.selfIntroduction = selfIntroduction;
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
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public Timestamp getRegTime() {
		return regTime;
	}
	public void setRegTime(Timestamp regTime) {
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
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
}
