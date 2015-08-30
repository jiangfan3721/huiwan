package entity;

import java.sql.Timestamp;
import java.util.Date;

public class PictureForMinisite {
	
	private long id;
	private String picPath;
	private long minisiteCommentId;
	private long userId;
	private long minisiteId;
	private float size;
	private Timestamp time;
	
	public PictureForMinisite(){
		this.id = -1;
		this.picPath = "";
		this.minisiteCommentId = -1;
		this.userId = -1;
		this.minisiteId = -1;
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

	public long getMinisiteCommentId() {
		return minisiteCommentId;
	}

	public void setMinisiteCommentId(long minisiteCommentId) {
		this.minisiteCommentId = minisiteCommentId;
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
