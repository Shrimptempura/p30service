package com.tech.prjm09.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
	
	static Connection con = null;
	
	public static Connection getConnection() {
		
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String driver = "oracle.jdbc.driver.OracleDriver";
			String user = "blue";
			String pass = "123456";
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
