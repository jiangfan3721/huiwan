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
 * Servlet implementation class AddPlan
 */
@WebServlet("/api/account/addPlan")
public class AddPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String siteIdStr = request.getParameter("siteId");
		String uidStr = request.getParameter("uid");
		
		if (siteIdStr == null || siteIdStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.SITE_ID_NULL);
			return;
		}
		
		if (uidStr == null || uidStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.UID_NULL);
			return;
		}
		
		long uid = -1, siteId = -1;
		
		try {
			uid = Long.parseLong(uidStr);
			siteId = Long.parseLong(siteIdStr);
		} catch (NumberFormatException e) {
			System.out.println("Error while parse uid/siteId to long");
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.NUMBER_FORMAT_ERROR);
			return;
		}
		
		if (BizUtil.checkPlan(siteId, uid)) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.PLAN_ALREADY_EXIST);
			return;
		}
		
		//int optRet = BizUtil.addPlan(siteId, uid);
		BizUtil.addPlan(siteId, uid);
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
