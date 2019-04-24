package com.chinasofti.hotel.biz;

import java.util.List;

import com.chinasofti.hotel.domain.Menu;

public interface MenuBiz {
	//删除菜品分类
	public String deleteMenu(int mid);
	//增加菜品分类
	public String addMenu(Menu m);
	//展示所有分类
	public List<Menu> selectAllMenu();
}
