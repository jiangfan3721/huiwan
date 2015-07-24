package api.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ApiRet;
import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import api.ret.obj.Uid;
import bll.BizUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/api/account/userLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String telephone = request.getParameter("telephone");
		String loginPassword = request.getParameter("loginPassword");
		
		Uid uid = BizUtil.userLogin(telephone, loginPassword);
		
		ApiRet ret = new ApiRet();
		
		if (uid.getUid() < 0) {
			ret.setCode(RetCode.NOT_FOUND);
			ret.setData(new ErrMsg());
		} else {
			ret.setCode(RetCode.SUCCESS);
			ret.setData(uid);
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
