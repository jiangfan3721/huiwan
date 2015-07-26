package api.location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import api.ret.obj.SiteCommentList;
import bll.BizUtil;
import bll.HttpUtil;

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
		
		if (siteIdStr == null || siteIdStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.SITE_ID_NULL);
			return;
		}
		
		if (sizeStr == null || sizeStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.SIZE_NULL);
			return;
		}
		
		if (offsetStr == null || offsetStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.OFFSET_NULL);
			return;
		}
		
		long siteId = -1, size = 0, offset = 0;
		
		try {
			siteId = Long.parseLong(siteIdStr);
			size = Long.parseLong(sizeStr);
			offset = Long.parseLong(offsetStr);
		} catch (NumberFormatException e) {
			System.out.println("Error while parse siteId/size/offset to long");
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.NUMBER_FORMAT_ERROR);
			return;
		}
		
		SiteCommentList siteCommentList = BizUtil.getSiteComments(siteId, size, offset);
		
		HttpUtil.normalRespond(response, RetCode.SUCCESS, siteCommentList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
