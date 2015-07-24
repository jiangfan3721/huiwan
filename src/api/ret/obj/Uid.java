package api.ret.obj;

public class Uid extends RetObjBase {
	
	private long uid;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public Uid(long uid) {
		super();
		this.uid = uid;
	}

	public Uid() {
		super();
	}
}
