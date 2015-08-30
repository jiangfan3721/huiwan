package entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import entity.PictureForSite;

public class SiteComment {
	
	private long id;
	private long userId;
	private String nickname;
	private String userIcon;
	private long siteId;
	private String content;
	private int score;
	private ArrayList<PictureForSite> pic;
	private Timestamp time;
	
	public SiteComment(){
		this.id = -1;
		this.userId = -1;
		this.nickname = "";
		this.userIcon = "";
		this.siteId = -1;
		this.content = "";
		this.score = -1;
		this.time = new Timestamp(0);
		this.pic = new ArrayList<PictureForSite>();
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

	public ArrayList<PictureForSite> getPic() {
		return pic;
	}

	public void setPic(ArrayList<PictureForSite> pic) {
		this.pic = pic;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
