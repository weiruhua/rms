package com.chinasofti.hotel.biz.impl;

import java.util.List;

import com.chinasofti.hotel.biz.CartBiz;
import com.chinasofti.hotel.dao.CartDao;
import com.chinasofti.hotel.dao.impl.CartDaoImpl;
import com.chinasofti.hotel.domain.Cart;


public class CartBizImpl implements CartBiz{
	//创建dao对象
		private CartDao caDao;

		public CartBizImpl() {
			super();
			this.caDao =new CartDaoImpl();
		}
	public List<Cart> showCart() {
		List<Cart> list = this.caDao.selectAllCart();
		return list;
	}

	public String addCart(Cart cart) {
		boolean f = this.caDao.addCart(cart);
		return f?"添加成功":"添加失败";
	}

	public String updateCart(Cart cart,int num) {
		boolean f = this.caDao.updateCart(cart,num);
		return f?"修改成功":"修改失败";
	}

	public String deleteCart(int fid) {
		boolean f = this.caDao.deleteCartById(fid);
		return f?"取消成功":"取消失败";
	}
	public Cart selectCartById(int fid) {
		// TODO Auto-generated method stub
		return this.caDao.selectCartById(fid);
	}
	public String clearCart() {
		// TODO Auto-generated method stub
		return this.caDao.clearCart()?"支付成功":"支付失败";
	}

}
