package com.chinasofti.hotel.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.hotel.dao.CartDao;
import com.chinasofti.hotel.domain.Cart;
import com.chinasofti.hotel.domain.Employee;
import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Menu;
import com.chinasofti.hotel.util.DBUtil;


public class CartDaoImpl implements CartDao {
	private DBUtil db;

	public boolean addCart(Cart cart) {
		this.db=new DBUtil();
		String sql="insert into cart values(?,?)";
		try {
			int i = this.db.update(sql, cart.getF().getFid(),cart.getNum());
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		
	}

	public boolean deleteCartById(int id) {
		this.db=new DBUtil();
		String sql="delete from cart where fid="+id;
		try {
			int i = this.db.update(sql);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		
	}

	public boolean updateCart(Cart cart,int num) {
		this.db=new DBUtil();
		String sql="update cart set cnum=? where fid=?";
		try {
			int i = this.db.update(sql, num,cart.getF().getFid());
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		
	}

	public List<Cart> selectAllCart() {
		this.db=new DBUtil();
		String sql="select f.*,c.*,m.* from cart c,food f,menu m where c.fid=f.fid and m.menuId=f.menuId";
		try {
			ResultSet rs = this.db.query(sql);
			List<Cart> list=new ArrayList<Cart>();
			while(rs.next()){
				list.add(new Cart
						(new Food(rs.getInt("fid"),
								new Menu(rs.getInt("menuId"),
										rs.getString("menuName")),
								rs.getString("fname"),
								rs.getDouble("price"),
								rs.getString("status")),
						rs.getInt("cnum")));
		
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
		
	}
	public Cart selectCartById(int id) {
		this.db=new DBUtil();	
		Cart c=new Cart();
		String sql="select f.*,c.*,m.* from cart c,food f,menu m where c.fid=f.fid and m.menuId=f.menuId and f.fid="+id;
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				c.setF(new Food(rs.getInt("fid"),
						new Menu(rs.getInt("menuId"), 
								rs.getString("menuName")), 
						rs.getString("fname"),
						rs.getDouble("price"),
						rs.getString("status")));
				c.setNum(rs.getInt("cnum"));
				return c;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
		return null;
	}

	
	public boolean clearCart() {
		this.db=new DBUtil();
		String sql="delete from cart";
		try {
			int i = this.db.update(sql);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.db.closed();
		}
		return false;
	}

	
}
