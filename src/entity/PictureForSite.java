package entity;

import java.sql.Timestamp;
import java.util.Date;

public class PictureForSite {
	
	private long pictureForSiteId;
	private String picPath;
	private long commentForSiteId;
	private long userId;
	private long siteId;
	private float size;
	private Timestamp time;
	
	public PictureForSite(){
		this.pictureForSiteId = -1;
		this.picPath = "";
		this.commentForSiteId = -1;
		this.userId = -1;
		this.siteId = -1;
		this.size = -1;
		this.time = new Timestamp(0);
	}

	public long getPictureForSiteId() {
		return pictureForSiteId;
	}

	public void setPictureForSiteId(long pictureForSiteId) {
		this.pictureForSiteId = pictureForSiteId;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public long getCommentForSiteId() {
		return commentForSiteId;
	}

	public void setCommentForSiteId(long commentForSiteId) {
		this.commentForSiteId = commentForSiteId;
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
