package com.chinasofti.hotel.biz;

import java.util.List;

import com.chinasofti.hotel.domain.Orderitem;

public interface OrderitemBiz {
	//查询所有订单项
	public List<Orderitem> selectAllOrderitem();
	//	根据oid查询
	public List<Orderitem> selectOrderitemById(String id);
	//增加订单项
	public String addOrderitem(Orderitem oi);
}
