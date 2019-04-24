package com.chinasofti.hotel.biz;

import java.util.List;

import com.chinasofti.hotel.domain.Users;


public interface UsersBiz {
	//查询所有会员用户
	public List<Users> selectAllUsers();
	//根据用户编号查找用户
	public Users selectById(int uid);
	//增加用户
	public String addUsers(Users o);
}
