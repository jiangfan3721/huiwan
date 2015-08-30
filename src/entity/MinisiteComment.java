package entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import entity.PictureForMinisite;

public class MinisiteComment {
	
	private long id;
	private long userId;
	private String nickname;
	private String userIcon;
	private long minisiteId;
	private String content;
	private ArrayList<PictureForMinisite> pic;
	private Timestamp time;
	
	public MinisiteComment(){
		this.id = -1;
		this.userId = -1;
		this.nickname = "";
		this.userIcon = "";
		this.minisiteId = -1;
		this.content = "";
		this.time = new Timestamp(0);
		this.pic = new ArrayList<PictureForMinisite>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public ArrayList<PictureForMinisite> getPic() {
		return pic;
	}

	public void setPic(ArrayList<PictureForMinisite> pic) {
		this.pic = pic;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
