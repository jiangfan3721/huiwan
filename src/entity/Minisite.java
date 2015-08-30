package entity;

import java.sql.Timestamp;
import java.util.Date;


public class Minisite {

	private long id;
	private long siteId;
	private String name;
	private String logoPath;
	private String address;
	private String desc;
	private float coorsX;
	private float coorsY;
	private String keyValue;
	private String musicPath;
	private String lrcPath;
	private String englishName;
	
	public Minisite(){
		this.id = -1;
		this.siteId = -1;
		this.name = "";
		this.logoPath = "";
		this.address = "";
		this.desc = "";
		this.coorsX = 0.0f;
		this.coorsY = 0.0f;
		this.keyValue = "";
		this.lrcPath = "";
		this.musicPath = "";
		this.englishName = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSiteId() {
		return siteId;
	}

	public void setSiteId(long siteId) {
		this.siteId = siteId;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	public String getLrcPath() {
		return lrcPath;
	}

	public void setLrcPath(String lrcPath) {
		this.lrcPath = lrcPath;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
}
