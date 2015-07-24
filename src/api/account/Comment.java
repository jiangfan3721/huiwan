package api.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ApiRet;
import api.ret.obj.ErrMsg;
import api.ret.obj.OptRetCode;
import api.ret.obj.RetCode;
import bll.BizUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/api/account/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String minisiteIdStr = request.getParameter("minisiteId");
		String desc = request.getParameter("desc");
		String uidStr = request.getParameter("uid");
		
		long minisiteId = -1, uid = -1;
		ApiRet ret = new ApiRet();
		
		try {
			minisiteId = Long.parseLong(minisiteIdStr);
			uid = Long.parseLong(uidStr);
		} catch (NumberFormatException e) {
			System.out.println("Error while parse minisiteId/uid to long");
			ret.setCode(RetCode.BAD_REQUEST);
			ret.setData(new ErrMsg());
			JSONObject jsonObject = JSONObject.fromObject(ret);
			response.getWriter().append(jsonObject.toString());
			return;
		}
		
		int optRet = BizUtil.comment(minisiteId, desc, uid);
		if (optRet > 0) {
			ret.setCode(RetCode.SUCCESS);
			ret.setData(OptRetCode.getSuccRetCode());
		} else {
			ret.setCode(RetCode.NOT_FOUND);
			ret.setData(OptRetCode.getFailRetCode());
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
