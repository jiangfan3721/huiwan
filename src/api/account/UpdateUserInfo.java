package api.account;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import api.ret.obj.UserInfo;
import bll.BizUtil;
import bll.HttpUtil;

/**
 * Servlet implementation class UpdateUserInfo
 */
@WebServlet("/api/account/updateUserInfo")
public class UpdateUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uidStr = request.getParameter("uid");
		String nickname = request.getParameter("nickname");
		String state = request.getParameter("state");
		String sex = request.getParameter("sex");
		String birthdayStr = request.getParameter("birthday");
		String selfIntroduction = request.getParameter("selfIntroduction");
		String userIcon = request.getParameter("userIcon");
		String background = request.getParameter("background");
		
		if (uidStr == null || uidStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.UID_NULL);
			return;
		}
				
		long uid = -1;
		try {
			uid = Long.parseLong(uidStr);
		} catch (NumberFormatException e) {
			System.out.println("Error while parse " + uidStr + " to long.");
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.NUMBER_FORMAT_ERROR);
			return;
		}
		
		//Timestamp birthday = new Timestamp(System.currentTimeMillis());
		Timestamp birthday = null;
		try {
			birthday = Timestamp.valueOf(birthdayStr);
		} catch (Exception e) {
			System.out.println("Error while parse " + birthdayStr + " to timestamp.");
			//HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.TIME_FORMAT_ERROR);
			//return;
		}
		
		UserInfo userInfo = BizUtil.updateUserInfo(uid, nickname, state, sex, birthday, selfIntroduction, userIcon, background);
		if (userInfo != null) {
			HttpUtil.normalRespond(response, RetCode.SUCCESS, userInfo);
		} else {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.UPDATE_USER_INFO_ERROR);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
