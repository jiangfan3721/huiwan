package entity;

import java.sql.Timestamp;
import java.util.Date;

public class Site {
	
	private long id;
	private String name;
	private String logoPath;
	private String imgPath;
	private String address;
	private String rate;
	private float coorsX;
	private float coorsY;
	private String keyValue;
	private String desc;
	private long cityId;
	private String englishName;
	
	public Site() {
		this.id = -1;
		this.name = "";
		this.logoPath = "";
		this.imgPath = "";
		this.address = "";
		this.rate = "";
		this.coorsX = 0.0f;
		this.coorsY = 0.0f;
		this.keyValue = "";
		this.desc = "";
		this.cityId = -1;
		this.englishName = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	
}
