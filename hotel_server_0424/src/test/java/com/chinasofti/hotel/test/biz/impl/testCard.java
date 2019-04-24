package com.chinasofti.hotel.test.biz.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.chinasofti.hotel.dao.CardDao;
import com.chinasofti.hotel.dao.impl.CardDaoImpl;
import com.chinasofti.hotel.domain.Card;
import com.chinasofti.hotel.domain.Users;

public class testCard {
	CardDao c=new CardDaoImpl();
	//测试会员卡业务逻辑层
	public void addCard(){
		CardDao c=new CardDaoImpl();
		String dStr ="1998-12-12";    
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sdf.parse(dStr);
			Users u=new Users(5, "周七","55555555555" ,d);
			c.addCard(new Card(5, u, 10, 100, 0.9, 0));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}}
