package com.chinasofti.hotel.dao;

import java.util.Date;
import java.util.List;

import com.chinasofti.hotel.domain.Orders;

public interface OrdersDao {
	//查询所有订单
	public List<Orders> selectAllOrders();
	//根据订单编号查询订单
	public Orders selectOrdersById(String oid);
	//添加订单
	public boolean addOrders(Orders o);
	//根据时间段查找
	public List<Orders> selectOrdersByTime(Date d1, Date d2);
	//查询月销售额
	//查询最受欢迎的菜
	//查询消费最高的顾客
}
