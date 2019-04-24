package com.chinasofti.hotel.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.hotel.dao.EmployeeDao;
import com.chinasofti.hotel.domain.Employee;
import com.chinasofti.hotel.util.DBUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	private DBUtil db;
	public Employee loginEmployee(String account, String password) {
		this.db=new DBUtil();
		String sql="select * from employee where empAccount=? and empPassword=?";
		try {
			ResultSet rs = this.db.query(sql,account,password);
			if(rs.next()){
				return new Employee(rs.getInt("empId"),rs.getString("eName"),rs.getString("empAccount"),rs.getString("empPassword"),rs.getInt("pow"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.db.closed();
		}
		return null;
	}

	public boolean addEmployee(Employee emp) {
		this.db=new DBUtil();
		String sql="insert into employee values(?,?,?,?,?)";
		try {
			int i = this.db.update(sql, emp.getEmpId(),emp.getEmpName(),emp.getEmpAccount(),emp.getEmpPassword(),emp.getPow());
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		
	}

	public boolean deleteEmployeeById(int id) {
		this.db=new DBUtil();
		String sql="delete from employee where empId="+id;
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

	public boolean updateEmployee(Employee emp) {
		this.db=new DBUtil();
		String sql="update employee set eName=?,empAccount=?,empPassword=?,pow=? where empId=?";
		try {
			int i = this.db.update(sql,emp.getEmpName(),emp.getEmpAccount(),emp.getEmpPassword(),emp.getPow(),emp.getEmpId());	
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}	
	}

	public List<Employee> selectAllEmployee() {
		this.db=new DBUtil();
		List<Employee> list=new ArrayList<Employee>();
		String sql="select * from employee";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				list.add(new Employee(rs.getInt("empId"),
						rs.getString("eName"),
						rs.getString("empAccount"),
						rs.getString("empPassword"),
						rs.getInt("pow")
						));
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

	public Employee selectEmployeeById(int id) {
		this.db=new DBUtil();
		Employee emp=new Employee();
		String sql="select * from employee where empId="+id;
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				emp.setEmpId(rs.getInt("empId"));
				emp.setEmpName(rs.getString("eName"));
				emp.setEmpAccount(rs.getString("empAccount"));
				emp.setEmpPassword(rs.getString("empPassword"));
				emp.setPow(rs.getInt("pow"));
				return emp;
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
