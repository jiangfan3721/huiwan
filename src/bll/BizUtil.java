package bll;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import api.ret.obj.CityList;
import api.ret.obj.MinisiteComment;
import api.ret.obj.MinisiteCommentList;
import api.ret.obj.MinisiteInfo;
import api.ret.obj.MinisiteInfoList;
import api.ret.obj.MinisiteList;
import api.ret.obj.SiteComment;
import api.ret.obj.SiteCommentList;
import api.ret.obj.SiteInfo;
import api.ret.obj.SiteInfoList;
import api.ret.obj.SiteList;
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
	
	public static Uid userLogin(String telephone, String loginPassword) {
		
		long uid = DBUtil.userLogin(telephone, loginPassword);
		
		return new Uid(uid);
	}
	
	public static UserInfo getUserInfo(long uid) {
		
		Account account = DBUtil.getUserInfo(uid);
		
		UserInfo userInfo = null;
		if (account != null) {
			userInfo = new UserInfo(account.getUserId(), account.getTelephone(), 
					account.getNickname(), account.getUserIcon(), account.getRegTime(),
					account.getSex(), account.getState());
		}
		
		return userInfo;
	}

	public static Uid signup(String telephone, String password, String nickname, String state) {

		if (DBUtil.checkUserExist(telephone)) {
			return new Uid(-1);
		}
		
		Account account = new Account();
		account.setTelephone(telephone);
		account.setPassword(password);
		account.setNickname(nickname);
		account.setState(state);
		account.setRegTime((new Date()).getTime());
		
		long uid = DBUtil.signup(account);
		
		return new Uid(uid);
	}
	
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
	
	public static void resetLoginPwd(String telephone, String newPassword) {
		
		DBUtil.resetLoginPwd(telephone, newPassword);
		
		return;
	}
	
	public static CityList getCityList() {
		
		ArrayList<City> list = DBUtil.getCityList();
		
		CityList cityList = new CityList();
		cityList.setCityList(list);
		
		return cityList;
	}
	
	public static SiteList getSiteList(long cityId, long size, long offset) {
		
		ArrayList<Long> list = DBUtil.getSiteList(cityId, size, offset);
		
		SiteList siteList = new SiteList();
		siteList.setSiteIdList(list);
		
		return siteList;
	}
	
	public static SiteInfoList getSitesInfo(long[] siteIds) {
		
		SiteInfoList siteInfoList = new SiteInfoList();
		for	(int i = 0; i < siteIds.length; i++) {
			Site site = DBUtil.getSiteInfo(siteIds[i]);
			if (site == null) {
				continue;
			}
			siteInfoList.addSiteInfo(new SiteInfo(site.getName(), site.getLogoPath(), 
					site.getAddress(), site.getPicPath(), site.getRate(),
					site.getCoorsX(), site.getCoorsY(), site.getKeyValue(), site.getDescPath()));
		}
		
		return siteInfoList;
	}
	
	public static MinisiteInfoList getMinisitesInfo(long[] minisiteIds) {
		
		MinisiteInfoList minisiteInfoList = new MinisiteInfoList();
		for	(int i = 0; i < minisiteIds.length; i++) {
			Minisite minisite = DBUtil.getMinisiteInfo(minisiteIds[i]);
			if (minisite == null) {
				continue;
			}
			minisiteInfoList.addMinisiteInfo(new MinisiteInfo(minisite.getName(),
					minisite.getLogoPath(), minisite.getAddress(), minisite.getPicPath(),
					minisite.getDescPath(), minisite.getCoorsX(), minisite.getCoorsY(),
					minisite.getKeyValue(), minisite.getMusicPath(), minisite.getIrcPath()));
		}
		
		return minisiteInfoList;
	}
	
	public static int addPlan(long siteId, long uid) {
		
		int ret = DBUtil.addPlan(siteId, uid, new Timestamp(System.currentTimeMillis()));
		
		return ret;
	}
	
	public static int deletePlan(long siteId, long uid) {
		
		int ret = DBUtil.deletePlan(siteId, uid);
		
		return ret;
	}
	
	public static int addMinisiteCollect(long minisiteId, long uid) {
		
		int ret = DBUtil.addMinisiteCollect(minisiteId, uid);
		
		return ret;
	}
	
	public static int deleteMinisiteCollect(long minisiteId, long uid) {
		
		int ret = DBUtil.deleteMinisiteCollect(minisiteId, uid);
		
		return ret;
	}
	
	public static SiteCommentList getSiteComments(long siteId, long size, long offset) {
		
		SiteCommentList siteCommentList = new SiteCommentList();
		
		ArrayList<CommentForSite> list = DBUtil.getSiteComments(siteId, size, offset);
		for (CommentForSite comment : list) {
			siteCommentList.addSiteCommnet(new SiteComment(comment.getAccountUserId(),
					comment.getNickName(), comment.getContent(), comment.getScore(),
					comment.getPicPath(), comment.getTime()));
		}
		
		return siteCommentList;
	}
	
	public static MinisiteCommentList getMinisiteComments(long minisiteId, long size, long offset) {
		
		MinisiteCommentList minisiteCommentList = new MinisiteCommentList();
		
		ArrayList<CommentForMinisite> list = DBUtil.getMinisiteComments(minisiteId, size, offset);
		for (CommentForMinisite comment : list) {
			minisiteCommentList.addMinisiteComment(new MinisiteComment(comment.getAccountUserId(),
					comment.getNickName(), comment.getContent(), comment.getTime()));
		}
		
		return minisiteCommentList;
	}
	
	public static SiteList getPlanList(long uid, Timestamp startTime, Timestamp endTime) {
		
		ArrayList<Long> list = DBUtil.getPlanList(uid, startTime, endTime);
		SiteList siteList = new SiteList();
		siteList.setSiteIdList(list);
		
		return siteList;
	}
	
	public static MinisiteList getMinisiteCollectList(long uid) {
		
		ArrayList<Long> list = DBUtil.getMinisiteCollectList(uid);
		
		MinisiteList minisiteList = new MinisiteList();
		minisiteList.setMinisiteIdList(list);
		
		return minisiteList;
	}
	
	public static int comment(long minisiteId, String desc, long uid) {
		
		int ret = DBUtil.comment(minisiteId, desc, uid, new Timestamp(System.currentTimeMillis()));
		
		return ret;
	}
}
