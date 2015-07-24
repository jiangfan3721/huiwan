package api.location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ApiRet;
import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import api.ret.obj.SiteCommentList;
import bll.BizUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetSiteComments
 */
@WebServlet("/api/location/getSiteComments")
public class GetSiteComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSiteComments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String siteIdStr = request.getParameter("siteId");
		String sizeStr = request.getParameter("size");
		String offsetStr = request.getParameter("offset");
		
		long siteId = -1, size = 0, offset = 0;
		ApiRet ret = new ApiRet();
		
		try {
			siteId = Long.parseLong(siteIdStr);
			size = Long.parseLong(sizeStr);
			offset = Long.parseLong(offsetStr);
		} catch (NumberFormatException e) {
			System.out.println("Error while parse siteId/size/offset to long");
			ret.setCode(RetCode.BAD_REQUEST);
			ret.setData(new ErrMsg());
			JSONObject jsonObject = JSONObject.fromObject(ret);
			response.getWriter().append(jsonObject.toString());
			return;
		}
		
		SiteCommentList siteCommentList = BizUtil.getSiteComments(siteId, size, offset);
		ret.setCode(RetCode.SUCCESS);
		ret.setData(siteCommentList);
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
