package com.chinasofti.hotel.control;

import java.util.Date;
import java.util.List;

import com.chinasofti.hotel.domain.Card;
import com.chinasofti.hotel.domain.Cart;
import com.chinasofti.hotel.domain.Employee;
import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Menu;
import com.chinasofti.hotel.domain.Orderitem;
import com.chinasofti.hotel.domain.Orders;
import com.chinasofti.hotel.domain.Users;

public interface RMSService {

	/*-----------购物车部分------------*/
	//浏览购物车菜品
	public List<Cart> showCart();
	//增加购物车菜品
	public String addCart(Cart cart);
	//修改菜品信息
	public String updateCart(Cart cart,int num);
	//删除菜品
	public String deleteCart(int fid);
	//根据id查询购物车记录
	public Cart selectCartById(int fid);
	//清空购物车
	public String clearCart();
	/*-----------会员卡部分------------*/
	//增加新会员
	public String addCard(Card card);
	//会员卡充值
	public String addMoney(Card card,double money);
	//会员卡挂失，解挂，冻结
	public String changeCardStatus(Card card,int status);
	//会员卡积分兑换
	public String subScore(Card card,int num);
	//会员卡补办
	public String replaceCard(Card card,int cno);
	//会员卡优惠额度修改
	public String changeCardDiscount(double discount);
	//根据id查询会员卡
	public Card selectCardById(int cno);
	//根据用户名查询会员卡
	public Card selectCardByName(String uname);
	//查询所有会员卡信息
	public List<Card> selectAllCard();
	/*-----------员工表部分------------*/
	//员工登录
	public Employee login(String account, String password);
	//查看所有员工
	public List<Employee> selectAllEmployee();
	//根据ID查询员工个人信息
	public Employee selectEmployeeByid(int empid);
	//增加员工
	public String addEmployee(Employee e);
	//修改员工信息
	public String updateEmployee(Employee emp);
	//删除员工
	public String deleteEmployee(int empid);
	/*-----------菜品部分------------*/
	//查看所有菜品
	public List<Food> selectAllFood();
	//通过id查询菜品
	public Food selectFoodById(int fid);
	//通过菜品名查询菜品
	public Food selectFoodByFname(String fname);
	//增加菜品
	public String addFood(Food f);
	//修改菜品
	public String updateFood(Food f);
	//删除菜品
	public String deleteFood(int fid);
	//设置特价菜
	public String discountFood(int fid);
	/*-----------菜品分类部分------------*/
	//删除菜品分类
	public String deleteMenu(int mid);
	//增加菜品分类
	public String addMenu(Menu m);
	//展示所有分类
	public List<Menu> selectAllMenu();
	/*-----------订单部分------------*/
	//查询所有订单
	public List<Orders> selectAllOrders();
	//根据订单编号查找订单
	public Orders selectByid(String oid);
	//增加订单
	public String addOrders(Orders o);
	//根据时间段查找
	public List<Orders> selectOrdersByTime(Date d1, Date d2);
	/*-----------用户部分------------*/
	//查询所有会员用户
	public List<Users> selectAllUsers();
	//根据用户编号查找用户
	public Users selectById(int uid);
	//增加用户
	public String addUsers(Users o);
	/*-----------订单项部分------------*/
	//查询所有订单项
	public List<Orderitem> selectAllOrderitem();
	//	根据oid查询
	public List<Orderitem> selectOrderitemById(String id);
	//增加订单项
	public String addOrderitem(Orderitem oi);

}
