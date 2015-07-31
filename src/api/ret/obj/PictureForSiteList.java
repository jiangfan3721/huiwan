package api.ret.obj;

import java.util.ArrayList;

import entity.PictureForSite;

public class PictureForSiteList extends RetObjBase {
	
	ArrayList<PictureForSite> pictures = new ArrayList<PictureForSite>();

	public ArrayList<PictureForSite> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<PictureForSite> pictures) {
		this.pictures = pictures;
	}
	
	public void addPicture(PictureForSite picture) {
		this.pictures.add(picture);
	}
}
