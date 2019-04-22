package com.chinasofti.hotel.biz;

import java.util.List;

import com.chinasofti.hotel.domain.Food;

public interface FoodBiz {
//菜品的实体类业务逻辑实现
	//查看所有菜品
	public List<Food> selectAllFood();
	//通过id查询菜品
	public Food selectFoodById(int fid);
	//增加菜品
	public String addFood(Food f);
	//修改菜品
	public String updateFood(Food f);
	//删除菜品
	public String deleteFood(int fid);
	//设置特价菜
	public String discountFood(int fid);
}
