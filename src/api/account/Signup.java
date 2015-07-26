package api.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import api.ret.obj.Uid;
import bll.BizUtil;
import bll.HttpUtil;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/api/account/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String telephone = request.getParameter("telephone");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String state = request.getParameter("state");
		
		if (telephone == null || telephone.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.TELEPHONE_NULL);
			return;
		}
		
		if (password == null || password.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.PASSWORD_NULL);
			return;
		}
		
		if (nickname == null || nickname.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST,
					ErrMsg.NICKNAME_NULL);
			return;
		}
		
		if (state == null || nickname.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST,
					ErrMsg.STATE_NULL);
		}
		
		if (BizUtil.checkUser(telephone)) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.USER_ALREADY_EXIST);
			return;
		}
		
		Uid uid = BizUtil.signup(telephone, password, nickname, state);
				
		if (uid.getUid() < 0) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.SIGNUP_ERROR);
		} else {
			HttpUtil.normalRespond(response, RetCode.SUCCESS, uid);
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
