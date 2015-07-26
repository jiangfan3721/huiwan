package api.location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ErrMsg;
import api.ret.obj.MinisiteInfoList;
import api.ret.obj.RetCode;
import bll.BizUtil;
import bll.HttpUtil;

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
		if (minisiteIdsStr == null || minisiteIdsStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.SITE_IDS_NULL);
			return;
		}
		String[] minisiteIdStrArray = minisiteIdsStr.split(";");
		
		long[] minisiteIds = new long[minisiteIdStrArray.length];
		
		for (int i = 0; i < minisiteIdStrArray.length; i++) {
			try {
				minisiteIds[i] = Long.parseLong(minisiteIdStrArray[i]);
			} catch (NumberFormatException e) {
				System.out.println("Error while parse " + minisiteIdStrArray[i] + " to long");
				HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.NUMBER_FORMAT_ERROR);
				return;
			}
		}
		
		MinisiteInfoList minisiteInfoList = BizUtil.getMinisitesInfo(minisiteIds);
		
		HttpUtil.normalRespond(response, RetCode.SUCCESS, minisiteInfoList);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
