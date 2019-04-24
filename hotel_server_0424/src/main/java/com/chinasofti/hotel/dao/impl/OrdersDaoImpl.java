package com.chinasofti.hotel.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinasofti.hotel.dao.OrdersDao;
import com.chinasofti.hotel.domain.Card;
import com.chinasofti.hotel.domain.Employee;
import com.chinasofti.hotel.domain.Orders;
import com.chinasofti.hotel.util.DBUtil;

public class OrdersDaoImpl implements OrdersDao {
	private DBUtil db;
	public List<Orders> selectAllOrders() {
		this.db=new DBUtil();
		List<Orders> list=new ArrayList<Orders>();
		//String sql="select o.*,c.*,e.* from orders o,card c,employee e where o.cno=c.cno(+) and o.empId=e.empId";
		String sql1="select * from orders";
		try {
			ResultSet rs1 = this.db.query(sql1);
			while(rs1.next()){
				Orders o=new Orders();
				o.setOid(rs1.getString("oid"));
				if(rs1.getInt("empId")==0){
					o.setE1(new Employee());
				}else{
					String sql2="select * from employee where empid="+rs1.getInt("empId");
					ResultSet rs2 = this.db.query(sql2);
					while(rs2.next()){
						o.setE1(new Employee(rs2.getInt("empId"),
								rs2.getString("eName"),
								rs2.getString("empAccount"),
								rs2.getString("empPassword"),
								rs2.getInt("pow")));
					}
					
				}
				if(rs1.getInt("cno")==0){
					o.setC1(new Card());
				}else{
					String sql3="select * from card where cno="+rs1.getInt("cno");
					ResultSet rs3 = this.db.query(sql3);
					while(rs3.next()){
						o.setC1(new Card(rs3.getInt("cno"),
								null,
								rs3.getInt("score"),
								rs3.getDouble("money"),
								rs3.getDouble("discount"),
								rs3.getInt("status")));
					}
				}
				o.setOdate(rs1.getDate("oDate"));
				o.setTotal(rs1.getDouble("total"));
				
				list.add(o);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.db.closed();
		}
		return null;
	}
	public Orders selectOrdersById(String id) {
		this.db=new DBUtil();
		String sql="select o.*,c.*,e.* from orders o,card c,employee e where o.cno=c.cno(+) and o.empId=e.empId and oid="+id;
		Orders o=new Orders();
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				o.setOid(rs.getString("oid"));
				o.setE1(new Employee(rs.getInt("empId"),
						rs.getString("eName"),
						rs.getString("empAccount"),
						rs.getString("empPassword"),
						rs.getInt("pow")));
				o.setC1(new Card(rs.getInt("cno"),
						null,
						rs.getInt("score"),
						rs.getDouble("money"),
						rs.getDouble("discount"),
						rs.getInt("status")));
				o.setOdate(rs.getDate("oDate"));
				o.setTotal(rs.getDouble("total"));	
				return o;
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


	public boolean addOrders(Orders o){
		this.db=new DBUtil();
		String sql="insert into orders values(?,?,?,?,?)"; 
		java.sql.Date da = new java.sql.Date(o.getOdate().getTime());
		try {
			int i = this.db.update(sql, o.getOid(),o.getE1().getEmpId(),o.getC1().getCno(),da,o.getTotal());
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			this.db.closed();
		}

	}
	public List<Orders> selectOrdersByTime(Date d1, Date d2) {
		this.db=new DBUtil();
		//String sql="select o.*,c.*,e.* from orders o,card c,employee e where o.cno=c.cno(+) and o.empId=e.empId and oDate between to_date('"+d1+"','yyyy-mm-dd') and to_date('"+d2+"','yyyy-mm-dd')";
		List<Orders> list=new ArrayList<Orders>();
		String sql1="select * from orders where oDate between to_date('"+d1+"','yyyy-mm-dd') and to_date('"+d2+"','yyyy-mm-dd')";
		try {
			ResultSet rs1 = this.db.query(sql1);
			while(rs1.next()){
				Orders o=new Orders();
				o.setOid(rs1.getString("oid"));
				if(rs1.getInt("empId")==0){
					o.setE1(new Employee());
				}else{
					String sql2="select * from employee where empId="+rs1.getInt("empId");
					ResultSet rs2 = this.db.query(sql2);
					while(rs2.next()){
						o.setE1(new Employee(rs2.getInt("empId"),
								rs2.getString("eName"),
								rs2.getString("empAccount"),
								rs2.getString("empPassword"),
								rs2.getInt("pow")));
					}
				}
				if(rs1.getInt("cno")==0){
					o.setC1(new Card());
				}else{
					String sql3="select * from card where cno="+rs1.getInt("cno");
					ResultSet rs3 = this.db.query(sql3);
					while(rs3.next()){
						o.setC1(new Card(rs3.getInt("cno"),
								null,
								rs3.getInt("score"),
								rs3.getDouble("money"),
								rs3.getDouble("discount"),
								rs3.getInt("status")));
					}
				}
				o.setOdate(rs1.getDate("oDate"));
				o.setTotal(rs1.getDouble("total"));
				list.add(o);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.db.closed();
		}
	/*	try {
			ResultSet rs = this.db.query(sql);
				while(rs.next()){
					Orders o=new Orders();
					o.setOid(rs.getString("oid"));
					o.setE1(new Employee(rs.getInt("empId"),
							rs.getString("eName"),
							rs.getString("empAccount"),
							rs.getString("empPassword"),
							rs.getInt("pow")));
					o.setC1(new Card(rs.getInt("cno"),
							null,
							rs.getInt("score"),
							rs.getDouble("money"),
							rs.getDouble("discount"),
							rs.getInt("status")));
					o.setOdate(rs.getDate("oDate"));
					o.setTotal(rs.getDouble("total"));
					list.add(o);
				}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			this.db.closed();
		}*/
		return null;
	}

}
