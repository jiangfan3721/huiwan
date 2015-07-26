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
 * Servlet implementation class AddMinisiteCollect
 */
@WebServlet("/api/account/addMinisiteCollect")
public class AddMinisiteCollect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMinisiteCollect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String minisiteIdStr = request.getParameter("minisiteId");
		String uidStr = request.getParameter("uid");
		
		if (minisiteIdStr == null || minisiteIdStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.MINISITE_ID_NULL);
			return;
		}
		
		if (uidStr == null || uidStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.UID_NULL);
			return;
		}
		
		long uid = -1, minisiteId = -1;
		
		try {
			uid = Long.parseLong(uidStr);
			minisiteId = Long.parseLong(minisiteIdStr);
		} catch (NumberFormatException e) {
			System.out.println("Error while parse uid/minisiteId to long");
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.NUMBER_FORMAT_ERROR);
			return;
		}
		
		if (BizUtil.checkMinisiteCollect(minisiteId, uid)) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.MINISITE_COLLECT_ALREADY_EXIST);
			return;
		}
		
		//int optRet = BizUtil.addMinisiteCollect(minisiteId, uid);
		BizUtil.addMinisiteCollect(minisiteId, uid);
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
