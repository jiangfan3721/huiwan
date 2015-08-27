package api.location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import api.ret.obj.UsersNumber;
import bll.BizUtil;
import bll.HttpUtil;

/**
 * Servlet implementation class GetUsersNumberOfSite
 */
@WebServlet("/api/location/getUsersNumberOfSite")
public class GetUsersNumberOfSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUsersNumberOfSite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String siteIdStr = request.getParameter("siteId");
		
		if (siteIdStr == null || siteIdStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.SITE_ID_NULL);
			return;
		}
		
		long siteId = -1;
		
		try {
			siteId = Long.parseLong(siteIdStr);
		} catch (NumberFormatException e) {
			System.out.println("Error while parse " + siteIdStr + " to long");
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.NUMBER_FORMAT_ERROR);
			return;
		}
		
		UsersNumber usersNumber = BizUtil.getUsersNumberOfSite(siteId);
		HttpUtil.normalRespond(response, RetCode.SUCCESS, usersNumber);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
