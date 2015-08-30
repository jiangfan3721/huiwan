package entity;

import java.sql.Timestamp;
import java.util.Date;

public class PictureForSite {
	
	private long id;
	private String picPath;
	private long siteCommentId;
	private long userId;
	private long siteId;
	private float size;
	private Timestamp time;
	
	public PictureForSite(){
		this.id = -1;
		this.picPath = "";
		this.siteCommentId = -1;
		this.userId = -1;
		this.siteId = -1;
		this.size = -1;
		this.time = new Timestamp(0);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public long getSiteCommentId() {
		return siteCommentId;
	}

	public void setSiteCommentId(long siteCommentId) {
		this.siteCommentId = siteCommentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getSiteId() {
		return siteId;
	}

	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
