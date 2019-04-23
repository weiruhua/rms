package com.chinasofti.hotel.test;

import org.junit.Test;

import com.chinasofti.hotel.control.Control;

public class testControl {
	// 测试Control
	Control c=new Control();
	/*-------------测试自主点餐页面--------------*/
	@Test
	public void start(){
		this.c.start();
	}
	@Test
	public void showFood(){
		this.c.orderFood();
	}
}
