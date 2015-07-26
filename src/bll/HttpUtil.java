package bll;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ApiRet;
import api.ret.obj.ErrMsg;
import api.ret.obj.RetObjBase;
import net.sf.json.JSONObject;

public class HttpUtil {
	
	private static String CHARSET = "text/html;charset=UTF-8";
	
	public static void respond(HttpServletResponse response, ApiRet ret) throws ServletException, IOException {
		JSONObject jsonObject = JSONObject.fromObject(ret);
		response.setHeader("Content-type", CHARSET);
		response.getWriter().append(jsonObject.toString());
	}
	
	public static void normalRespond(HttpServletResponse response, int code, RetObjBase obj) throws ServletException, IOException{
		HttpUtil.respond(response, new ApiRet(code, obj));
	}
	
	public static void errorRespond(HttpServletResponse response, int code, String msg) throws ServletException, IOException {
		HttpUtil.respond(response, new ApiRet(code, new ErrMsg(msg)));
	}
}
