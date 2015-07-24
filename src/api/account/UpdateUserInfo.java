package api.account;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ApiRet;
import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import api.ret.obj.UserInfo;
import bll.BizUtil;
import entity.Account;
import net.sf.json.JSONObject;

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
		
		ApiRet ret = new ApiRet();
		
		long uid = -1;
		try {
			uid = Long.parseLong(uidStr);
		} catch (NumberFormatException e) {
			System.out.println("Error while parse " + uidStr + " to long.");
			ret.setCode(RetCode.BAD_REQUEST);
			ret.setData(new ErrMsg());
			JSONObject jsonObject = JSONObject.fromObject(ret);
			response.getWriter().append(jsonObject.toString());
			return;
		}
		
		Timestamp birthday = new Timestamp(System.currentTimeMillis());
		try {
			birthday = Timestamp.valueOf(birthdayStr);
		} catch (Exception e) {
			System.out.println("Error while parse " + birthdayStr + " to timestamp.");
			ret.setCode(RetCode.BAD_REQUEST);
			ret.setData(new ErrMsg());
			JSONObject jsonObject = JSONObject.fromObject(ret);
			response.getWriter().append(jsonObject.toString());
			return;
		}
		
		UserInfo userInfo = BizUtil.updateUserInfo(uid, nickname, state, sex, birthday, selfIntroduction);
		if (userInfo != null) {
			ret.setCode(RetCode.SUCCESS);
			ret.setData(userInfo);
		} else {
			ret.setCode(RetCode.NOT_FOUND);
			ret.setData(new ErrMsg());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(ret);
		response.getWriter().append(jsonObject.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
