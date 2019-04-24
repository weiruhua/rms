package com.chinasofti.hotel.biz;

import java.util.List;

import com.chinasofti.hotel.domain.Cart;

public interface CartBiz {
	//浏览购物车菜品
	public List<Cart> showCart();
	//增加购物车菜品
	public String addCart(Cart cart);
	//修改菜品信息
	public String updateCart(Cart cart,int num);
	//删除菜品
	public String deleteCart(int fid);
	//根据id查询购物车记录
	public Cart selectCartById(int fid);
	//清空购物车
	public String clearCart();
}
