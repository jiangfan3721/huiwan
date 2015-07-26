package api.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import bll.BizUtil;
import bll.HttpUtil;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/api/account/commentMinisite")
public class CommentMinisite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentMinisite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String minisiteIdStr = request.getParameter("minisiteId");
		String content = request.getParameter("content");
		String uidStr = request.getParameter("uid");
		String picPathStr = request.getParameter("picPath");
		
		if (minisiteIdStr == null || minisiteIdStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.MINISITE_ID_NULL);
			return;
		}
		
		if (content == null || content.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.CONTENT_NULL);
			return;
		}
		
		if (uidStr == null || uidStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.UID_NULL);
			return;
		}
		
		long minisiteId = -1, uid = -1;
		try {
			uid = Long.parseLong(uidStr);
			minisiteId = Long.parseLong(minisiteIdStr);
		} catch (NumberFormatException e) {
			System.out.println("Error while parse minisiteId/uid to long.");
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.NUMBER_FORMAT_ERROR);
			return;
		}
		
		String[] paths = picPathStr.split(";");
		ArrayList<String> picPath = (ArrayList<String>) Arrays.asList(paths);
		
		//int optRet = BizUtil.commentMinisite(minisiteId, content, uid, picPath);
		BizUtil.commentMinisite(minisiteId, content, uid, picPath);
		HttpUtil.normalRespond(response, RetCode.SUCCESS, null);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
