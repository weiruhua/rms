package com.chinasofti.hotel.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.hotel.dao.UsersDao;
import com.chinasofti.hotel.domain.Users;
import com.chinasofti.hotel.util.DBUtil;

public class UsersDaoImpl implements UsersDao {
	private DBUtil db;
	
	public Users selectUsersById(int id) {
		this.db=new DBUtil();
		Users u=new Users();
		String sql="select * from users where usid="+id;
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				u.setUsid(rs.getInt("usid"));
				u.setUname(rs.getString("uname"));
				u.setTel(rs.getString("tel"));
				u.setBirth(rs.getDate("birth"));
				return u;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return null;
	}

	
	public boolean addUsers(Users u) {
		this.db=new DBUtil();
		String sql="insert into users values(?,?,?,?)";
		try {
			java.sql.Date da = new java.sql.Date(u.getBirth().getTime());
			int i = this.db.update(sql, u.getUsid(),u.getUname(),u.getTel(),da);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		
	}

	
	public List<Users> selectAllUsers() {
		this.db=new DBUtil();
		List<Users> list=new ArrayList<Users>();
		String sql="select * from users";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				list.add(new Users(rs.getInt("usid"), 
						rs.getString("uname"), 
						rs.getString("tel"),
						rs.getDate("birth")));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
		
	}

}
