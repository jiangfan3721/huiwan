package entity;

public class Site {
	
	private long siteId;
	private String name;
	private String logoPath;
	private String address;
	private String rate;
	private float coorsX;
	private float coorsY;
	private String keyValue;
	private String descPath;
	private long cityId;
	private String englishName;
	
	public Site() {
		this.siteId = -1;
		this.name = "";
		this.logoPath = "";
		this.address = "";
		this.rate = "";
		this.coorsX = 0.0f;
		this.coorsY = 0.0f;
		this.keyValue = "";
		this.descPath = "";
		this.cityId = -1;
		this.englishName = "";
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

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	
}
