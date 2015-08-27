package api.ret.obj;

public class UsersNumber extends RetObjBase {

	private int usersNumber = 0;

	public int getUsersNumber() {
		return usersNumber;
	}

	public void setUsersNumber(int usersNumber) {
		this.usersNumber = usersNumber;
	}

	public UsersNumber(int usersNumber) {
		super();
		this.usersNumber = usersNumber;
	}

	public UsersNumber() {
		super();
	}
}
