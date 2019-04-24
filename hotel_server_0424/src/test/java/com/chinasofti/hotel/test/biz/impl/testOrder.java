package com.chinasofti.hotel.test.biz.impl;

import java.util.List;

import org.junit.Test;

import com.chinasofti.hotel.dao.OrdersDao;
import com.chinasofti.hotel.dao.impl.OrdersDaoImpl;
import com.chinasofti.hotel.domain.Orders;

public class testOrder {
	OrdersDao od=new OrdersDaoImpl();
	@Test
	public void main1(){
		List<Orders> list = this.od.selectAllOrders();
		System.out.println(list.size());
	}
}
