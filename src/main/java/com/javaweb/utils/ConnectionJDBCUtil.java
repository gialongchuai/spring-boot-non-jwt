package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBCUtil {
	public static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	public static final String USER = "root";
	public static final String PASSWORD = "123123";

	public static Connection getConnection() {
		Connection con = null;
		try {
				con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
