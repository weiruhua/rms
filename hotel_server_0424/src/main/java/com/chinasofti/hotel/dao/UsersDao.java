package com.chinasofti.hotel.dao;

import java.util.List;

import com.chinasofti.hotel.domain.Users;

public interface UsersDao {
		//查询会员信息
		public Users selectUsersById(int id);
		//添加会员
		public boolean addUsers(Users u);
		//查询所有会员信息
		public List<Users> selectAllUsers();
}
