package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * データベースに接続するためのConnectionManagerです。
 * @author 大石圭純
 */

public class ConnectionManager {
	
	private final static String URL = "jdbc:mysql://localhost:3306/task_db";
	
	private final static String USER = "root";

	private final static String PASSWORD = "root";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection(URL, USER, PASSWORD);

	}

}
