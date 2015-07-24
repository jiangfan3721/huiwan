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
import api.ret.obj.SiteList;
import bll.BizUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetPlanList
 */
@WebServlet("/api/account/getPlanList")
public class GetPlanList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPlanList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uidStr = request.getParameter("uid");
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		
		long uid = -1;
		Timestamp startTime = new Timestamp(System.currentTimeMillis());
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		ApiRet ret = new ApiRet();
		
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
		
		try {
			System.out.println(startTimeStr);
			System.out.println(endTimeStr);
			startTime = Timestamp.valueOf(startTimeStr);
			endTime = Timestamp.valueOf(endTimeStr);
		} catch (Exception e) {
			System.out.println("Error while parse startTime/endTime to timestamp.");
			ret.setCode(RetCode.BAD_REQUEST);
			ret.setData(new ErrMsg());
			JSONObject jsonObject = JSONObject.fromObject(ret);
			response.getWriter().append(jsonObject.toString());
			return;
		}
		
		SiteList siteList = BizUtil.getPlanList(uid, startTime, endTime);
		
		ret.setCode(RetCode.SUCCESS);
		ret.setData(siteList);
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
