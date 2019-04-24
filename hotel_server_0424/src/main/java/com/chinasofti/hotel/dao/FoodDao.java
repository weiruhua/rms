package com.chinasofti.hotel.dao;

import java.util.List;

import com.chinasofti.hotel.domain.Food;

public interface FoodDao {
	//查询所有菜品
	public List<Food> selectAllFood();
	//根据菜品编号查询菜品
	public Food selectFoodById(int id);
	//增加菜品
	public boolean addFood(Food food);
	//删除菜品（根据菜品编号）
	public boolean delectFoodById(int id);
	//更改菜品信息
	public boolean updateFood(Food food);
	//根据名称查询菜品
	public Food selectFoodByName(String name);

}
