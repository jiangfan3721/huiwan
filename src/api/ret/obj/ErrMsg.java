package api.ret.obj;

public class ErrMsg extends RetObjBase {
	
	private String msg;
	
	public ErrMsg(String msg) {
		super();
		this.msg = msg;
	}

	public ErrMsg() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
