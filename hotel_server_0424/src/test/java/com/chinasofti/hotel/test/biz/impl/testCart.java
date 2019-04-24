package com.chinasofti.hotel.test.biz.impl;

import java.util.List;

import org.junit.Test;

import com.chinasofti.hotel.biz.CartBiz;
import com.chinasofti.hotel.biz.impl.CartBizImpl;
import com.chinasofti.hotel.dao.CartDao;
import com.chinasofti.hotel.dao.impl.CartDaoImpl;
import com.chinasofti.hotel.domain.Cart;
import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Menu;

public class testCart {
//测试购物车业务层
	CartBiz c=new CartBizImpl();
	CartDao cc=new CartDaoImpl();
	Menu m=new Menu(1, "肉菜");
	Food f=new Food(102, m, "鱼香肉丝", 59, "n");
	//测试查看购物车
	@Test
	public void showCart(){
		List<Cart> list = this.c.showCart();
		for (Cart s : list) {
			System.out.println(s);
		}
	}
	@Test
	public void addCart(){
		String a = this.c.addCart(new Cart(f,5));
		System.out.println(a);
		
	}
	@Test
	public void updateCart(){
		String a = this.c.updateCart(new Cart(f,5),6);
		System.out.println(a);
		
	}
	@Test
	public void deleteCart(){
		String a = this.c.deleteCart(102);
		System.out.println(a);
		
	}
}
