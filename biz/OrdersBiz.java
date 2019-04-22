package com.chinasofti.hotel.biz;

import java.util.List;

import com.chinasofti.hotel.domain.Orders;

public interface OrdersBiz {
	//查询所有订单
	public List<Orders> selectAllOrders();
	//根据订单编号查找订单
	public Orders selectByid(int oid);
	//增加订单
	public String addOrders(Orders o);
}
