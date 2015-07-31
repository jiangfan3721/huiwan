package entity;

import java.sql.Timestamp;
import java.util.Date;

public class PictureForMinisite {
	
	private long pictureForMinisite;
	private String picPath;
	private long commentForMinisiteId;
	private long userId;
	private long minisiteId;
	private float size;
	private Timestamp time;
	
	public PictureForMinisite(){
		this.pictureForMinisite = -1;
		this.picPath = "";
		this.commentForMinisiteId = -1;
		this.userId = -1;
		this.minisiteId = -1;
		this.size = -1;
		this.time = new Timestamp(0);
	}

	public long getPictureForMinisite() {
		return pictureForMinisite;
	}

	public void setPictureForMinisite(long pictureForMinisite) {
		this.pictureForMinisite = pictureForMinisite;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public long getCommentForMinisiteId() {
		return commentForMinisiteId;
	}

	public void setCommentForMinisiteId(long commentForMinisiteId) {
		this.commentForMinisiteId = commentForMinisiteId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getMinisiteId() {
		return minisiteId;
	}

	public void setMinisiteId(long minisiteId) {
		this.minisiteId = minisiteId;
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
