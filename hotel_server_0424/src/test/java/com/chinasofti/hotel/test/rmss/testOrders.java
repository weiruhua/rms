package com.chinasofti.hotel.test.rmss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.chinasofti.hotel.dao.FoodDao;
import com.chinasofti.hotel.dao.OrderitemDao;
import com.chinasofti.hotel.dao.OrdersDao;
import com.chinasofti.hotel.dao.impl.FoodDaoImpl;
import com.chinasofti.hotel.dao.impl.OrderitemDaoImpl;
import com.chinasofti.hotel.dao.impl.OrdersDaoImpl;
import com.chinasofti.hotel.domain.Card;
import com.chinasofti.hotel.domain.Employee;
import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Orderitem;
import com.chinasofti.hotel.domain.Orders;

public class testOrders {
	OrdersDao or=new OrdersDaoImpl();
	FoodDao fo=new FoodDaoImpl();
	OrderitemDao oi=new OrderitemDaoImpl();
	@Test
	public void addOrders(){
		boolean b = this.or.addOrders(new Orders("11111", new Employee(), new Card(), new Date(), 24));
		System.out.println(b);
	}
	@Test
	public void selectFoodByName(){
		Food f = this.fo.selectFoodByName("hh");
		System.out.println(f);
		/*if(f==null){
			System.out.println("55");
		}else{
			System.out.println(f);
		}*/
		
	}
	@Test
	public void selectOrdersByDate(){
		Date d1 =new Date(2018-01-01);
		System.out.println(d1);
		/*Date d1 =new Date(2018-01-01);
		Date d2 =new Date(2018-10-01);
		List<Orders> list = this.or.selectOrdersByTime(d1, d2);
		for (Orders ord : list) {
			System.out.println(ord.getOid());
		}*/
		
		/*if(f==null){
			System.out.println("55");
		}else{
			System.out.println(f);
		}*/
		
	}
	@Test
	public void selectOrdersByTime(){
		OrdersDao o=new OrdersDaoImpl();
		String d1 ="2018-01-01";   
		String d2="2018-10-30"; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date utilDate1;
		try {
			utilDate1 = sdf.parse(d1);
			 Date utilDate2 = sdf.parse(d2);
			 //System.out.println(utilDate);//查看utilDate的值
		    Date date1= new java.sql.Date(utilDate1.getTime());
		    Date date2 = new java.sql.Date(utilDate2.getTime());
		    //System.out.println(date);//查看date的值
		    List<Orders> list = o.selectOrdersByTime(date1, date2);
		    System.out.println(list.size());
		   for (Orders orders : list) {
			System.out.println(orders.getOid());
		}
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void selectFoodByid(){
		Food f = this.fo.selectFoodById(999);
		if(f==null){
			System.out.println("55");
		}else{
			System.out.println(f);
		}
		
	}
	@Test
	public void sefi(){
		List<Orderitem> list = this.oi.selectOrderitemById("f62da983");
		System.out.println(list.size());
		for (Orderitem or : list) {
			System.out.println(or);
		}
	}
}
