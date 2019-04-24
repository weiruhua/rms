package com.chinasofti.hotel.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	public DBUtil(){
		
		try {
			Properties prop=new Properties();
			prop.load(new FileInputStream("prop.properties"));
			Class.forName(prop.getProperty("classname")).newInstance();
			conn=DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//增删改方法
	//非预编译
	public int update(String sql) throws SQLException{
		stmt=conn.createStatement();
		return stmt.executeUpdate(sql);
	}
	public int update(String sql,Object...arr) throws SQLException{
		pstmt=conn.prepareStatement(sql);
		for(int i=0;i<arr.length;i++){
			pstmt.setObject((i+1), arr[i]);
		}
		return pstmt.executeUpdate();
	}
	//查询方法
	//非预编译
	public ResultSet query(String sql) throws SQLException{
		stmt=conn.createStatement();
		return stmt.executeQuery(sql);
	}
	//预编译
	public ResultSet query(String sql,Object...arr) throws SQLException{
		pstmt=conn.prepareStatement(sql);
		for(int i=0;i<arr.length;i++){
			pstmt.setObject((i+1), arr[i]);
		}
		return pstmt.executeQuery();
	}
	//关闭资源
	public void closed(){
		
		try {
			if(pstmt!=null && !pstmt.isClosed())pstmt.close();
			if(stmt!=null && !stmt.isClosed())stmt.close();
			if(conn!=null && !conn.isClosed())conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}





























