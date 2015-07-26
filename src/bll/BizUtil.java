package bll;

import java.sql.Timestamp;
import java.util.ArrayList;

import api.ret.obj.CityList;
import api.ret.obj.MinisiteCommentList;
import api.ret.obj.MinisiteInfoList;
import api.ret.obj.PicturePathList;
import api.ret.obj.MinisiteIdList;
import api.ret.obj.SiteCommentList;
import api.ret.obj.SiteInfoList;
import api.ret.obj.SiteIdList;
import api.ret.obj.Uid;
import api.ret.obj.UserInfo;
import dal.DBUtil;
import entity.Account;
import entity.City;
import entity.CommentForMinisite;
import entity.CommentForSite;
import entity.Minisite;
import entity.Site;

public class BizUtil {
	
	/**
	 * 
	 * @param telephone
	 * @return true if telephone exists; false otherwise
	 */
	public static boolean checkUser(String telephone) {
		
		return DBUtil.checkUserExist(telephone);
	}
	
	/**
	 * 
	 * @param telephone
	 * @param loginPassword
	 * @return contains uid if login successfully; contains -1 otherwise
	 */
	public static Uid userLogin(String telephone, String loginPassword) {
		
		long uid = DBUtil.userLogin(telephone, loginPassword);
		
		return new Uid(uid);
	}
	
	/**
	 * 
	 * @param uid
	 * @return contains user information if uid exists; null otherwise
	 */
	public static UserInfo getUserInfo(long uid) {
		
		Account account = DBUtil.getUserInfo(uid);
		
		UserInfo userInfo = null;
		if (account != null) {
			userInfo = new UserInfo(account.getUserId(), account.getRealName(), account.getTelephone(),
					account.getPassword(), account.getNickname(), account.getUserIcon(), account.getRegTime(),
					account.getSex(), account.getState(), account.getBirthday(), account.getSelfIntroduction());
		}
		
		return userInfo;
	}

	/**
	 * 
	 * @param telephone
	 * @param password
	 * @param nickname
	 * @param state
	 * @return contains uid if signup successfully; -1 otherwise
	 */
	public static Uid signup(String telephone, String password, String nickname, String state) {
		
		Account account = new Account();
		account.setTelephone(telephone);
		account.setPassword(password);
		account.setNickname(nickname);
		account.setState(state);
		account.setRegTime(new Timestamp(System.currentTimeMillis()));
		
		long uid = DBUtil.signup(account);
		
		return new Uid(uid);
	}
	
	/**
	 * 
	 * @param uid
	 * @param nickname
	 * @param state
	 * @param sex
	 * @param birthday
	 * @param selfIntroduction
	 * @return updated user information if success; null otherwise
	 */
	public static UserInfo updateUserInfo(long uid, String nickname, String state, String sex, Timestamp birthday, String selfIntroduction) {
		
		Account account = new Account();
		account.setUserId(uid);
		account.setNickname(nickname);
		account.setState(state);
		account.setSex(sex);
		account.setBirthday(birthday);
		account.setSelfIntroduction(selfIntroduction);
		
		DBUtil.updateUserInfo(account);
		
		return getUserInfo(uid);
	}
	
	/**
	 * 
	 * @param telephone
	 * @param newPassword
	 */
	public static void resetLoginPwd(String telephone, String newPassword) {
		
		DBUtil.resetLoginPwd(telephone, newPassword);
		
		return;
	}
	
	/**
	 * Get all the cities' information
	 * @return
	 */
	public static CityList getCityList() {
		
		ArrayList<City> list = DBUtil.getCityList();
		
		CityList cityList = new CityList();
		cityList.setCityList(list);
		
		return cityList;
	}
	
	/**
	 * Get all the sites that belong to cityId
	 * @param cityId
	 * @param size
	 * @param offset
	 * @return
	 */
	public static SiteIdList getSiteList(long cityId, long size, long offset) {
		
		ArrayList<Long> list = DBUtil.getSiteList(cityId, size, offset);
		
		SiteIdList siteList = new SiteIdList();
		siteList.setSiteIdList(list);
		
		return siteList;
	}
	
	/**
	 *
	 * @param siteIds
	 * @return sites' information in the siteIds
	 */
	public static SiteInfoList getSitesInfo(long[] siteIds) {
		
		SiteInfoList siteInfoList = new SiteInfoList();
		for	(int i = 0; i < siteIds.length; i++) {
			Site site = DBUtil.getSiteInfo(siteIds[i]);
			if (site == null) {
				continue;
			}
			siteInfoList.addSiteInfo(site);
		}
		
		return siteInfoList;
	}
	
	/**
	 * 
	 * @param minisiteIds
	 * @return minisites' information in the minisiteIds
	 */
	public static MinisiteInfoList getMinisitesInfo(long[] minisiteIds) {
		
		MinisiteInfoList minisiteInfoList = new MinisiteInfoList();
		for	(int i = 0; i < minisiteIds.length; i++) {
			Minisite minisite = DBUtil.getMinisiteInfo(minisiteIds[i]);
			if (minisite == null) {
				continue;
			}
			minisiteInfoList.addMinisiteInfo(minisite);
		}
		
		return minisiteInfoList;
	}
	
	/**
	 * 
	 * @param siteId
	 * @param uid
	 * @return true if already exists; false otherwise
	 */
	public static boolean checkPlan(long siteId, long uid) {
		
		boolean ret = DBUtil.checkPlan(siteId, uid);
		
		return ret;
	}
	
	/**
	 * 
	 * @param minisiteId
	 * @param uid
	 * @return true if already exists; false otherwise
	 */
	public static boolean checkMinisiteCollect(long minisiteId, long uid) {
		
		boolean ret = DBUtil.checkMinisiteCollect(minisiteId, uid);
		
		return ret;
	}
	
	/**
	 * 
	 * @param siteId
	 * @param uid
	 * @return 1 if successful; -1 otherwise
	 */
	public static int addPlan(long siteId, long uid) {
		
		int ret = DBUtil.addPlan(siteId, uid);
		
		return ret;
	}
	
	/**
	 * 
	 * @param siteId
	 * @param uid
	 * @return 1 if successful; -1 otherwise
	 */
	public static int deletePlan(long siteId, long uid) {
		
		int ret = DBUtil.deletePlan(siteId, uid);
		
		return ret;
	}
	
	/**
	 * 
	 * @param minisiteId
	 * @param uid
	 * @return 1 if successful; -1 otherwise
	 */
	public static int addMinisiteCollect(long minisiteId, long uid) {
		
		int ret = DBUtil.addMinisiteCollect(minisiteId, uid);
		
		return ret;
	}
	
	/**
	 * 
	 * @param minisiteId
	 * @param uid
	 * @return 1 if successful; -1 otherwise
	 */
	public static int deleteMinisiteCollect(long minisiteId, long uid) {
		
		int ret = DBUtil.deleteMinisiteCollect(minisiteId, uid);
		
		return ret;
	}
	
	/**
	 * 
	 * @param siteId
	 * @param size
	 * @param offset
	 * @return site comments start from offset
	 */
	public static SiteCommentList getSiteComments(long siteId, long size, long offset) {
				
		SiteCommentList siteCommentList = new SiteCommentList();
		
		ArrayList<CommentForSite> list = DBUtil.getSiteComments(siteId, size, offset);
		for (CommentForSite comment : list) {
			siteCommentList.addSiteCommnet(comment);
		}
		
		return siteCommentList;
	}
	
	/**
	 * 
	 * @param minisiteId
	 * @param size
	 * @param offset
	 * @return minisite comments start offset
	 */
	public static MinisiteCommentList getMinisiteComments(long minisiteId, long size, long offset) {
		
		MinisiteCommentList minisiteCommentList = new MinisiteCommentList();
		
		ArrayList<CommentForMinisite> list = DBUtil.getMinisiteComments(minisiteId, size, offset);
		for (CommentForMinisite comment : list) {
			minisiteCommentList.addMinisiteComment(comment);
		}
		
		return minisiteCommentList;
	}
	
	/**
	 * 
	 * @param uid
	 * @return plan list of the user
	 */
	public static SiteIdList getPlanList(long uid) {
		
		ArrayList<Long> list = DBUtil.getPlanList(uid);
		SiteIdList siteList = new SiteIdList();
		siteList.setSiteIdList(list);
		
		return siteList;
	}
	
	/**
	 * 
	 * @param uid
	 * @return minisite collect of the user
	 */
	public static MinisiteIdList getMinisiteCollectList(long uid) {
		
		ArrayList<Long> list = DBUtil.getMinisiteCollectList(uid);
		
		MinisiteIdList minisiteList = new MinisiteIdList();
		minisiteList.setMinisiteIdList(list);
		
		return minisiteList;
	}
	
	/**
	 * 
	 * @param minisiteId
	 * @param desc
	 * @param uid
	 * @param picPath
	 * @return 1 if successful; -1 otherwise
	 */
	public static int commentMinisite(long minisiteId, String content, long uid, ArrayList<String> picPath) {
		
		int ret = DBUtil.commentMinisite(minisiteId, content, uid, new Timestamp(System.currentTimeMillis()), picPath);
		
		return ret;
	}
	
	/**
	 * 
	 * @param siteId
	 * @param content
	 * @param score
	 * @param uid
	 * @param commentTime
	 * @param picPath
	 * @return 1 if successful; -1 otherwise
	 */
	public static int commentSite(long siteId, String content, int score, long uid, ArrayList<String> picPath) {
		
		int ret = DBUtil.commentSite(siteId, content, score, uid, new Timestamp(System.currentTimeMillis()), picPath);
		
		return ret;
	}
	
	/**
	 * 
	 * @param siteId
	 * @return picture path list of the site
	 */
	public static PicturePathList GetPictureForSiteBySiteId(long siteId) {
		
		PicturePathList picturePathList = new PicturePathList();
		
		ArrayList<String> list = DBUtil.getPictureForSiteBySiteId(siteId);
		for (String path : list) {
			picturePathList.addPath(path);
		}
		
		return picturePathList;
	}
	
	/**
	 * 
	 * @param minisiteId
	 * @return picture path list of the minisite
	 */
	public static PicturePathList GetPictureForMinisiteByMinisiteId(long minisiteId) {
		
		PicturePathList picturePathList = new PicturePathList();
		
		ArrayList<String> list = DBUtil.getPictureForMinisiteByMinisiteId(minisiteId);
		for (String path : list) {
			picturePathList.addPath(path);
		}
		
		return picturePathList;
	}
}
