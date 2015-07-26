package api.location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import api.ret.obj.SiteInfoList;
import bll.BizUtil;
import bll.HttpUtil;

/**
 * Servlet implementation class GetSiteInfo
 */
@WebServlet("/api/location/getSitesInfo")
public class GetSitesInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSitesInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String siteIdsStr = request.getParameter("siteIds");
		if (siteIdsStr == null || siteIdsStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.SITE_IDS_NULL);
			return;
		}
		String[] siteIdStrArray = siteIdsStr.split(";");
		
		long[] siteIds = new long[siteIdStrArray.length];
		
		for (int i = 0; i < siteIdStrArray.length; i++) {
			try {
				siteIds[i] = Long.parseLong(siteIdStrArray[i]);
			} catch (NumberFormatException e) {
				System.out.println("Error while parse " + siteIdStrArray[i] + " to long");
				HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.NUMBER_FORMAT_ERROR);
				return;
			}
		}
		
		SiteInfoList siteInfoList = BizUtil.getSitesInfo(siteIds);
		
		HttpUtil.normalRespond(response, RetCode.SUCCESS, siteInfoList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
