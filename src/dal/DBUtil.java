package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import entity.*;

public class DBUtil {

	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName(DBConf.DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("No driver named " + DBConf.DRIVER + " found.");
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(DBConf.URL, DBConf.USERNAME, DBConf.PWD);
		} catch (SQLException e) {
			System.out.println("Cannot connect to " + DBConf.URL);
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException sqlEx) {
				// ignore
			}
		}
		
		if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException sqlEx) {
	        	// ignore
	        }

	        rs = null;
	    }

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) {
	        	// ignore
	        }

	        stmt = null;
	    }
	    
	}
	
	/**
	 * Login
	 * @param telephone string
	 * @param loginPassword string
	 * @return long; uid if success; -1 if fail
	 */
	public static long userLogin(String telephone, String loginPassword){
		
		PreparedStatement stmt = null;
		
		Connection conn = DBUtil.getConnection();
		String sql = "select user_id from account "+
				"where telephone = ? and password = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, telephone);
			stmt.setString(2, loginPassword);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()){
				long t = rs.getLong(1);
				DBUtil.close(conn, stmt, rs);
				return t;
			}
			else {
				DBUtil.close(conn, stmt, rs);
				return -1;
			}
		} catch (SQLException e) {
			System.out.println("Something error in UserLogin");
		}
		return -1;
	}
	
	/**
	 * checkUserExist
	 * @param telephone string
	 * @return Bool; true if exist
	 */
	public static boolean checkUserExist(String telephone) {
		
		PreparedStatement stmt = null;
		
		Connection conn = DBUtil.getConnection();
		String sql = "select user_id from account "+
				"where telephone = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, telephone);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()){
				DBUtil.close(conn, stmt, rs);
				return true;
			}
			else {
				DBUtil.close(conn, stmt, rs);
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Something error in checkUserExist");
		}
		return false;
	}
	
	/**
	 * getUserInfo
	 * @param uid string; user id
	 * @return Account if success; null if fail
	 */
	public static Account getUserInfo(long uid) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select user_id, real_name, telephone, nickname, user_icon, reg_time, sex, state, birthday, self_introduction, background "+
				"from account where user_id = ?;";
		
		Account result = new Account();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, uid);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result.setUserId(rs.getLong(1));
				result.setRealName(rs.getString(2));
				result.setTelephone(rs.getString(3));
				result.setNickname(rs.getString(4));
				result.setUserIcon(rs.getString(5));
				result.setRegTime(rs.getTimestamp(6));
				result.setSex(rs.getString(7));
				result.setState(rs.getString(8));
				result.setBirthday(rs.getTimestamp(9));
				result.setSelfIntroduction(rs.getString(10));
				result.setBackground(rs.getString(11));				
			}
			else{
				result = null;
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in GetUserInfo");
		}
		return result;
	}
	
	/**
	 * signup
	 * @param account Account; user information for register
	 * @return long; uid if success; -1 if fail
	 */
	public static long signup(Account account) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO account(telephone, password, nickname, reg_time, state) "
				+ "VALUES(?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getTelephone());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getNickname());
			stmt.setTimestamp(4, account.getRegTime());
			stmt.setString(5, account.getState());
			
			stmt.executeUpdate();
			
			DBUtil.close(conn, stmt, null);
		} catch (SQLException e) {
			System.out.println("Something error in signup insert");
		}
		
		long uid = DBUtil.userLogin(account.getTelephone(), account.getPassword());
		if (uid == -1){
			System.out.println("Something error in signup getuid");
		}
		return uid;
	}
	
	/**
	 * updateUserInformation
	 * @param account Account; user new information
	 * @return Account; new information if success; null if fail ???what is new information
	 */
	public static void updateUserInfo(Account account){
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "update account "+
				"set nickname = ? , state = ? , sex = ? , birthday = ? , self_introduction = ?, user_icon = ?, background = ? "+
				"where user_id = ?;";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getNickname());
			stmt.setString(2, account.getState());
			stmt.setString(3, account.getSex());
			stmt.setTimestamp(4, account.getBirthday());
			stmt.setString(5, account.getSelfIntroduction());
			stmt.setString(6, account.getUserIcon());
			stmt.setString(7, account.getBackground());
			stmt.setLong(8, account.getUserId());
			
			stmt.executeUpdate();
			
			DBUtil.close(conn, stmt, null);
		} catch (SQLException e) {
			System.out.println("Something error in updateUserInfo");
		}
	}
	
	/**
	 * reset login password
	 * @param telephone String; user telephone
	 * @param newPassword String; new password
	 * @return void
	 */
	public static void resetLoginPwd(String telephone, String newPassword){
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "update account "+
				"set password = ? "+
				"where telephone = ?;";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newPassword);
			stmt.setString(2, telephone);
			
			stmt.executeUpdate();
			
			DBUtil.close(conn, stmt, null);
		} catch (SQLException e) {
			System.out.println("Something error in resetLoginPwd");
		}
	}
	
//	public static ArrayList<City> getCityList() {
//		
//		PreparedStatement stmt = null;
//        
//		Connection conn = DBUtil.getConnection();
//		String sql = "select city_id, name, city_sketch, city_information, english_name, pic_path from city;";
//		
//		ArrayList<City> result = new ArrayList<City>();
//		
//		try {
//			stmt = conn.prepareStatement(sql);
//			
//			ResultSet rs = stmt.executeQuery();
//			
//			while(rs.next()) {
//				City t = new City();
//				t.setCity_id(rs.getLong(1));
//				t.setName(rs.getString(2));
//				t.setCitySketch(rs.getString(3));
//				t.setCityInformation(rs.getString(4));
//				t.setEnglishName(rs.getString(5));
//				t.setPicPath(rs.getString(6));
//				
//				result.add(t);
//			}
//			
//			DBUtil.close(conn, stmt, rs);
//			
//		} catch (SQLException e) {
//			System.out.println("Something error in getCityList");
//		}
//		return result;
//	}
	
	/**
	 * Get City List
	 * @param size long
	 * @param offset long
	 * @return long ArrayList
	 */
	public static ArrayList<Long> getCityList(long size, long offset) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select city_id from city " +
				"limit ?, ?;";
		
		ArrayList<Long> result = new ArrayList<Long>();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, offset);
			stmt.setLong(2, size);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				result.add(rs.getLong(1));
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getCityList");
		}
		return result;
	}
	
	/**
	 * getCityInfo
	 * @param cityId
	 * @return City; City information if success; null if fail
	 */
	public static City getCityInfo(long cityId){

		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select name, city_sketch, city_information, english_name, pic_path "+
				"from city "+
				"where city_id = ?;";
		
		City result = new City();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, cityId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result.setName(rs.getString(1));
				result.setCitySketch(rs.getString(2));
				result.setCityInformation(rs.getString(3));
				result.setEnglishName(rs.getString(4));
				result.setPicPath(rs.getString(5));
				result.setCity_id(cityId);
			}
			else{
				result = null;
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in GetCityInfo");
		}
		return result;
	}
	
	/**
	 * Get Site List in city_id
	 * @param cityId
	 * @param size
	 * @param offset
	 * @return ArrayList of Long which contains all site_id in city_id
	 */
	public static ArrayList<Long> getSiteList(long cityId, long size, long offset) {

		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select site_id from site "+
					"where City_city_id = ? " +
					"limit ?, ?;";
		
		ArrayList<Long> result = new ArrayList<Long>();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, cityId);
			stmt.setLong(2, offset);
			stmt.setLong(3, size);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result.add(rs.getLong(1));
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getSiteList");
		}
		return result;
	}
	
	/**
	 * 
	 * @param siteId
	 * @param size
	 * @param offset
	 * @return ArrayList of Long which contains all minisiteId in siteId
	 */
	public static ArrayList<Long> getMinisiteList(long siteId, long size, long offset) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select Minisite_id from minisite "+
					"where Site_site_id = ? " +
					"limit ?, ?;";
		
		ArrayList<Long> result = new ArrayList<Long>();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, siteId);
			stmt.setLong(2, offset);
			stmt.setLong(3, size);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result.add(rs.getLong(1));
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getMinisiteList");
		}
		return result;
	}
	
	/**
	 * getSitesInfo
	 * @param siteId
	 * @return Site; Site information if success; null if fail
	 */
	public static Site getSiteInfo(long siteId){
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select name, logo_path, address, rate, coors_x, coors_y, `desc`, key_value, english_name, City_city_id, img_path "+
				"from site "+
				"where site_id = ?;";
		
		Site result = new Site();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, siteId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result.setName(rs.getString(1));
				result.setLogoPath(rs.getString(2));
				result.setAddress(rs.getString(3));
				result.setRate(rs.getString(4));
				result.setCoorsX(rs.getFloat(5));
				result.setCoorsY(rs.getFloat(6));
				result.setDesc(rs.getString(7));
				result.setKeyValue(rs.getString(8));
				result.setEnglishName(rs.getString(9));
				result.setCityId(rs.getLong(10));
				result.setImgPath(rs.getString(11));
				result.setSiteId(siteId);
			}
			else{
				result = null;
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in GetSitesInfo");
		}
		return result;
	}
	
	/**
	 * getMinisitesInfo
	 * @param minisiteId
	 * @return Minisite; Minisite information if success; null if fail
	 */
	public static Minisite getMinisiteInfo(long minisiteId){
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select name, logo_path, address, coors_x, coors_y, `desc`, key_value, music_path, lrc_path, english_name, Site_site_id "+
				"from minisite "+
				"where minisite_id = ?;";
		
		Minisite result = new Minisite();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, minisiteId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result.setName(rs.getString(1));
				result.setLogoPath(rs.getString(2));
				result.setAddress(rs.getString(3));
				result.setCoorsX(rs.getFloat(4));
				result.setCoorsY(rs.getFloat(5));
				result.setDesc(rs.getString(6));
				result.setKeyValue(rs.getString(7));
				result.setMusicPath(rs.getString(8));
				result.setIrcPath(rs.getString(9));
				result.setEnglishName(rs.getString(10));
				result.setSiteId(rs.getLong(11));
				result.setMinisiteId(minisiteId);
			}
			else{
				result = null;
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in GetMiniSitesInfo");
		}
		return result;
	}
	
	/**
	 * addPlan
	 * @param siteId site id
	 * @param uid user id
	 * @return int; 1 if success; -1 if fail
	 */
	public static int addPlan(long siteId, long uid) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO plan(Account_user_id, Site_site_id) "
				+ "VALUES(?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, uid);
			stmt.setLong(2, siteId);
			
			stmt.executeUpdate();
			
			DBUtil.close(conn, stmt, null);
		} catch (SQLException e) {
			System.out.println("Something error in addPlan");
			return -1;
		}
		return 1;
	}
	
	/**
	 * DeletePlan
	 * @param siteId site id
	 * @param uid user id
	 * @return int; 1 if success; -1 if fail
	 */
	public static int deletePlan(long siteId, long uid) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "delete from plan "+
					"where Account_user_id = ? and Site_site_id = ?;";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, uid);
			stmt.setLong(2, siteId);
			
			stmt.executeUpdate();
			
			DBUtil.close(conn, stmt, null);
		} catch (SQLException e) {
			System.out.println("Something error in deletePlan");
			return -1;
		}
		return 1;
	}
	
	/**
	 * addMinisiteCollect
	 * @param minisiteId minisite id
	 * @param uid user id
	 * @return int; 1 if success; -1 if fail
	 */
	public static int addMinisiteCollect(long minisiteId, long uid) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO favoriteminisite(Account_user_id, Minisite_minisite_id) "
				+ "VALUES(?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, uid);
			stmt.setLong(2, minisiteId);
			
			stmt.executeUpdate();
			
			DBUtil.close(conn, stmt, null);
		} catch (SQLException e) {
			System.out.println("Something error in addMinisiteCollect");
			return -1;
		}
		return 1;
	}
	
	/**
	 * deleteMinisiteCollect
	 * @param minisiteId minisite id
	 * @param uid user id
	 * @return int; 1 if success; -1 if fail
	 */
	public static int deleteMinisiteCollect(long minisiteId, long uid) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "delete from favoriteminisite "+
				"where Account_user_id = ? and Minisite_minisite_id = ?;";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, uid);
			stmt.setLong(2, minisiteId);
			
			stmt.executeUpdate();
			
			DBUtil.close(conn, stmt, null);
		} catch (SQLException e) {
			System.out.println("Something error in deleteMinisiteCollect");
			return -1;
		}
		return 1;
	}
	
	/**
	 * comments about site
	 * @param siteId site_id
	 * @param size String; size of ArrayList
	 * @param offset String;
	 * @return ArrayList of CommentsForSite
	 */
	public static ArrayList<CommentForSite> getSiteComments(long siteId, long size, long offset) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select Account_user_id, time, score, content, nickname, user_icon, commentsForSite_id "+ 
					"from commentsforsite, account "+
					"where commentsforsite.Account_user_id = account.user_id and "+
					"commentsforsite.Site_site_id = ? "+
					"limit ?, ?;";
		
		ArrayList<CommentForSite> result = new ArrayList<CommentForSite>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, siteId);
			stmt.setLong(2, offset);
			stmt.setLong(3, size);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				CommentForSite t = new CommentForSite();
				t.setAccountUserId(rs.getLong(1));
				t.setTime(rs.getTimestamp(2));
				t.setScore(rs.getInt(3));
				t.setContent(rs.getString(4));
				t.setNickname(rs.getString(5));
				t.setUserIcon(rs.getString(6));
				t.setCommentsForSiteId(rs.getLong(7));
				t.setSiteId(siteId);
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in GetSiteComments");
		}
		
		for (int i = 0; i < result.size(); i++) {
			result.get(i).setPic(getPictureForSiteByCommentId(result.get(i).getCommentsForSiteId()));
		}
		
		return result;
	}
	
	/**
	 * addPictureForSite
	 * @param pic_path String
	 * @param commentId long
	 * @param userId long
	 * @param siteId long
	 * @return int; 1 if success; -1 if fail
	 */
	public static int addPictureForSite(String pic_path, long commentId, long userId, long siteId, float size, Timestamp photoTime) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO pictureforcommentsforsite(picture_path, CommentsForSite_commentsForSite_id, CommentsForSite_Account_user_id, CommentsForSite_Site_site_id, size, time) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pic_path);
			stmt.setLong(2, commentId);
			stmt.setLong(3, userId);
			stmt.setLong(4, siteId);
			stmt.setFloat(5, size);
			stmt.setTimestamp(6, photoTime);
			
			System.out.println(stmt.toString());
			stmt.executeUpdate();
			
			DBUtil.close(conn, stmt, null);
		} catch (SQLException e) {
			System.out.println("Something error in addPictureForSite");
			return -1;
		}
		return 1;
	}

	
	/**
	 * getPictureForSiteByCommentId
	 * @param commentId comment_id
	 * @return ArrayList of PictureForSite
	 */
	public static ArrayList<PictureForSite> getPictureForSiteByCommentId(long commentId) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select picture_path, size, time, pictureForCommentsForSite_id, CommentsForSite_Account_user_id, CommentsForSite_Site_site_id "+ 
					"from pictureforcommentsforsite "+
					"where CommentsForSite_commentsForSite_id = ?; ";
		
		ArrayList<PictureForSite> result = new ArrayList<PictureForSite>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, commentId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				PictureForSite t = new PictureForSite();
				
				t.setPicPath(rs.getString(1));
				t.setSize(rs.getFloat(2));
				t.setTime(rs.getTimestamp(3));
				t.setPictureForSiteId(rs.getLong(4));
				t.setUserId(rs.getLong(5));
				t.setSiteId(rs.getLong(6));
				t.setCommentForSiteId(commentId);
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getPictureForSiteByCommentId");
		}
		
		
		return result;
	}
	
	/**
	 * getPictureForSiteByUserId
	 * @param userId user_id
	 * @return ArrayList of PictureForSite for picture path
	 */
	public static ArrayList<PictureForSite> getPictureForSiteByUserId(long userId) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select picture_path, size, time "+ 
					"from pictureforcommentsforsite "+
					"where CommentsForSite_Account_user_id = ?; ";
		
		ArrayList<PictureForSite> result = new ArrayList<PictureForSite>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				PictureForSite t = new PictureForSite();
				
				t.setPicPath(rs.getString(1));
				t.setSize(rs.getFloat(2));
				t.setTime(rs.getTimestamp(3));
				t.setUserId(userId);
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getPictureForSiteByUserId");
		}
		
		
		return result;
	}
	
	/**
	 * getPictureForSiteBySiteId
	 * @param siteId site_id
	 * @return ArrayList of PictureForSite for picture path
	 */
	public static ArrayList<PictureForSite> getPictureForSiteBySiteId(Long siteId) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select picture_path, size, time "+ 
					"from pictureforcommentsforsite "+
					"where CommentsForSite_Site_site_id = ?; ";
		
		ArrayList<PictureForSite> result = new ArrayList<PictureForSite>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, siteId);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				PictureForSite t = new PictureForSite();
				
				t.setPicPath(rs.getString(1));
				t.setSize(rs.getFloat(2));
				t.setTime(rs.getTimestamp(3));
				t.setSiteId(siteId);
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getPictureForSiteBySiteId");
		}
		
		
		return result;
	}
	
	/**
	 * comments about site
	 * @param minisiteId minisite_id
	 * @param size String; size of ArrayList
	 * @param offset String;
	 * @return ArrayList of CommentsForSite
	 */
	public static ArrayList<CommentForMinisite> getMinisiteComments(long minisiteId, long size, long offset) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select Account_user_id, time, content, nickname, user_icon, commentsForMinisite_id "+ 
				"from commentsforminisite, account "+
				"where commentsforminisite.Account_user_id = account.user_id "+
				"and commentsforminisite.Minisite_minisite_id = ? "+
				"limit ?, ?;";
		
		ArrayList<CommentForMinisite> result = new ArrayList<CommentForMinisite>();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, minisiteId);
			stmt.setLong(2, offset);
			stmt.setLong(3, size);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				CommentForMinisite t = new CommentForMinisite();
				t.setAccountUserId(rs.getLong(1));
				t.setTime(rs.getTimestamp(2));
				t.setContent(rs.getString(3));
				t.setNickname(rs.getString(4));
				t.setUserIcon(rs.getString(5));
				t.setCommentsForMinisiteId(rs.getLong(6));
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
		} catch (SQLException e) {
			System.out.println("Something error in getMinisiteComments");
		}
		
		for (int i = 0; i < result.size(); i++) {
			result.get(i).setPic(getPictureForMinisiteByCommentId(result.get(i).getCommentsForMinisiteId()));
		}
		
		return result;
	}
	
	/**
	 * addPictureForMinisite
	 * @param pic_path String
	 * @param commentId long
	 * @param userId long
	 * @param minisiteId long
	 * @return int; 1 if success; -1 if fail
	 */
	public static int addPictureForMinisite(String pic_path, long commentId, long userId, long minisiteId, float size, Timestamp photoTime) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO pictureforcommentsforminisite(picture_path, CommentsForMinisite_commentsForMinisite_id, CommentsForMinisite_Account_user_id, CommentsForMinisite_Minisite_minisite_id, size, time) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pic_path);
			stmt.setLong(2, commentId);
			stmt.setLong(3, userId);
			stmt.setLong(4, minisiteId);
			stmt.setFloat(5, size);
			stmt.setTimestamp(6, photoTime);
			
			stmt.executeUpdate();
			
			DBUtil.close(conn, stmt, null);
		} catch (SQLException e) {
			System.out.println("Something error in addPictureForMinisite");
			return -1;
		}
		return 1;
	}
	
	/**
	 * getPictureForMinisiteByCommentId
	 * @param commentId comment_id
	 * @return ArrayList of PictureForMinisite
	 */
	public static ArrayList<PictureForMinisite> getPictureForMinisiteByCommentId(long commentId) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select picture_path, size, time "+ 
					"from pictureforcommentsforminisite "+
					"where CommentsForMinisite_commentsForMinisite_id = ?; ";
		
		ArrayList<PictureForMinisite> result = new ArrayList<PictureForMinisite>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, commentId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				PictureForMinisite t = new PictureForMinisite();
				
				t.setPicPath(rs.getString(1));
				t.setSize(rs.getFloat(2));
				t.setTime(rs.getTimestamp(3));
				t.setCommentForMinisiteId(commentId);
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getPictureForMinisiteByCommentId");
		}
		
		return result;
	}
	
	/**
	 * getPictureForMinisiteByUserId
	 * @param userId user_id
	 * @return ArrayList of PictureForMinisite
	 */
	public static ArrayList<PictureForMinisite> getPictureForMinisiteByUserId(Long userId) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select picture_path, size, time "+ 
					"from pictureforcommentsforminisite "+
					"where CommentsForMinisite_Account_user_id = ?; ";
		
		ArrayList<PictureForMinisite> result = new ArrayList<PictureForMinisite>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				PictureForMinisite t = new PictureForMinisite();
				
				t.setPicPath(rs.getString(1));
				t.setSize(rs.getFloat(2));
				t.setTime(rs.getTimestamp(3));
				t.setUserId(userId);
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getPictureForMinisiteByUserId");
		}
		
		return result;
	}
	
	/**
	 * getPictureForMinisiteByMinisiteId
	 * @param siteId site_id
	 * @return ArrayList of PictureForMinisite
	 */
	public static ArrayList<PictureForMinisite> getPictureForMinisiteByMinisiteId(Long minisiteId) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select picture_path, size, time "+ 
					"from pictureforcommentsforminisite "+
					"where CommentsForMinisite_Minisite_minisite_id = ?; ";
		
		ArrayList<PictureForMinisite> result = new ArrayList<PictureForMinisite>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, minisiteId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				PictureForMinisite t = new PictureForMinisite();
				
				t.setPicPath(rs.getString(1));
				t.setSize(rs.getFloat(2));
				t.setTime(rs.getTimestamp(3));
				t.setMinisiteId(minisiteId);
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getPictureForMinisiteByMinisiteId");
		}
		
		
		return result;
	}
	
	/**
	 * Get plan List about user_id
	 * @param userId String; user_id
	 * @return ArrayList of Long which contains all plan site id about user_id
	 */
	public static ArrayList<Long> getPlanList(long userId){

		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT Site_site_id FROM plan "+
				"where Account_user_id = ? ;";
		
		ArrayList<Long> result = new ArrayList<Long>();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result.add(rs.getLong(1));
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getPlanList");
		}
		return result;
	}
	
	/**
	 * Get Favorite minisite List about user_id
	 * @param userId user_id
	 * @return ArrayList of Long which contains all favorite minisite id about user_id
	 */
	public static ArrayList<Long> getMinisiteCollectList(long userId){

		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select Minisite_minisite_id from favoriteminisite "+
				"where Account_user_id = ?;";
		
		ArrayList<Long> result = new ArrayList<Long>();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result.add(rs.getLong(1));
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getMinisiteCollectList");
		}
		return result;
	}
	
	/**
	 * commentMinisite
	 * @param minisiteId String
	 * @param content String; content
	 * @param uid String; user id
	 * @param commentTime String;
	 * @param pic ArrayList<PictureForMinisite>
	 * @return int; 1 if success; -1 if fail
	 */
	public static int commentMinisite(long minisiteId, String content, long uid, Timestamp commentTime, ArrayList<PictureForMinisite> pic) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO commentsforminisite(Account_user_id, Minisite_minisite_id, content, time) "
				+ "VALUES(?, ?, ?, ?)";
		
		long commentId = -1;
		
		try {
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, uid);
			stmt.setLong(2, minisiteId);
			stmt.setString(3, content);
			stmt.setTimestamp(4, commentTime);
			
			stmt.executeUpdate();
			//ResultSet rs = stmt.executeQuery();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()){
				commentId = rs.getLong(1);
			} else {
				System.out.println("Something error in commentMinisite. Maybe @@IDENTITY didn't fetch a identity to commentId");
				return -1;
			}
			
			DBUtil.close(conn, stmt, rs);
		} catch (SQLException e) {
			System.out.println("Something error in commentMinisite");
			return -1;
		}
		
		for (int i = 0; i < pic.size(); i++)
		{
			DBUtil.addPictureForMinisite(pic.get(i).getPicPath(), commentId, uid, minisiteId, pic.get(i).getSize(), pic.get(i).getTime());
		}
		
		return 1;
	}
	
	/**
	 * commentSite
	 * @param siteId String
	 * @param content String; content
	 * @param score int;
	 * @param uid String; user id
	 * @param commentTime String;
	 * @param pic ArrayList<PictureForSite>
	 * @return int; 1 if success; -1 if fail
	 */
	public static int commentSite(long siteId, String content, int score, long uid, Timestamp commentTime, ArrayList<PictureForSite> pic) {
		
		PreparedStatement stmt = null;
		        
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO commentsforsite(Account_user_id, Site_site_id, content, time, score) "
				+ "VALUES(?, ?, ?, ?, ?)";
		
		long commentId = -1;
		
		try {
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, uid);
			stmt.setLong(2, siteId);
			stmt.setString(3, content);
			stmt.setTimestamp(4, commentTime);
			stmt.setInt(5, score);
						
			stmt.executeUpdate();
			//ResultSet rs = stmt.executeQuery();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()){
				commentId = rs.getLong(1);
			} else {
				System.out.println("Something error in commentSite. Maybe @@IDENTITY didn't fetch a identity to commentId");
				return -1;
			}
			
			DBUtil.close(conn, stmt, rs);
		} catch (SQLException e) {
			System.out.println("Something error in commentSite");
			System.out.println(e.toString());
			return -1;
		}
		
		System.out.println(pic.size());
		for (int i = 0; i < pic.size(); i++)
		{
			DBUtil.addPictureForSite(pic.get(i).getPicPath(), commentId, uid, siteId, pic.get(i).getSize(), pic.get(i).getTime());
		}
		
		return 1;
	}
	
	/**
	 * checkPlan
	 * @param siteId long
	 * @param uid long
	 * @return Bool; true if exist
	 */
	public static boolean checkPlan(long siteId, long uid) {
		
		PreparedStatement stmt = null;
		
		Connection conn = DBUtil.getConnection();
		String sql = "select * from plan "+
				"where Site_site_id = ? and Account_user_id = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, siteId);
			stmt.setLong(2, uid);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()){
				DBUtil.close(conn, stmt, rs);
				return true;
			}
			else {
				DBUtil.close(conn, stmt, rs);
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Something error in checkPlan");
		}
		return false;
	}
	
	/**
	 * checkMinisiteCollect
	 * @param minisiteId long
	 * @param uid long
	 * @return Bool; true if exist
	 */
	public static boolean checkMinisiteCollect(long minisiteId, long uid) {
		
		PreparedStatement stmt = null;
		
		Connection conn = DBUtil.getConnection();
		String sql = "select * from favoriteminisite "+
				"where Minisite_minisite_id = ? and Account_user_id = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, minisiteId);
			stmt.setLong(2, uid);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()){
				DBUtil.close(conn, stmt, rs);
				return true;
			}
			else {
				DBUtil.close(conn, stmt, rs);
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Something error in checkMinisiteCollect");
		}
		return false;
	}
	
	/**
	 * Get site list by user_id in commentForSite
	 * @param userId
	 * @param size
	 * @param offset
	 * @return ArrayList of Long which contains all site_id in city_id
	 */
	public static ArrayList<Long> getSiteListByUserId(long userId, long size, long offset) {

		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select Site_site_id from commentsforsite "+
					"where Account_user_id = ? " +
					"limit ?, ?;";
		
		ArrayList<Long> result = new ArrayList<Long>();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userId);
			stmt.setLong(2, offset);
			stmt.setLong(3, size);
						
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result.add(rs.getLong(1));
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getSiteList");
		}
		return result;
	}
	
	/**
	 * 
	 * @param siteId
	 * @return comments number of the site
	 */
	public static int getSiteCommentsNumber(long siteId) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select count(commentsForSite_id) from commentsforsite " +
					"where Site_site_id = ?;";
		
		int commentNumber = -1;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, siteId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()){
				commentNumber = rs.getInt(1);
				DBUtil.close(conn, stmt, rs);
				return commentNumber;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commentNumber;
	}
	
	/**
	 * 
	 * @param minisiteId
	 * @return comments number of the minisite
	 */
	public static int getMinisiteCommentsNumber(long minisiteId) {
		
		PreparedStatement stmt = null;
		
		Connection conn = DBUtil.getConnection();
		String sql = "select count(commentsForMinisite_id) from commentsforminisite " +
				"where Minisite_minisite_id = ?;";
		
		int commentNumber = -1;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, minisiteId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()){
				commentNumber = rs.getInt(1);
				DBUtil.close(conn, stmt, rs);
				return commentNumber;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commentNumber;
	}
	
	public static int getUsersNumberOfSite(long siteId) {
		
		PreparedStatement stmt = null;
		
		Connection conn = DBUtil.getConnection();
		String sql = "select count(distinct Account_user_id) from commentsforsite "
				+ "where Site_site_id = ?";
		
		int userNumber = -1;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, siteId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				userNumber = rs.getInt(1);
				DBUtil.close(conn, stmt, rs);
				return userNumber;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userNumber;
	}
	
	public static int getUsersNumberOfMinisite(long minisiteId) {
		
		PreparedStatement stmt = null;
		
		Connection conn = DBUtil.getConnection();
		String sql = "select count(distinct Account_user_id) from commentsforminisite "
				+ "where Minisite_minisite_id = ?";
		
		int userNumber = -1;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, minisiteId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				userNumber = rs.getInt(1);
				DBUtil.close(conn, stmt, rs);
				return userNumber;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userNumber;
	}
}
