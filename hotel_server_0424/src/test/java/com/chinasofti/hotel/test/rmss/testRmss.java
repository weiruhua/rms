package com.chinasofti.hotel.test.rmss;

import java.util.List;

import org.junit.Test;

import com.chinasofti.hotel.control.RMSService;
import com.chinasofti.hotel.control.RMSServiceImpl;
import com.chinasofti.hotel.domain.Food;

public class testRmss {
	RMSService rm=new RMSServiceImpl();
	@Test
	public void showFood(){
		List<Food> list = rm.selectAllFood();
		for (Food f : list) {
			System.out.println(f);
		}
	}
}
