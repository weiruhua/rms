package com.chinasofti.hotel.biz.impl;

import java.util.List;

import com.chinasofti.hotel.biz.UsersBiz;
import com.chinasofti.hotel.dao.UsersDao;
import com.chinasofti.hotel.dao.impl.UsersDaoImpl;
import com.chinasofti.hotel.domain.Users;

public class UsersBizImpl implements UsersBiz {
	//创建dao对象
			private UsersDao uDao;
			
	public UsersBizImpl() {
		super();
		this.uDao=new UsersDaoImpl();
	}

	
	public List<Users> selectAllUsers() {
		// TODO Auto-generated method stub
		return this.uDao.selectAllUsers();
	}


	public Users selectById(int uid) {
		// TODO Auto-generated method stub
		return this.uDao.selectUsersById(uid);
	}

	
	public String addUsers(Users o) {
		// TODO Auto-generated method stub
		return this.uDao.addUsers(o)?"添加成功！":"添加失败！";
	}

}
