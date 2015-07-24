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
		String sql = "select user_id, telephone, nickname, user_icon, reg_time, sex, state "+
				"from account where user_id = ?;";
		
		Account result = new Account();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, uid);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result.setUserId(rs.getLong(1));
				result.setTelephone(rs.getString(2));
				result.setNickname(rs.getString(3));
				result.setUserIcon(rs.getString(4));
				result.setRegTime(rs.getLong(5));
				result.setSex(rs.getString(6));
				result.setState(rs.getString(7));
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
			stmt.setLong(4, account.getRegTime());
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
				"set nickname = ? , state = ? , sex = ? , birthday = ? , self_introduction = ? "+
				"where user_id = ?;";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getNickname());
			stmt.setString(2, account.getState());
			stmt.setString(3, account.getSex());
			stmt.setTimestamp(4, account.getBirthday());
			stmt.setString(5, account.getSelfIntroduction());
			stmt.setLong(6, account.getUserId());
			
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
	
	/**
	 * Get City List
	 * @return City ArrayList
	 */
	public static ArrayList<City> getCityList() {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select city_id, name from city;";
		
		ArrayList<City> result = new ArrayList<City>();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				City t = new City();
				t.setCity_id(rs.getLong(1));
				t.setName(rs.getString(2));
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in getCityList");
		}
		return result;
	}
	
	/**
	 * Get Destination List in city_id
	 * @param cityId
	 * @param size
	 * @param offset
	 * @return ArrayList of Long which contains all destination_id in city_id
	 */
	public static ArrayList<Long> getSiteList(long cityId, long size, long offset){

		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select destination_id from destination "+
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
			System.out.println("Something error in getCityList");
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
		String sql = "select name, logo_path, address, pic_path, rate, coors_x, coors_y, desc_path, key_value "+
				"from destination "+
				"where destination_id = ?;";
		
		Site result = new Site();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, siteId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result.setName(rs.getString(1));
				result.setLogoPath(rs.getString(2));
				result.setAddress(rs.getString(3));
				result.setPicPath(rs.getString(4));
				result.setRate(rs.getString(5));
				result.setCoorsX(rs.getFloat(6));
				result.setCoorsY(rs.getFloat(7));
				result.setDescPath(rs.getString(8));
				result.setKeyValue(rs.getString(9));
				result.setDestinationId(siteId);
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
		String sql = "select name, logo_path, address, pic_path, coors_x, coors_y, desc_path, key_value, music_path, irc_path "+
				"from site "+
				"where site_id = ?;";
		
		Minisite result = new Minisite();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, minisiteId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result.setName(rs.getString(1));
				result.setLogoPath(rs.getString(2));
				result.setAddress(rs.getString(3));
				result.setPicPath(rs.getString(4));
				result.setCoorsX(rs.getFloat(5));
				result.setCoorsY(rs.getFloat(6));
				result.setDescPath(rs.getString(7));
				result.setKeyValue(rs.getString(8));
				result.setMusicPath(rs.getString(9));
				result.setIrcPath(rs.getString(10));
				result.setSiteId(minisiteId);
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
	 * @param currentTime
	 * @return int; 1 if success; -1 if fail
	 */
	public static int addPlan(long siteId, long uid, Timestamp currentTime) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO plan(Account_user_id, Destination_destination_id, time) "
				+ "VALUES(?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, uid);
			stmt.setLong(2, siteId);
			stmt.setTimestamp(3, currentTime);
			
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
					"where Account_user_id = ? and Destination_destination_id = ?;";
		
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
		String sql = "INSERT INTO favoritesite(Account_user_id, Site_commentsForSite_id) "
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
		String sql = "delete from favoritesite "+
				"where Account_user_id = ? and Site_commentsForSite_id = ?;";
		
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
	 * comments about destination
	 * @param siteId site_id
	 * @param size String; size of ArrayList
	 * @param offset String;
	 * @return ArrayList of CommentsForDestination
	 */
	public static ArrayList<CommentForSite> getSiteComments(long siteId, long size, long offset) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select Account_user_id, time, score, content, pic_path, nickname "+ 
					"from commentsfordestination, account "+
					"where commentsfordestination.Account_user_id = account.user_id and "+
					"commentsfordestination.Destination_destination_id = ? "+
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
				t.setPicPath(rs.getString(5));
				t.setNickName(rs.getString(6));
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
			
		} catch (SQLException e) {
			System.out.println("Something error in GetSiteComments");
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
		String sql = "select Account_user_id, time, content, nickname "+ 
				"from commentsforsite, account "+
				"where commentsforsite.Account_user_id = account.user_id "+
				"and commentsforsite.Site_commentsForSite_id = ? "+
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
				t.setNickName(rs.getString(4));
				
				result.add(t);
			}
			
			DBUtil.close(conn, stmt, rs);
		} catch (SQLException e) {
			System.out.println("Something error in signup");
		}
		return result;
	}
	
	/**
	 * Get plan List about user_id
	 * @param userId String; user_id
	 * @param startTime String;
	 * @param endTime String;
	 * @return ArrayList of Long which contains all plan site id about user_id
	 */
	public static ArrayList<Long> getPlanList(long userId, Timestamp startTime, Timestamp endTime){

		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT Destination_destination_id FROM plan "+
				"where Account_user_id = ? and time > ? and time < ?;";
		
		ArrayList<Long> result = new ArrayList<Long>();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userId);
			stmt.setTimestamp(2, startTime);
			stmt.setTimestamp(3, endTime);
			
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
	 * Get Favorite site List about user_id
	 * @param userId user_id
	 * @return ArrayList of Long which contains all favorite site id about user_id
	 */
	public static ArrayList<Long> getMinisiteCollectList(long userId){

		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "select Site_commentsForSite_id from favoritesite "+
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
	 * comment
	 * @param minisiteId String
	 * @param desc String; content
	 * @param uid String; user id
	 * @param commentTime String;
	 * @return int; 1 if success; -1 if fail
	 */
	public static int comment(long minisiteId, String desc, long uid, Timestamp commentTime) {
		
		PreparedStatement stmt = null;
        
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO commentsforsite(Account_user_id, Site_commentsForSite_id, content, time, score) "
				+ "VALUES(?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, uid);
			stmt.setLong(2, minisiteId);
			stmt.setString(3, desc);
			stmt.setTimestamp(4, commentTime);
			stmt.setInt(5, 3);
			
			stmt.executeUpdate();
			
			DBUtil.close(conn, stmt, null);
		} catch (SQLException e) {
			System.out.println("Something error in comment");
			return -1;
		}
		return 1;
	}
}
