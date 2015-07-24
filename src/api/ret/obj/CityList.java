package api.ret.obj;

import java.util.ArrayList;

import entity.City;

public class CityList extends RetObjBase {
	
	ArrayList<City> cityList;

	public ArrayList<City> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<City> cityList) {
		this.cityList = cityList;
	}
}
