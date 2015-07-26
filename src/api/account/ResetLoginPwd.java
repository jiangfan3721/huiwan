package api.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import bll.BizUtil;
import bll.HttpUtil;

/**
 * Servlet implementation class ResetLoginPwd
 */
@WebServlet("/api/account/resetLoginPwd")
public class ResetLoginPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetLoginPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String telephone = request.getParameter("telephone");
		String newPassword = request.getParameter("newPassword");
		
		if (telephone == null || telephone.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.TELEPHONE_NULL);
			return;
		}
		
		if (newPassword == null || newPassword.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.PASSWORD_NULL);
			return;
		}
		
		if (!BizUtil.checkUser(telephone)) {
			HttpUtil.errorRespond(response, RetCode.NOT_FOUND, 
					ErrMsg.USER_NOT_EXIST);
			return;
		}
		
		BizUtil.resetLoginPwd(telephone, newPassword);
		
		HttpUtil.normalRespond(response, RetCode.SUCCESS, null);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
