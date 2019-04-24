package com.chinasofti.hotel.test.biz.impl;

import org.junit.Test;

import com.chinasofti.hotel.biz.CartBiz;
import com.chinasofti.hotel.biz.EmployeeBiz;
import com.chinasofti.hotel.biz.impl.CartBizImpl;
import com.chinasofti.hotel.biz.impl.EmployeeBizImpl;
import com.chinasofti.hotel.dao.EmployeeDao;
import com.chinasofti.hotel.dao.impl.EmployeeDaoImpl;
import com.chinasofti.hotel.domain.Employee;

public class testEmployee {
	EmployeeBiz e=new EmployeeBizImpl();
	EmployeeDao ee=new EmployeeDaoImpl();
	@Test
	public void login(){
		//Employee emp = this.e.login("111", "111");
		Employee emp = this.ee.loginEmployee("111", "111");
		System.out.println(emp);
	}
}
