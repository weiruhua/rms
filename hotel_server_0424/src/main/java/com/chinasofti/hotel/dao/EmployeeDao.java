package com.chinasofti.hotel.dao;

import java.util.List;

import com.chinasofti.hotel.domain.Employee;

public interface EmployeeDao {
	//员工登录验证
	public Employee loginEmployee(String account,String password);
	//增加员工
	public boolean addEmployee(Employee emp);
	//删除员工
	public boolean deleteEmployeeById(int id); 
	//修改员工信息
	public boolean updateEmployee(Employee emp);
	//查询所有员工
	public List<Employee> selectAllEmployee();
	//根据员工编号查询员工
	public Employee selectEmployeeById(int id);
}
