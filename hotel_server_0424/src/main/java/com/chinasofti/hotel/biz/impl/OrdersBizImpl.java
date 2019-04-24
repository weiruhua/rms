package com.chinasofti.hotel.biz.impl;

import java.util.Date;
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

	public Orders selectByid(String oid) {
		// TODO Auto-generated method stub
		return this.orDao.selectOrdersById(oid);
	}

	public String addOrders(Orders o) {
		// TODO Auto-generated method stub
		return this.orDao.addOrders(o)?"支付成功":"支付失败";
	}
	public List<Orders> selectOrdersByTime(Date d1, Date d2) {
		// TODO Auto-generated method stub
		return this.orDao.selectOrdersByTime(d1, d2);
	}

}
