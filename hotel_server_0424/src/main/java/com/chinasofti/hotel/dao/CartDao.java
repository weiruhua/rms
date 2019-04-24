package com.chinasofti.hotel.dao;

import java.util.List;

import com.chinasofti.hotel.domain.Cart;

public interface CartDao {
	//添加购物车
	public boolean addCart(Cart cart);
	//删除购物车
	public boolean deleteCartById(int id);
	//更改购物车
	public boolean updateCart(Cart cart,int num);
	//查询购物车
	public List<Cart> selectAllCart();
	//根据菜品id查询数据
	public Cart selectCartById(int id);
	//清空购物车
	public boolean clearCart();

}
