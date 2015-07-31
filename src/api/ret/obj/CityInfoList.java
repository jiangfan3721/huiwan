package api.ret.obj;

import java.util.ArrayList;

import entity.City;

public class CityInfoList extends RetObjBase {
	
	private ArrayList<City> cityInfoList = new ArrayList<City>();

	public ArrayList<City> getCityInfoList() {
		return cityInfoList;
	}

	public void setCityInfoList(ArrayList<City> cityInfoList) {
		this.cityInfoList = cityInfoList;
	}
	
	public void addCityInfo(City city) {
		this.cityInfoList.add(city);
	}
}
