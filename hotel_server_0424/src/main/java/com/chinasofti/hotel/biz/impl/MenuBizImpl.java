package com.chinasofti.hotel.biz.impl;

import java.util.List;

import com.chinasofti.hotel.biz.MenuBiz;

import com.chinasofti.hotel.dao.MenuDao;

import com.chinasofti.hotel.dao.impl.MenuDaoImpl;
import com.chinasofti.hotel.domain.Menu;

public class MenuBizImpl implements MenuBiz {
	private MenuDao menuDao;

	public MenuBizImpl() {
		super();
		this.menuDao =new MenuDaoImpl();
	}
	public String deleteMenu(int mid) {
		boolean msg = this.menuDao.delectMenuById(mid);
		return msg?"删除成功":"删除失败";
	}

	public String addMenu(Menu m) {
		// TODO Auto-generated method stub
		return this.menuDao.addMenu(m)?"添加成功":"添加失败";
	}
	public List<Menu> selectAllMenu() {
		// TODO Auto-generated method stub
		return this.menuDao.selectAllMenu();
	}

}
