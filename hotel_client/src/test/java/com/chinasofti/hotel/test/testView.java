package com.chinasofti.hotel.test;

import org.junit.Test;

import com.chinasofti.hotel.view.View;

public class testView {
	//View包中的方法测试
	View v=new View();
	/*------------人工点餐服务模块测试---------------*/
	@Test
	public void welcomeE(){
		this.v.welcomeE();
	}
	@Test
	public void updateF(){
		this.v.updateF();
	}
	@Test
	public void applayF(){
		this.v.applayF();
	}
	@Test
	public void userF(){
		this.v.userF();
	}
	@Test
	public void scoreF(){
		this.v.scoreF();
	}
	/*------------后台管理模块---------------*/
	@Test
	public void welcomeM(){
		this.v.welcomeM();
	}
	@Test
	public void manFood(){
		this.v.manFood();
	}
	@Test
	public void manUser(){
		this.v.manUser();
	}
	@Test
	public void manCard(){
		this.v.manCard();
	}
	@Test
	public void manMessage(){
		this.v.manMessage();
	}
	/*--------------客户自主点餐模块--------------------*/
	@Test
	public void welcomeU(){
		this.v.welcomeU();
	}
}
