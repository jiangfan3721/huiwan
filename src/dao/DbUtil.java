package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/huiwan";
	public static final String USERNAME = "root";
	public static final String PWD = "";
	
	private Connection connection = null;
	
	public Connection getConnection() throws Exception {
		
		System.out.println("Get connection!");
		Class.forName(DRIVER);
		System.out.println("Load Mysql Driver successfully...");
		connection = DriverManager.getConnection(URL, USERNAME, PWD);
		
		return connection;
	}
}
