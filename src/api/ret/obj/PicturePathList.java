package api.ret.obj;

import java.util.ArrayList;

public class PicturePathList extends RetObjBase {
	
	ArrayList<String> paths = new ArrayList<String>();

	public ArrayList<String> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<String> paths) {
		this.paths = paths;
	}
	
	public void addPath(String path) {
		this.paths.add(path);
	}
}
