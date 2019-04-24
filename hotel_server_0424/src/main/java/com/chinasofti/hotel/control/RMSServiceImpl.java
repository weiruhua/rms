package com.chinasofti.hotel.control;

import java.util.Date;
import java.util.List;

import com.chinasofti.hotel.biz.CardBiz;
import com.chinasofti.hotel.biz.CartBiz;
import com.chinasofti.hotel.biz.EmployeeBiz;
import com.chinasofti.hotel.biz.FoodBiz;
import com.chinasofti.hotel.biz.MenuBiz;
import com.chinasofti.hotel.biz.OrderitemBiz;
import com.chinasofti.hotel.biz.OrdersBiz;
import com.chinasofti.hotel.biz.UsersBiz;
import com.chinasofti.hotel.biz.impl.CardBizImpl;
import com.chinasofti.hotel.biz.impl.CartBizImpl;
import com.chinasofti.hotel.biz.impl.EmployeeBizImpl;
import com.chinasofti.hotel.biz.impl.FoodBizImpl;
import com.chinasofti.hotel.biz.impl.MenuBizImpl;
import com.chinasofti.hotel.biz.impl.OrderitemBizImpl;
import com.chinasofti.hotel.biz.impl.OrdersBizImpl;
import com.chinasofti.hotel.biz.impl.UsersBizImpl;
import com.chinasofti.hotel.domain.Card;
import com.chinasofti.hotel.domain.Cart;
import com.chinasofti.hotel.domain.Employee;
import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Menu;
import com.chinasofti.hotel.domain.Orderitem;
import com.chinasofti.hotel.domain.Orders;
import com.chinasofti.hotel.domain.Users;

public class RMSServiceImpl implements RMSService {
	private CardBiz cardBiz;
	private CartBiz cartBiz;
	private EmployeeBiz empBiz;
	private FoodBiz foodBiz;
	private MenuBiz menuBiz;
	private OrdersBiz ordersBiz;
	private UsersBiz usersBiz;
	private OrderitemBiz orderitemBiz;
	public RMSServiceImpl() {
		super();
		this.cardBiz =new CardBizImpl();
		this.cartBiz = new CartBizImpl();
		this.empBiz = new EmployeeBizImpl();
		this.foodBiz = new FoodBizImpl();
		this.menuBiz = new MenuBizImpl();
		this.ordersBiz = new OrdersBizImpl();
		this.usersBiz = new UsersBizImpl();
		this.orderitemBiz=new OrderitemBizImpl();
		
	}

	public List<Cart> showCart() {
		// TODO Auto-generated method stub
		return this.cartBiz.showCart();
	}

	public String addCart(Cart cart) {
		return this.cartBiz.addCart(cart);
	}

	public String updateCart(Cart cart, int num) {
		// TODO Auto-generated method stub
		return this.cartBiz.updateCart(cart, num);
	}

	public String deleteCart(int fid) {
		// TODO Auto-generated method stub
		return this.cartBiz.deleteCart(fid);
	}
	public Cart selectCartById(int fid) {
		// TODO Auto-generated method stub
		return this.cartBiz.selectCartById(fid);
	}

	public String clearCart() {
		// TODO Auto-generated method stub
		return this.cartBiz.clearCart();
	}
	public String addCard(Card card) {
		// TODO Auto-generated method stub
		return this.cardBiz.addCard(card);
	}

	public String addMoney(Card card, double money) {
		// TODO Auto-generated method stub
		return this.cardBiz.addMoney(card, money);
	}

	public String changeCardStatus(Card card, int status) {
		// TODO Auto-generated method stub
		return this.cardBiz.changeCardStatus(card, status);
	}

	public String subScore(Card card, int num) {
		// TODO Auto-generated method stub
		return this.cardBiz.subScore(card, num);
	}

	public String replaceCard(Card card, int cno) {
		// TODO Auto-generated method stub
		return this.cardBiz.replaceCard(card, cno);
	}

	public String changeCardDiscount(double discount) {
		// TODO Auto-generated method stub
		return this.cardBiz.changeCardDiscount(discount);
	}
	public Card selectCardById(int cno) {
		// TODO Auto-generated method stub
		return this.cardBiz.selectCardById(cno);
	}
	public Card selectCardByName(String uname) {
		// TODO Auto-generated method stub
		return this.cardBiz.selectCardByName(uname);
	}
	public List<Card> selectAllCard() {
		// TODO Auto-generated method stub
		return this.cardBiz.selectAllCard();
	}

	public Employee login(String account, String password) { 
		return this.empBiz.login(account, password);
	}

	public List<Employee> selectAllEmployee() {
		// TODO Auto-generated method stub
		return this.empBiz.selectAllEmployee();
	}

	public Employee selectEmployeeByid(int empid) {
		// TODO Auto-generated method stub
		return this.empBiz.selectEmployeeByid(empid);
	}

	public String addEmployee(Employee e) {
		// TODO Auto-generated method stub
		return this.empBiz.addEmployee(e);
	}

	public String updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return this.empBiz.updateEmployee(emp);
	}

	public String deleteEmployee(int empid) {
		// TODO Auto-generated method stub
		return this.empBiz.deleteEmployee(empid);
	}

	public List<Food> selectAllFood() {
		
		return this.foodBiz.selectAllFood();
	}

	public Food selectFoodById(int fid) {
		
		return this.foodBiz.selectFoodById(fid);
	}
	
	public Food selectFoodByFname(String fname) {
		// TODO Auto-generated method stub
		return this.foodBiz.selectFoodByFname(fname);
	}
	
	public String addFood(Food f) {
		// TODO Auto-generated method stub
		return this.foodBiz.addFood(f);
	}

	public String updateFood(Food f) {
		// TODO Auto-generated method stub
		return this.foodBiz.updateFood(f);
	}

	public String deleteFood(int fid) {
		// TODO Auto-generated method stub
		return this.foodBiz.deleteFood(fid);
	}

	public String discountFood(int fid) {
		// TODO Auto-generated method stub
		return this.foodBiz.discountFood(fid);
	}

	public String deleteMenu(int mid) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addMenu(Menu m) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Menu> selectAllMenu() {
		// TODO Auto-generated method stub
		return this.menuBiz.selectAllMenu();
	}

	public List<Orders> selectAllOrders() {
		// TODO Auto-generated method stub
		return this.ordersBiz.selectAllOrders();
	}

	public Orders selectByid(String oid) {
		// TODO Auto-generated method stub
		return this.ordersBiz.selectByid(oid);
	}

	public String addOrders(Orders o) {
		// TODO Auto-generated method stub
		return this.ordersBiz.addOrders(o);
	}

	public List<Users> selectAllUsers() {
		// TODO Auto-generated method stub
		return this.usersBiz.selectAllUsers();
	}

	public Users selectById(int uid) {
		// TODO Auto-generated method stub
		return this.usersBiz.selectById(uid);
	}

	public String addUsers(Users o) {
		// TODO Auto-generated method stub
		return this.usersBiz.addUsers(o);
	}

	public List<Orderitem> selectAllOrderitem() {
		// TODO Auto-generated method stub
		return this.orderitemBiz.selectAllOrderitem();
	}

	public List<Orderitem> selectOrderitemById(String id) {
		// TODO Auto-generated method stub
		return this.orderitemBiz.selectOrderitemById(id);
	}

	public String addOrderitem(Orderitem oi) {
		// TODO Auto-generated method stub
		return this.orderitemBiz.addOrderitem(oi);
	}

	public List<Orders> selectOrdersByTime(Date d1, Date d2) {
		// TODO Auto-generated method stub
		return this.ordersBiz.selectOrdersByTime(d1, d2);
	}

	
	

	

	

	

	

	
}
