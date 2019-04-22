package com.chinasofti.hotel.biz;

import java.util.List;

import com.chinasofti.hotel.domain.Employee;

public interface EmployeeBiz {
//员工类业务逻辑层实现
	//员工登录
	public Employee login(String account, String password);
	//查看所有员工
	public List<Employee> selectAllEmployee();
	//根据ID查询员工个人信息
	public Employee selectEmployeeByid(int empid);
	//增加员工
	public String addEmployee(Employee e);
	//修改员工信息
	public String updateEmployee(Employee emp);
	//删除员工
	public String deleteEmployee(int empid);
}
