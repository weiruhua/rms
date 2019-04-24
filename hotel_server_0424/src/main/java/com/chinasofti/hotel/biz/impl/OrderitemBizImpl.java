package com.chinasofti.hotel.biz.impl;

import java.util.List;

import com.chinasofti.hotel.biz.OrderitemBiz;
import com.chinasofti.hotel.dao.OrderitemDao;
import com.chinasofti.hotel.dao.impl.OrderitemDaoImpl;
import com.chinasofti.hotel.domain.Orderitem;

public class OrderitemBizImpl implements OrderitemBiz {
	//创建dao对象
	private OrderitemDao orDao;

	public OrderitemBizImpl() {
		super();
		this.orDao =new OrderitemDaoImpl();
	}
	public List<Orderitem> selectAllOrderitem() {
		// TODO Auto-generated method stub
		return this.orDao.selectAllOrderitem();
	}

	public List<Orderitem> selectOrderitemById(String id) {
		// TODO Auto-generated method stub
		return this.orDao.selectOrderitemById(id);
	}

	public String addOrderitem(Orderitem oi) {
		// TODO Auto-generated method stub
		return this.orDao.addOrderitem(oi)?"添加成功":"添加失败";
	}

}
