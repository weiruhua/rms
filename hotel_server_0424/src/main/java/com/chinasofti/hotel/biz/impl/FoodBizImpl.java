package com.chinasofti.hotel.biz.impl;

import java.util.List;

import com.chinasofti.hotel.biz.FoodBiz;

import com.chinasofti.hotel.dao.FoodDao;

import com.chinasofti.hotel.dao.impl.FoodDaoImpl;
import com.chinasofti.hotel.domain.Food;

public class FoodBizImpl implements FoodBiz {
	private FoodDao foDao;

	public FoodBizImpl() {
		super();
		this.foDao =new FoodDaoImpl();
	}
	//查询所有菜品
	public List<Food> selectAllFood() {
		List<Food> list = this.foDao.selectAllFood();
		return list;
	}
	//通过id查询菜品
	public Food selectFoodById(int fid) {
		// TODO Auto-generated method stub
		return this.foDao.selectFoodById(fid);
	}
	//添加菜品
	public String addFood(Food f) {
		boolean msg = this.foDao.addFood(f);
		return msg?"添加成功":"添加失败";
	}
	//修改菜品
	public String updateFood(Food f) {
		boolean msg = this.foDao.updateFood(f);
		return msg?"修改成功":"修改失败";
	}
	//删除菜品
	public String deleteFood(int fid) {
		// TODO Auto-generated method stub
		return this.foDao.delectFoodById(fid)?"删除成功":"删除失败";
	}
	//修改特价菜
	public String discountFood(int fid) {
		Food food = selectFoodById(fid);
		if("y".equals(food.getStatus())){
			food.setStatus("n");
		}else{
			food.setStatus("y");
		}
		updateFood(food);
		return "设置成功";
	}
	public Food selectFoodByFname(String fname) {
		// TODO Auto-generated method stub
		return this.foDao.selectFoodByName(fname);
	}

}
