package entity;

public class Minisite {

	private long minisiteId;
	private long siteId;
	private String name;
	private String logoPath;
	private String address;
	private String descPath;
	private float coorsX;
	private float coorsY;
	private String keyValue;
	private String musicPath;
	private String ircPath;
	private String englishName;
	
	public Minisite(){
		this.minisiteId = -1;
		this.siteId = -1;
		this.name = "";
		this.logoPath = "";
		this.address = "";
		this.descPath = "";
		this.coorsX = 0.0f;
		this.coorsY = 0.0f;
		this.keyValue = "";
		this.ircPath = "";
		this.musicPath = "";
		this.englishName = "";
	}

	public long getMinisiteId() {
		return minisiteId;
	}

	public void setMinisiteId(long minisiteId) {
		this.minisiteId = minisiteId;
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

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
}
