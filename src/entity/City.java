package entity;

public class City {

	private long city_id;
	private String name;
	private String citySketch;
	private String cityInformation;
	private String englishName;
	private String picPath;
	
	public City(){
		this.city_id = -1;
		this.name = "";
		this.citySketch = "";
		this.cityInformation = "";
		this.englishName = "";
		this.picPath = "";
	}

	public long getCity_id() {
		return city_id;
	}

	public void setCity_id(long city_id) {
		this.city_id = city_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCitySketch() {
		return citySketch;
	}

	public void setCitySketch(String citySketch) {
		this.citySketch = citySketch;
	}

	public String getCityInformation() {
		return cityInformation;
	}

	public void setCityInformation(String cityInformation) {
		this.cityInformation = cityInformation;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
	
}
