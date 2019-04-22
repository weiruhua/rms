package com.chinasofti.hotel.biz;

import com.chinasofti.hotel.domain.Menu;

public interface MenuBiz {
	//删除菜品分类
	public String deleteMenu(int mid);
	//增加菜品分类
	public String addMenu(Menu m);
}
