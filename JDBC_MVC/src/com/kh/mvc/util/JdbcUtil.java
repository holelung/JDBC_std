package com.kh.mvc.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	/*
	 * JDBC API 사용 중 중복코드가 너무 많음
	 * 중복된 코드를 메소드로 분리하여 필요할 때 마다 '재사용'하자
	 */	
	public static Connection getConnect() {
		final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		final String USERNAME = "KH19_JJH";
		final String PASSWORD = "KH1234";
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	// close 재사용
	public static void closeStmt(Statement stmt) {
		try {
			if(stmt != null && !!!stmt.isClosed()) {
				stmt.close();
			}
		}catch(SQLException e) {
			System.out.println("PrepareStatement 이상");
		}
	}
	
	public static void closeRset(ResultSet rset) {
		try {
			if(rset != null) {
				rset.close();
			}
		}catch(SQLException e) {
			System.out.println("ResultSet 이상");
		}
	}
	
	public static void closeConn(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException e) {
			System.out.println("Conn 이상해");
		}
	}
	
	// DML(insert, update, delete)시 사용할 close
	public static void dmlClose(Statement stmt, Connection conn) {
		try {
			if(stmt != null && !!!stmt.isClosed()) {
				stmt.close();
			}
		}catch(SQLException e) {
			System.out.println("PrepareStatement 이상");
		}
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException e) {
			System.out.println("Conn 이상해");
		}
		
	}
	
	// DQL(SELECT) 시 사용할 close
	public static void dqlClose(ResultSet rset, Statement stmt, Connection conn) {
		try {
			if(rset != null) {
				rset.close();
			}
		}catch(SQLException e) {
			System.out.println("ResultSet 이상");
		}
		try {
			if(stmt != null && !!!stmt.isClosed()) {
				stmt.close();
			}
		}catch(SQLException e) {
			System.out.println("PrepareStatement 이상");
		}
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException e) {
			System.out.println("Conn 이상해");
		}
	}
}


