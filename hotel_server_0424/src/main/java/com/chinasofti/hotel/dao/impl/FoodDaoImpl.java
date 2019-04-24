package com.chinasofti.hotel.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.hotel.dao.FoodDao;
import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Menu;
import com.chinasofti.hotel.util.DBUtil;

public class FoodDaoImpl implements FoodDao {
	private DBUtil db;
	public List<Food> selectAllFood() {
		this.db=new DBUtil();
		List<Food> list=new ArrayList<Food>();
		String sql="select f.*,m.* from food f,menu m where f.menuId=m.menuId";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				list.add(new Food(rs.getInt("fid"),
						new Menu(rs.getInt("menuId"),
								rs.getString("menuName")),
						rs.getString("fname"),
						rs.getDouble("price"),
						rs.getString("status")
						));
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

	public Food selectFoodById(int id) {
		this.db=new DBUtil();	
		Food f=new Food();
		String sql="select f.*,m.* from food f,menu m where f.menuId=m.menuId and fid="+id;
		try {
			ResultSet rs = this.db.query(sql);
			if(rs.next()){
				f.setFid(rs.getInt("fid"));
				f.setM1(new Menu(rs.getInt("menuId"),
						rs.getString("menuName")));
				f.setFname(rs.getString("fname"));
				f.setPrice(rs.getDouble("price"));
				f.setStatus(rs.getString("status"));
				return f;
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

	public boolean addFood(Food food) {
		this.db=new DBUtil();
		String sql="insert into food values(?,?,?,?,?)"; 
		try {
			int i = this.db.update(sql, food.getFid(),food.getM1().getMenuId(),food.getFname(),food.getPrice(),food.getStatus());
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		
	}

	public boolean delectFoodById(int id) {
		this.db=new DBUtil();
		String sql="delete from food where fid="+id;
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

	public boolean updateFood(Food food) {
		this.db=new DBUtil();
		String sql="update food set fname=?,price=?,status=? where fid=?";
		try {
			int i = this.db.update(sql, food.getFname(),food.getPrice(),food.getStatus(),food.getFid());
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		
	}
	public Food selectFoodByName(String name) {
		this.db=new DBUtil();	
		Food f=new Food();
		String sql="select f.*,m.* from food f,menu m where f.menuId=m.menuId and fname='"+name+"'";
		try {
			ResultSet rs = this.db.query(sql);
			if(rs.next()){
				//return new Food(fid, m1, fname, price, status)
				f.setFid(rs.getInt("fid"));
				f.setM1(new Menu(rs.getInt("menuId"),
						rs.getString("menuName")));
				f.setFname(rs.getString("fname"));
				f.setPrice(rs.getDouble("price"));
				f.setStatus(rs.getString("status"));
				return f;
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

}
