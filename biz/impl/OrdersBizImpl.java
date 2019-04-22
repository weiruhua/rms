package com.chinasofti.hotel.biz.impl;

import java.util.List;

import com.chinasofti.hotel.biz.OrdersBiz;

import com.chinasofti.hotel.dao.OrdersDao;

import com.chinasofti.hotel.dao.impl.OrdersDaoImpl;
import com.chinasofti.hotel.domain.Orders;

public class OrdersBizImpl implements OrdersBiz {
	//创建dao对象
		private OrdersDao orDao;

		public OrdersBizImpl() {
			super();
			this.orDao =new OrdersDaoImpl();
		}
	public List<Orders> selectAllOrders() {
		// TODO Auto-generated method stub
		return this.orDao.selectAllOrders();
	}

	public Orders selectByid(int oid) {
		// TODO Auto-generated method stub
		return this.orDao.selectOrdersById(oid);
	}

	public String addOrders(Orders o) {
		// TODO Auto-generated method stub
		return this.orDao.addOrders(o)?"支付成功":"支付失败";
	}

}
