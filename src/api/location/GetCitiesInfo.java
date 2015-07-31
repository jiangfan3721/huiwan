package api.location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.CityInfoList;
import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import bll.BizUtil;
import bll.HttpUtil;

/**
 * Servlet implementation class GetCitiesInfo
 */
@WebServlet("/api/location/getCitiesInfo")
public class GetCitiesInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCitiesInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cityIdsStr = request.getParameter("cityIds");
		if (cityIdsStr == null || cityIdsStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.CITY_IDS_NULL);
			return;
		}
		String[] cityIdStrArray = cityIdsStr.split(";");
		
		long[] cityIds = new long[cityIdStrArray.length];
		
		for (int i = 0; i < cityIdStrArray.length; i++) {
			try {
				cityIds[i] = Long.parseLong(cityIdStrArray[i]);
			} catch (NumberFormatException e) {
				System.out.println("Error while parse " + cityIdStrArray[i] + " to long");
				HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.NUMBER_FORMAT_ERROR);
				return;
			}
		}
		
		CityInfoList cityInfoList = BizUtil.getCitiesInfo(cityIds);
		
		HttpUtil.normalRespond(response, RetCode.SUCCESS, cityInfoList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
