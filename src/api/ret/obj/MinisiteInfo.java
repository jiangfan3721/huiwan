package api.ret.obj;

public class MinisiteInfo extends RetObjBase {

	private String name;
	private String logoPath;
	private String address;
	private String picPath;
	private String descPath;
	private float coorsX;
	private float coorsY;
	private String keyValue;
	private String musicPath;
	private String ircPath;
	
	public MinisiteInfo(String name, String logoPath, String address, String picPath, String descPath, float coorsX,
			float coorsY, String keyValue, String musicPath, String ircPath) {
		super();
		this.name = name;
		this.logoPath = logoPath;
		this.address = address;
		this.picPath = picPath;
		this.descPath = descPath;
		this.coorsX = coorsX;
		this.coorsY = coorsY;
		this.keyValue = keyValue;
		this.musicPath = musicPath;
		this.ircPath = ircPath;
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
	public String getDescPath() {
		return descPath;
	}
	public void setDescPath(String descPath) {
		this.descPath = descPath;
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
	public String getIrcPath() {
		return ircPath;
	}
	public void setIrcPath(String ircPath) {
		this.ircPath = ircPath;
	}

	
}
