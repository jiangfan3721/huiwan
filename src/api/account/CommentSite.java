package api.account;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class CommentSite
 */
@WebServlet("/api/account/commentSite")
public class CommentSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentSite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String siteIdStr = request.getParameter("siteId");
		String content = request.getParameter("content");
		String scoreStr = request.getParameter("score");
		String uidStr = request.getParameter("uid");
		String picPathStr = request.getParameter("picPath");
		
		if (siteIdStr == null || siteIdStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.SITE_ID_NULL);
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
		
		if (scoreStr == null || scoreStr.isEmpty()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.SCORE_NULL);
			return;
		}
		
		long siteId = -1, uid = -1;
		int score = -1;
		try {
			uid = Long.parseLong(uidStr);
			siteId = Long.parseLong(siteIdStr);
			score = Integer.parseInt(scoreStr);
		} catch (NumberFormatException e) {
			System.out.println("Error while parse siteId/uid/score to long.");
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.NUMBER_FORMAT_ERROR);
			return;
		}
		
		String[] paths = picPathStr.split(";");
		ArrayList<String> picPath = new ArrayList<String>();
		for (int i = 0; i < paths.length; i++) {
			picPath.add(paths[i]);
		}

		BizUtil.commentSite(siteId, content, score, uid, picPath);
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
