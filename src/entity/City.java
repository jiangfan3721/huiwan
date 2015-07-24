package entity;

public class City {

	private long city_id;
	private String name;
	
	public City(){
		this.city_id = -1;
		this.name = "";
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
}
