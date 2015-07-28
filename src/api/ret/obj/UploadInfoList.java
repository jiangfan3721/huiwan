package api.ret.obj;

import java.util.HashMap;

public class UploadInfoList extends RetObjBase {
	
	private HashMap<String, String> uploadInfoList = 
			new HashMap<String, String>();

	public HashMap<String, String> getUploadInfoList() {
		return uploadInfoList;
	}

	public void setUploadInfoList(HashMap<String, String> uploadInfoList) {
		this.uploadInfoList = uploadInfoList;
	}
	
	public void addUploadInfo(String fileName, String md5) {
		this.uploadInfoList.put(fileName, md5);
	}
}
