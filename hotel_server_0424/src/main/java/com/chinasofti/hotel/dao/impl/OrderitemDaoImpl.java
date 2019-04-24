package com.chinasofti.hotel.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.hotel.dao.OrderitemDao;
import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Orderitem;
import com.chinasofti.hotel.domain.Orders;
import com.chinasofti.hotel.util.DBUtil;

public class OrderitemDaoImpl implements OrderitemDao {
	private DBUtil db;
	public List<Orderitem> selectAllOrderitem() {
		this.db=new DBUtil();
		//List<Orderitem> list=new ArrayList<Orderitem>();
		List<Orderitem> list=new ArrayList<Orderitem>();
		String sql="select f.*,oi.* ,o.*from food f,orderitem oi,orders o where f.fid(+)=oi.fid";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				list.add(new Orderitem(new Orders(rs.getString("oid"),null,
							/*new Employee(rs.getInt("empId"),
									rs.getString("eName"),
									rs.getString("empAccount"),
									rs.getString("empPassword"),
									rs.getInt("pow"))*/null,
							/*new Card(rs.getInt("cno"),
									null,
									rs.getInt("score"),
									rs.getDouble("money"),
									rs.getDouble("discount"),
									rs.getInt("status")),*/
						rs.getDate("oDate"),
						rs.getDouble("total")),
				new Food(rs.getInt("fid"),null,
						/*new Menu(rs.getInt("menuId"),
								rs.getString("menuName")),*/
						rs.getString("fname"),
						rs.getDouble("price"),
						rs.getString("status")),
				rs.getInt("num")));
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

	public List<Orderitem> selectOrderitemById(String id) {
		this.db=new DBUtil();	
		List<Orderitem> list=new ArrayList<Orderitem>();
		String sql="select f.*,oi.* from food f,orderitem oi where f.fid=oi.fid and oi.oid='"+id+"'";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				Orderitem oi=new Orderitem();
				String sql1="select * from orders where oid='"+id+"'";
				ResultSet rso = this.db.query(sql1);
				if(rso.next()){
					oi.setO1(new Orders(rso.getString("oid"), null, null, rso.getDate("oDate"), rso.getDouble("total")));
				}
				oi.setF1(new Food(rs.getInt("fid"),null, rs.getString("fname"), rs.getDouble("price"), rs.getString("status")));
				oi.setNum(rs.getInt("num"));
				list.add(oi);
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

	public boolean addOrderitem(Orderitem oi) {
		this.db=new DBUtil();
		String sql="insert into orderitem values(?,?,?)"; 
		try {
			
			int i = this.db.update(sql, oi.getO1().getOid(),oi.getF1().getFid(),oi.getNum());
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		
	}


}
