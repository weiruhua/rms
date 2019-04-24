package com.chinasofti.hotel.biz.impl;

import java.util.List;

import com.chinasofti.hotel.biz.EmployeeBiz;
import com.chinasofti.hotel.dao.EmployeeDao;
import com.chinasofti.hotel.dao.impl.EmployeeDaoImpl;
import com.chinasofti.hotel.domain.Employee;

public class EmployeeBizImpl implements EmployeeBiz {
	private EmployeeDao empDao;

	public EmployeeBizImpl() {
		super();
		this.empDao =new EmployeeDaoImpl();
	}
	//员工登陆
	public Employee login(String account, String password) {
		return this.empDao.loginEmployee(account, password);
	}
	//查询所有员工
	public List<Employee> selectAllEmployee() {
		List<Employee> list = this.empDao.selectAllEmployee();
		return list;
	}
	//通过id查找员工
	public Employee selectEmployeeByid(int empid){
		return this.empDao.selectEmployeeById(empid);
		
	}
	//添加员工
	public String addEmployee(Employee e) {
		boolean msg = this.empDao.addEmployee(e);
		return msg?"添加成功":"添加失败";
	}
	//修改员工信息
	public String updateEmployee(Employee emp) {
		boolean msg = this.empDao.updateEmployee(emp);
		return msg?"修改成功":"修改失败";
	}
	//删除员工信息
	public String deleteEmployee(int empid) {
		// TODO Auto-generated method stub
		return this.empDao.deleteEmployeeById(empid)?"删除成功":"删除失败";
	}

}
