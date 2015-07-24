package api.ret.obj;

public class OptRetCode extends RetObjBase {
	
	private int optRetCode;
	

	public int getOptRetCode() {
		return optRetCode;
	}

	public void setOptRetCode(int optRetCode) {
		this.optRetCode = optRetCode;
	}
	
	public OptRetCode(int optRetCode) {
		this.optRetCode = optRetCode;
	}
	
	public static OptRetCode getSuccRetCode() {
		return new OptRetCode(1);
	}
	
	public static OptRetCode getFailRetCode() {
		return new OptRetCode(0);
	}
}
