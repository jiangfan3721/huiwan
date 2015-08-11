package api.ret.obj;

public class ErrMsg extends RetObjBase {
	
	public static String USER_NOT_EXIST = "用户不存在";
	public static String USER_ALREADY_EXIST = "用户已存在";
	public static String PASSWORD_NOT_CORRET = "密码错误";
	public static String PLAN_NOT_EXIST = "想去列表中没有该项";
	public static String PLAN_ALREADY_EXIST = "想去列表中已有该项";
	public static String MINISITE_COLLECT_NOT_EXIST = "景点收藏中没有该项";
	public static String MINISITE_COLLECT_ALREADY_EXIST = "景点收藏中已有该项";
	
	public static String SIGNUP_ERROR = "注册失败";
	public static String UPDATE_USER_INFO_ERROR = "更新用户信息失败";
	public static String UPLOAD_FILE_ERROR = "上传文件失败";

	public static String TELEPHONE_NULL = "手机号为空";
	public static String PASSWORD_NULL = "密码为空";
	public static String NICKNAME_NULL = "昵称为空";
	public static String STATE_NULL = "用户所在地为空";
	public static String UID_NULL = "用户ID为空";
	public static String SITE_ID_NULL = "目的地ID为空";
	public static String SIZE_NULL = "列表大小为空";
	public static String OFFSET_NULL = "偏移量为空";
	public static String SITE_IDS_NULL = "目的地列表为空";
	public static String CITY_IDS_NULL = "城市列表为空";
	public static String CITY_ID_NULL = "城市ID为空";
	public static String MINISITE_ID_NULL = "景点ID为空";
	public static String START_TIME_NULL = "开始时间为空";
	public static String END_TIME_NULL = "结束时间为空";
	public static String CONTENT_NULL = "评论内容为空";
	public static String SCORE_NULL = "评分为空";
	
	public static String NUMBER_FORMAT_ERROR = "数字格式错误";
	public static String TIME_FORMAT_ERROR = "时间格式错误";
	
	public static String NO_FILE_TO_UPLOAD = "没有上传文件";
			
	
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
