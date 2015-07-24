package entity;

import java.sql.Timestamp;
import java.util.Date;

public class Site {
	
	private long destinationId;
	private String name;
	private String logoPath;
	private String address;
	private String picPath;
	private String rate;
	private float coorsX;
	private float coorsY;
	private String keyValue;
	private String descPath;
	private long cityId;
	
	public Site() {
		this.destinationId = -1;
		this.name = "";
		this.logoPath = "";
		this.address = "";
		this.picPath = "";
		this.rate = "";
		this.coorsX = 0.0f;
		this.coorsY = 0.0f;
		this.keyValue = "";
		this.descPath = "";
		this.cityId = -1;
	}

	public long getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(long destinationId) {
		this.destinationId = destinationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public float getCoorsX() {
		return coorsX;
	}

	public void setCoorsX(float coorsX) {
		this.coorsX = coorsX;
	}

	public float getCoorsY() {
		return coorsY;
	}

	public void setCoorsY(float coorsY) {
		this.coorsY = coorsY;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getDescPath() {
		return descPath;
	}

	public void setDescPath(String descPath) {
		this.descPath = descPath;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	
	
}
