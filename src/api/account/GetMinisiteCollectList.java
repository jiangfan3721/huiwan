package api.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ApiRet;
import api.ret.obj.ErrMsg;
import api.ret.obj.MinisiteList;
import api.ret.obj.RetCode;
import bll.BizUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetMinisiteCollectList
 */
@WebServlet("/api/account/getMinisiteCollectList")
public class GetMinisiteCollectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMinisiteCollectList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uidStr = request.getParameter("uid");
		
		long uid = -1;
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

		MinisiteList minisiteList = BizUtil.getMinisiteCollectList(uid);
		ret.setCode(RetCode.SUCCESS);
		ret.setData(minisiteList);
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
