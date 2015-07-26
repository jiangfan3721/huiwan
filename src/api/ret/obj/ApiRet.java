package api.ret.obj;

public class ApiRet {
	
	private int code;
	private RetObjBase data;
	
	public ApiRet() {
		super();
		this.code = RetCode.NULL;
	}
	
	public ApiRet(int code, RetObjBase data) {
		super();
		this.code = code;
		this.data = data;
	}
		
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public RetObjBase getData() {
		return data;
	}
	public void setData(RetObjBase data) {
		this.data = data;
	}
}
