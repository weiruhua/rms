package com.chinasofti.hotel.test.biz.impl;

import java.util.List;

import org.junit.Test;

import com.chinasofti.hotel.biz.FoodBiz;
import com.chinasofti.hotel.biz.impl.FoodBizImpl;
import com.chinasofti.hotel.domain.Food;

public class testFood {
	FoodBiz food=new FoodBizImpl();
	@Test
	public void selectAllFood(){
		List<Food> list = food.selectAllFood();
		for (Food f : list) {
			System.out.println(f);
		}
	}
}
