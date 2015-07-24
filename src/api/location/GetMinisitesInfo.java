package api.location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ApiRet;
import api.ret.obj.ErrMsg;
import api.ret.obj.MinisiteInfoList;
import api.ret.obj.RetCode;
import api.ret.obj.SiteInfoList;
import bll.BizUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetMinisitesInfo
 */
@WebServlet("/api/location/getMinisitesInfo")
public class GetMinisitesInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMinisitesInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String minisiteIdsStr = request.getParameter("minisiteIds");
		String[] minisiteIdStrArray = minisiteIdsStr.split(",");
		
		long[] minisiteIds = new long[minisiteIdStrArray.length];
		ApiRet ret = new ApiRet();
		
		for (int i = 0; i < minisiteIdStrArray.length; i++) {
			try {
				minisiteIds[i] = Long.parseLong(minisiteIdStrArray[i]);
			} catch (NumberFormatException e) {
				System.out.println("Error while parse " + minisiteIdStrArray[i] + " to long");
				ret.setCode(RetCode.BAD_REQUEST);
				ret.setData(new ErrMsg());
				JSONObject jsonObject = JSONObject.fromObject(ret);
				response.getWriter().append(jsonObject.toString());
				return;
			}
		}
		
		MinisiteInfoList minisiteInfoList = BizUtil.getMinisitesInfo(minisiteIds);
		
		if (minisiteInfoList != null) {
			ret.setCode(RetCode.SUCCESS);
			ret.setData(minisiteInfoList);
		} else {
			ret.setCode(RetCode.NOT_FOUND);
			ret.setData(new ErrMsg());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(ret);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
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
