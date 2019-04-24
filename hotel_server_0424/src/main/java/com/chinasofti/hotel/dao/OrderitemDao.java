package com.chinasofti.hotel.dao;

import java.util.List;

import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Orderitem;

public interface OrderitemDao {
	//查询所有订单项
	public List<Orderitem> selectAllOrderitem();
	//	根据oid查询
	public List<Orderitem> selectOrderitemById(String id);
	//增加订单项
	public boolean addOrderitem(Orderitem oi);


}

