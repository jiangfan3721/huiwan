package api.location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ApiRet;
import api.ret.obj.CityList;
import api.ret.obj.RetCode;
import bll.BizUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetCityList
 */
@WebServlet("/api/location/getCityList")
public class GetCityList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCityList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CityList cityList = BizUtil.getCityList();
		
		ApiRet ret = new ApiRet();
		ret.setCode(RetCode.SUCCESS);
		ret.setData(cityList);
		
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
