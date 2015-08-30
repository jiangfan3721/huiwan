package entity;

public class City {

	private long id;
	private String name;
	private String citySketch;
	private String cityInformation;
	private String englishName;
	private String picPath;
	
	public City(){
		this.id = -1;
		this.name = "";
		this.citySketch = "";
		this.cityInformation = "";
		this.englishName = "";
		this.picPath = "";
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
