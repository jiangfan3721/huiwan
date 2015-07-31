package api.ret.obj;

import java.util.ArrayList;

public class CityIdList extends RetObjBase {
	
	ArrayList<Long> cityIdList;

	public ArrayList<Long> getCityList() {
		return cityIdList;
	}

	public void setCityList(ArrayList<Long> cityIdList) {
		this.cityIdList = cityIdList;
	}
}
