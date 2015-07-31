package api.ret.obj;

import java.util.ArrayList;

import entity.PictureForMinisite;

public class PictureForMinisiteList extends RetObjBase {
	
	private ArrayList<PictureForMinisite> pictures = new ArrayList<PictureForMinisite>();

	public ArrayList<PictureForMinisite> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<PictureForMinisite> pictures) {
		this.pictures = pictures;
	}
	
	public void addPicture(PictureForMinisite picture) {
		this.pictures.add(picture);
	}
	
}
