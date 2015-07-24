package api.ret.obj;

public class SiteInfo extends RetObjBase {
	
	private String name;
	private String logoPath;
	private String address;
	private String picPath;
	private String rate;
	private float coorsX;
	private float coorsY;
	private String keyValue;
	private String descPath;
	
	public SiteInfo(String name, String logoPath, String address, String picPath, String rate, float coorsX,
			float coorsY, String keyValue, String descPath) {
		super();
		this.name = name;
		this.logoPath = logoPath;
		this.address = address;
		this.picPath = picPath;
		this.rate = rate;
		this.coorsX = coorsX;
		this.coorsY = coorsY;
		this.keyValue = keyValue;
		this.descPath = descPath;
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
}
