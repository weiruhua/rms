package com.chinasofti.hotel.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import com.chinasofti.hotel.domain.Card;
import com.chinasofti.hotel.domain.Cart;
import com.chinasofti.hotel.domain.Employee;
import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Menu;
import com.chinasofti.hotel.domain.Orderitem;
import com.chinasofti.hotel.domain.Orders;
import com.chinasofti.hotel.domain.Users;
import com.chinasofti.hotel.util.UserInput;
import com.chinasofti.hotel.view.View;



public class Control {
	private View v;
	private UserInput ui;
	//创建业务层对象
	private RMSService service;
	public static final String IP="10.10.49.14";
	public static final int PORT=11111;
	public Control() {
		this.v = new View();
		this.ui = new UserInput();
		service=ProxyClient.getClient(RMSService.class, IP, PORT);

	}
	public void start(){
		while(true){
			//首页面---进行角色选择
			this.v.welcome();
			int select=this.ui.getInt("请选择：");
			if(select==0){
				this.v.println("系统终止");
				System.exit(0);
			}else if(select==1){
				//进入客户自主点餐模块
				loop:while(true){
					this.v.welcomeU();
					int se=this.ui.getInt("请选择：");
					if(se==0){
						this.v.println("系统终止");
						System.exit(0);
					}if(se==1){
						//进行点菜
						this.showFood();
						this.orderFood();

					}else if(se==2){
						//浏览点菜信息
						this.showCart();
					}else if(se==3){
						//修改点菜信息
						while(true){
							this.v.updateF();
							int sef=this.ui.getInt("请选择：");
							if(sef==0){
								break;
							}else if(sef==1){
								//继续添加菜品
								this.showFood();
								this.orderFood();
							}else if(sef==2){
								//修改菜品数量
								this.updateFoodNum();
							}else if(sef==3){
								//取消指定菜品
								this.deleteCart();
							}}
					}else if(se==4){
						//判断是否已经选购商品
						if(this.isSure()){
							//支付
							this.v.applayU();
							int sef=this.ui.getInt("请选择：");
							if(sef==0){
								break;
							}else if(sef==1){
								//第三方支付
								if(this.applyByThree()){
									break loop;
								}
							}else if(sef==2){
								//会员支付
								if(this.applyByCard()){
									break loop;
								}

							}
						}else{
							this.v.println("您还没有选购任何菜品，请先去点餐！");
						}

					}

				}

			}else if(select==2){
				Employee emp=null;
				emp=this.empLogin(emp);
				//人工点餐服务
				if(emp.getPow()==0){
					this.v.println("欢迎员工"+emp.getEmpName()+"登陆人工点餐系统！");
					while(true){
						this.v.welcomeE();
						int se=this.ui.getInt("请选择：");
						if(se==0){
							this.v.println("系统终止");
							System.exit(0);
						}else if(se==1){
							//展示菜品，进行点菜
							this.showFood();
							this.orderFood();
						}else if(se==2){
							//查看点餐信息
							this.showCart();
						}else if(se==3){
							//修改点餐信息
							while(true){
								this.v.updateF();
								int sef=this.ui.getInt("请选择：");
								if(sef==0){
									break;
								}else if(sef==1){
									//继续添加菜品
									this.showFood();
									this.orderFood();
								}else if(sef==2){
									//修改菜品数量
									this.updateFoodNum();
								}else if(sef==3){
									//取消指定菜品
									this.deleteCart();
								}}
						}else if(se==4){
							if(this.isSure()){
								//订单支付
								this.v.applayF();
								int sef=this.ui.getInt("请选择：");
								if(sef==0){
									break;
								}else if(sef==1){
									//现金支付
									this.applyByMoney(emp);
								}else if(sef==2){
									//会员支付
									this.applyByCard(emp);
								}
							}else{
								this.v.println("您还没有选购任何菜品，请先去点餐！");
							}

						}else if(se==5){
							while(true){
								//客户服务管理
								this.v.userF();
								int sef=this.ui.getInt("请选择：");
								if(sef==0){
									break;
								}else if(sef==1){
									//添加会员
									this.addUsers();
								}else if(sef==2){
									//会员卡充值
									this.addMoney();
								}else if(sef==3){
									//会员卡挂失
									this.guoshiCard();
								}else if(sef==4){
									//会员卡解挂
									this.jieguaCard();
								}}
						}else if(se==6){
							//积分兑换
							while(true){
								this.v.scoreF();
								int sef=this.ui.getInt("请选择：");
								if(sef==0){
									break;
								}else if(sef==1){
									//300--紫菜蛋花汤一份
									this.subScore(300);
								}else if(sef==2){
									//500--50元消费券
									this.subScore(500);
								}else if(sef==3){
									//1200--100元充值券
									this.subScore(1200);
								}else if(sef==4){
									//2000--200元充值券
									this.subScore(2000);
								}}
						}}
				}else if(emp.getPow()==1){
					this.v.println("欢迎经理"+emp.getEmpName()+"登陆后台管理系统！");
					//后台管理
					while(true){
						this.v.welcomeM();
						int se=this.ui.getInt("请选择：");
						if(se==0){
							this.v.println("系统终止");
							System.exit(0);
						}else if(se==1){
							//员工信息管理
							while(true){
								this.v.manEmp();
								int sef=this.ui.getInt("请选择：");
								if(sef==0){
									break;
								}else if(sef==1){
									//查看
									this.selectAllEmp();
								}else if(sef==2){
									//增加
									this.addEmployee();
								}else if(sef==3){
									//修改
									this.updateEmployee();
								}else if(sef==4){
									//删除
									this.deleteEmployee();
								}}
						}else if(se==2){
							//菜品信息管理
							while(true){
								this.v.manFood();
								int sef=this.ui.getInt("请选择：");
								if(sef==0){
									break;
								}else if(sef==1){
									//查看
									this.showFood();
								}else if(sef==2){
									//增加
									this.addFood();
								}else if(sef==3){
									//修改
									this.updateFood();
								}else if(sef==4){
									//删除
									this.deleteFood();
								}else if(sef==5){
									//推出特价菜
									this.discountFood();
								}}
						}else if(se==3){
							//会员信息管理
							while(true){
								this.v.manUser();
								int sef=this.ui.getInt("请选择：");
								if(sef==0){
									break;
								}else if(sef==1){
									//查看
									this.showUsers();
								}else if(sef==2){
									//增加
									this.addUsers();
								}}
						}else if(se==4){
							//会员卡管理
							while(true){
								this.v.manCard();
								int sef=this.ui.getInt("请选择：");
								if(sef==0){
									break;
								}else if(sef==1){
									//冻结
									this.dongjieCard();
								}else if(sef==2){
									//补办
									this.replaceCard();
								}else if(sef==3){
									//优惠额度修改
									this.changeDiscount();
								}}
						}else if(se==5){
							//销售信息统计
							while(true){
								this.v.manMessage();
								int sef=this.ui.getInt("请选择：");
								if(sef==0){
									break;
								}else if(sef==1){
									//统计销售额
									this.countSale();
								}else if(sef==2){
									//统计菜品销量
									this.countFood();
								}else if(sef==3){
									//统计会员消费量
								}}
						}
					}
				}
			}
		}


	}

	/*---------------客户自主选餐的方法-------------------*/
	//浏览所有菜品信息
	private void showFood() {
		try{ 
			//浏览所有菜品信息
			List<Food> list = this.service.selectAllFood();
			System.out.println("菜品编号\t菜品分类\t菜品名称\t菜品价格\t是否特价");
			for (Food ff : list) {
				System.out.println(ff);
			}
		}catch(Exception e){  
			System.out.println("无数据");  
		}  

	}
	//开始点菜
	public void orderFood(){
		while(true){
			int fid = this.ui.getInt("请选择菜品编号：");
			int fnum = this.ui.getInt("请输入菜品数量：");
			Cart c1 = this.service.selectCartById(fid);
			if(c1==null){
				String msg = this.service.addCart(new Cart(this.service.selectFoodById(fid), fnum));
				System.out.println(msg);
			}else{
				String ss = this.ui.getString("您已订购"+c1.getNum()+"份该菜品，是否继续订购此菜品（y/n）");
				if("y".equals(ss)){
					c1.setNum(c1.getNum()+fnum);
					this.service.updateCart(c1, c1.getNum());
					System.out.println("加购成功！");
				}else{

				}
			}
			String sel = this.ui.getString("是否继续点餐（y/n）");
			if("n".equals(sel)){
				break;
			}

		}
	}
	//浏览点菜信息
	private void showCart() {
		try{ 
			List<Cart> list = this.service.showCart();	
			if(list.size()>0){
				System.out.println("菜品编号\t菜品名称\t菜品单价\t菜品数量\t总金额");
				for (Cart cart : list) {
					System.out.println(cart);
				}
			}else{
				this.v.println("您还没有选择任何菜品，请先去点餐！");
			}

		}catch(Exception e){  
			System.out.println("您还没有点餐哦！");  
		}  

	}
	//修改购物车内菜品数量
	private void updateFoodNum() {
		while(true){
			int fid = this.ui.getInt("请选择菜品编号：");
			Cart c1 = this.service.selectCartById(fid);
			if(c1==null){
				System.out.println("对不起，您没有选择过该菜品！");
			}else{
				int fnum = this.ui.getInt("请输入菜品数量：");
				this.v.println(this.service.updateCart(c1, fnum));
			}

			String sel = this.ui.getString("是否继续修改（y/n）");
			if("n".equals(sel)){
				break;
			}
		}

	}
	//取消购物车内菜品
	private void deleteCart() {
		while(true){
			int fid = this.ui.getInt("请选择菜品编号：");
			//查询是否有该菜品
			Cart c1 = this.service.selectCartById(fid);
			if(c1!=null){
				String msg = this.service.deleteCart(fid);
				System.out.println(msg);
			}else{
				this.v.println("您没有订购此商品");
			}
			String sel = this.ui.getString("是否继续取消菜品（y/n）");
			if("n".equals(sel)){
				break;
			}
		}

	}
	//判断用户是否有条件进行支付
	private boolean isSure() {
		List<Cart> list = this.service.showCart();
		if(list.size()>0){
			return true;
		}
		return false;


	}
	//客户自主点餐--会员卡支付
	private boolean applyByCard() {
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		SimpleDateFormat bf = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
		//uuid生成订单号
		UUID uid=UUID.randomUUID();
		String ono=uid.toString().replaceAll("-", "");
		
		double sum = this.apply();
		System.out.println();
		int cno = this.ui.getInt("请输入会员卡号：");
		Card card = this.service.selectCardById(cno);
		Date birth = card.getU1().getBirth();
		//判断会员卡状态
		List<Cart> cart = this.service.showCart();
		if(card.getStatus()!=0){
			this.v.println("该会员卡处于挂失/冻结状态，无法完成支付！");
		}else{
			//判断会员卡余额
			if(card.getMoney()>=sum){
				this.v.println("----------支付成功，订单信息如下-----------");
				System.out.println();
				//订单信息
				this.v.println("\t百味轩餐厅1号店（欢迎光临）");
				this.v.println("=====================================");
				this.v.println("门店号：Z001\t"+df.format(new Date()));
				this.v.println("订单号："+ono);
				this.v.println("收银机号:Z006"+"\t\t交易类型：零售销售");
				this.v.println("-------------------------------------");
				this.apply();
				this.v.println("会员优惠金额："+sum*(1-card.getDiscount()));
				this.v.println("实收金额：\t"+sum*card.getDiscount());
				this.v.println("-------------------------------------");
				this.v.println("会员卡号："+"\t\t"+cno);
				this.v.println("本次积分："+"\t\t"+Math.floor(sum*card.getDiscount()));
				//增加数据库内会员积分
				this.service.subScore(card, (-(int)(Math.floor(sum*0.9))));
				this.v.println("可用积分："+"\t\t"+(card.getScore()+(int)(Math.floor(sum*card.getDiscount()))));
				this.v.println("卡消费："+"\t\t"+sum*card.getDiscount());
				//减少会员卡余额
				this.service.addMoney(card, (-sum*card.getDiscount()));
				this.v.println("可用余额："+"\t\t"+(card.getMoney()-sum*card.getDiscount()));
				this.v.println("=====================================");
				this.v.println("\t  谢谢惠顾，祝您生活愉快！");
				System.out.println();

				//会员生日判断
				if((birth.getMonth())==(new Date().getMonth())&&(birth.getDate())==(new Date().getDate())){
					this.v.println("今天是您的生日，您将获得我们为您准备的精美礼品一份，祝您生日快乐！");
				}
				System.out.println();
				//将记录插入至订单表

				this.service.addOrders(new Orders(ono, new Employee(), card, new Date(), sum*card.getDiscount()));
				try{ 
					//将记录插入至订单项表

					for (Cart c : cart) {
						this.service.addOrderitem(new Orderitem(new Orders(ono, new Employee(), card, new Date(), sum*card.getDiscount()), c.getF(), c.getNum()));
					}
				}catch(Exception e){  
					System.out.println("无数据");  
				}  
				this.service.clearCart(); 
				this.v.println("5秒后退出该页面");
				System.out.println();
				for (int i = 5; i>0; i--) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}  //1000毫秒就是1秒
					System.out.print(" ");
				}
				return true;
			}else{
				this.v.println("对不起，会员卡内余额不足，请更换支付方式！");

			}
		}
		return false;


	}
	//预订单
	private double apply() {
		int num=0;
		double sum=0;
		double discount=0;
		List<Cart> list = this.service.showCart();
		if(list.size()==0){
			this.v.println("您还没有选购菜品，请先去点餐！");
		}else{
			this.v.println("品名\t数量\t单价\t总额");
			this.v.println("-------------------------------------");
			for (Cart c : list) {
				if("y".equals((c.getF().getStatus()))){
					System.out.println(c.getF().getFname()+"\t"+c.getNum()+"\t"+c.getF().getPrice()*0.8+"\t"+c.getNum()*c.getF().getPrice()*0.8);
					System.out.println("\t原售价\t"+c.getF().getPrice());
					this.v.println("");
					num+=c.getNum();
					sum+=c.getNum()*c.getF().getPrice()*0.8;
					discount+=c.getNum()*c.getF().getPrice()*(1-0.8);
				}else{

					System.out.println(c.getF().getFname()+"\t"+c.getNum()+"\t"+c.getF().getPrice()+"\t"+c.getNum()*c.getF().getPrice());
					num+=c.getNum();
					sum+=c.getNum()*c.getF().getPrice();
				}

			}
			this.v.println("-------------------------------------");
			System.out.println("合计\t"+num+"\t\t"+sum);
			System.out.println("优惠金额：\t"+String.format("%.2f", discount));
		}
		return sum;
	}
	//第三方支付
	private boolean applyByThree() {
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//uuid生成订单号
		UUID uid=UUID.randomUUID();
		String ono=uid.toString().replaceAll("-", "");
		
		double sum = this.apply();
		String sel = this.ui.getString("是否确认支付（y/n）");
		if("y".equals(sel)){
			this.v.println("-----------支付成功，订单信息如下------------");
			System.out.println();
			//订单信息
			this.v.println("\t百味轩餐厅1号店（欢迎光临）");
			this.v.println("=====================================");
			this.v.println("门店号：Z001\t"+df.format(new Date()));
			this.v.println("订单号："+ono);
			this.v.println("收银员:第三方支付"+"\t\t交易类型：零售销售");
			this.v.println("-------------------------------------");
			this.apply();
			this.v.println("实收金额：\t"+sum);
			this.v.println("找零：\t 0");
			this.v.println("=====================================");
			this.v.println("\t  谢谢惠顾，祝您生活愉快！");
			System.out.println();
			System.out.println();
			try{ 
				//将记录插入至订单表
				this.service.addOrders(new Orders(ono, new Employee(), new Card(), new Date(), sum));
				//将记录插入至订单项表
				List<Cart> cart = this.service.showCart();
				for (Cart c : cart) {
					this.service.addOrderitem(new Orderitem(new Orders(ono, new Employee(), new Card(), new Date(), sum), c.getF(), c.getNum()));
				}
			}catch(Exception e){  
				System.out.println("无数据");  
			} 
			this.service.clearCart();
			this.v.println("5秒后退出该页面");
			System.out.println();
			for (int i = 5; i>0; i--) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  //1000毫秒就是1秒
				System.out.print(" ");
			}
			return true;

		}else{
			this.v.println("取消操作成功！");
			return false;
		}

	}
	/*---------------人工服务选餐的方法-------------------*/
	//员工登陆
	private Employee empLogin(Employee emp) {
		while(true){
			String account = this.ui.getString("请输入员工账户");
			String password = this.ui.getString("请输入员工密码");
			emp = this.service.login(account, password);
			if(emp!=null){
				break;
			}
			System.out.println("账户密码输入有误！");
		}
		return emp;
	}
	//会员卡充值
	private void addMoney() {
		this.v.println("充值优惠活动：每月28日，充200返50");
		int y,m,d;    
		Calendar cal=Calendar.getInstance();    
		y=cal.get(Calendar.YEAR);    
		m=cal.get(Calendar.MONTH);    
		d=cal.get(Calendar.DATE);    

		int cno = this.ui.getInt("请输入会员卡卡号：");
		Card c1 = this.service.selectCardById(cno);
		if(c1==null){
			System.out.println("卡号输入有误！");
		}else{
			//判断会员卡状态
			if(c1.getStatus()!=0){
				this.v.println("对不起，该会员卡处于挂失/冻结状态，无法进行充值操作");
			}else{
				double money = this.ui.getDouble("请输入充值金额：");
				if(0<money&&money<=1000){
					if(d==28){
						System.out.println("今天是"+y+"年"+m+"月"+d+"日,您可以享受充值优惠！");
						money+=(int)(money/200)*50;
					}
					this.service.addMoney(c1, money);
					this.v.println("成功充值"+money+"元,当前余额为"+(c1.getMoney()+money)+"元!");
				}else{
					this.v.println("金额输入有误，最高可充值1000元！");
				}
			}

		}

	}
	//会员卡挂失
	private void guoshiCard() {
		int cno = this.ui.getInt("请输入会员卡卡号：");
		Card c1 = this.service.selectCardById(cno);
		if(c1==null){
			System.out.println("卡号输入有误！");
		}else{
			if(c1.getStatus()==1){
				this.v.println("该会员卡已处于挂失状态！");
			}else if(c1.getStatus()==2){
				this.v.println("该会员卡处于冻结状态，无法进行挂失操作！");
			}else{
				this.v.println("请核实用户信息：");
				this.v.println("用户名\t会员卡号");
				this.v.println(c1.getU1().getUname()+"\t"+cno);
				String ss = this.ui.getString("用户信息是否正确（y/n）");
				if("y".equals(ss)){
					this.v.println(this.service.changeCardStatus(c1, 1));
				}
			}

		}


	}
	//会员卡解挂
	private void jieguaCard() {
		int cno = this.ui.getInt("请输入会员卡卡号：");
		Card c1 = this.service.selectCardById(cno);
		if(c1==null){
			System.out.println("卡号输入有误！");
		}else {
			if(c1.getStatus()==0){
				this.v.println("该会员卡未被挂失，请核实卡号！");
			}else if(c1.getStatus()==2){
				this.v.println("该会员卡处于冻结状态，无法进行此操作！");
			}else{
				this.v.println("请核实用户信息：");
				this.v.println("用户名\t会员卡号");
				this.v.println(c1.getU1().getUname()+"\t"+cno);
				String ss = this.ui.getString("用户信息是否正确（y/n）");
				if("y".equals(ss)){
					this.v.println(this.service.changeCardStatus(c1, 0));
				}

			}
		}

	}
	//积分兑换
	private void subScore(int i) {
		while(true){
			int cno = this.ui.getInt("请输入会员卡号：");
			Card c1=this.service.selectCardById(cno);
			if(c1==null){
				System.out.println("卡号输入错误！");
				break;
			}else{
				if(c1.getStatus()==0){
					if(c1.getScore()<i){
						System.out.println("对不起，当前卡内积分为"+c1.getScore()+",不足以兑换该商品！");
					}else{
						this.service.subScore(c1, i);
						System.out.println("您已成功兑换该商品，当前卡内积分为"+(c1.getScore()-i));
					}
					break;
				}else{
					this.v.println("对不起，该会员卡处于挂失/冻结状态，无法进行此操作");
					break;
				}
			}

		}

	}
	//现金支付
	private void applyByMoney(Employee emp) {
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//uuid生成订单号
		UUID uid=UUID.randomUUID();
		String ono=uid.toString().replaceAll("-", "");
		
		double sum = this.apply();
		System.out.println();
		double money = this.ui.getDouble("请输入支付金额：");
		if(money>=sum){
			this.v.println("-----------支付成功，订单信息如下------------");
			System.out.println();
			//订单信息
			this.v.println("\t百味轩餐厅1号店（欢迎光临）");
			this.v.println("=====================================");
			this.v.println("门店号：Z001\t"+df.format(new Date()));
			this.v.println("订单号："+ono);
			this.v.println("收银员:"+emp.getEmpId()+"\t\t交易类型：零售销售");
			this.v.println("-------------------------------------");
			this.apply();
			this.v.println("实收金额：\t"+money);
			this.v.println("找零：\t"+(money-sum));
			this.v.println("=====================================");
			this.v.println("\t  谢谢惠顾，祝您生活愉快！");
			this.service.clearCart();
			System.out.println();
			System.out.println();
			this.service.addOrders(new Orders(ono, emp,new Card(), new Date(), sum));
			//将记录插入至订单项表
			List<Cart> cart = this.service.showCart();
			for (Cart c : cart) {
				this.service.addOrderitem(new Orderitem(new Orders(ono, emp, new Card(), new Date(), sum), c.getF(), c.getNum()));
			}
		}else{
			this.v.println("金额不足以支付此订单！");
		}


	}
	//人工点餐--会员卡支付
	private void applyByCard(Employee emp) {
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//uuid生成订单号
		UUID uid=UUID.randomUUID();
		String ono=uid.toString().replaceAll("-", "");
		
		double sum = this.apply();
		System.out.println();
		int cno = this.ui.getInt("请输入会员卡号：");
		Card card = this.service.selectCardById(cno);
		Date birth = card.getU1().getBirth();
		if(card.getStatus()!=0){
			this.v.println("该会员卡处于挂失/冻结状态，无法完成支付！");
		}else{
			//判断会员卡余额
			if(card.getMoney()>=sum){
				this.v.println("----------支付成功，订单信息如下-----------");
				System.out.println();
				//订单信息
				this.v.println("\t百味轩餐厅1号店（欢迎光临）");
				this.v.println("=====================================");
				this.v.println("门店号：Z001\t"+df.format(new Date()));
				this.v.println("订单号："+ono);
				this.v.println("收银员:"+emp.getEmpId()+"\t\t交易类型：零售销售");
				this.v.println("-------------------------------------");
				this.apply();
				this.v.println("会员优惠金额："+sum*(1-card.getDiscount()));
				this.v.println("实收金额：\t"+sum*card.getDiscount());
				this.v.println("-------------------------------------");
				this.v.println("会员卡号："+"\t\t"+cno);
				this.v.println("本次积分："+"\t\t"+Math.floor(sum*card.getDiscount()));
				//增加数据库内会员积分
				this.service.subScore(card, (-(int)(Math.floor(sum*card.getDiscount()))));
				this.v.println("可用积分："+"\t\t"+(card.getScore()+(int)(Math.floor(sum*card.getDiscount()))));
				this.v.println("卡消费："+"\t\t"+sum*card.getDiscount());
				//减少会员卡余额
				this.service.addMoney(card, (-sum*card.getDiscount()));
				this.v.println("可用余额："+"\t\t"+(card.getMoney()-sum*card.getDiscount()));
				this.v.println("=====================================");
				this.v.println("\t  谢谢惠顾，祝您生活愉快！");
				this.service.clearCart();
				System.out.println();
				//会员生日判断
				if((birth.getMonth())==(new Date().getMonth())&&(birth.getDate())==(new Date().getDate())){
					this.v.println("今天是您的生日，您将获得我们为您准备的精美礼品一份，祝您生日快乐！");
				}
				System.out.println();

				this.service.addOrders(new Orders(ono, emp,card, new Date(), sum*card.getDiscount()));
				//将记录插入至订单项表
				List<Cart> cart = this.service.showCart();
				for (Cart c : cart) {
					this.service.addOrderitem(new Orderitem(new Orders(ono, emp, card, new Date(), sum*card.getDiscount()), c.getF(), c.getNum()));
				}
			}else{
				this.v.println("对不起，会员卡内余额不足，请更换支付方式！");
			}

		}


	}
	/*---------------后台管理的方法-------------------*/
	//查看所有员工
	private void selectAllEmp() {
		List<Employee> list = this.service.selectAllEmployee();
		this.v.println("员工编号\t员工姓名\t员工账号\t员工密码\t员工权限");
		for (Employee emp : list) {
			System.out.println(emp);
		}

	}
	//增加员工
	private void addEmployee() {
		while(true){
			int empId =0;
			List<Employee> list = this.service.selectAllEmployee();
			for (Employee emp : list) {
				if(emp.getEmpId()>empId){
					empId=emp.getEmpId();
				}
			}
			String empName = this.ui.getString("请输入员工姓名：");
			String empAccount = this.ui.getString("请输入员工账户：");
			String empPassword = this.ui.getString("请输入员工密码：");
			int pow = this.ui.getInt("请输入员工权限（0--普通员工，1--经理）：");
			if(pow!=0&&pow!=1){
				this.v.println("员工权限输入有误！");
				break;
			}
			this.v.println(this.service.addEmployee(new Employee((empId+1), empName, empAccount, empPassword, pow)));
			String ss = this.ui.getString("是否继续添加员工（y/n）");
			if("n".equals(ss)){
				this.v.println("退出成功！");
				break;
			}
		}


	}
	//修改员工信息
	private void updateEmployee() {
		while(true){
			int empId = this.ui.getInt("请输入要修改员工编号：");
			Employee emp = this.service.selectEmployeeByid(empId);
			if(emp==null){
				this.v.println("员工编号输入有误！");
				break;
			}else{
				System.out.println(emp);
				String empName = this.ui.getString("请输入员工姓名：");
				String empAccount = this.ui.getString("请输入员工账户：");
				String empPassword = this.ui.getString("请输入员工密码：");
				int pow = this.ui.getInt("请输入员工权限（0--普通员工，1--经理）：");
				if(pow!=0&&pow!=1){
					this.v.println("员工权限输入有误！");
					break;
				}
				String s = this.service.updateEmployee(new Employee(empId, empName, empAccount, empPassword, pow));
				this.v.println(s);
				String ss = this.ui.getString("是否继续修改员工（y/n）");
				if("n".equals(ss)){
					this.v.println("退出成功！");
					break;
				}
			}
		}



	}
	//删除员工
	private void deleteEmployee() {
		while(true){
			int empId = this.ui.getInt("请输入要删除员工编号：");
			Employee emp = this.service.selectEmployeeByid(empId);
			if(emp==null){
				this.v.println("员工编号输入有误！");
				break;
			}else{
				System.out.println(emp);
				String s = this.ui.getString("是否确认删除该员工（y/n）");
				if("n".equals(s)){
					this.v.println("取消操作成功！");
					break;
				}else if("y".equals(s)){
					this.v.println(this.service.deleteEmployee(empId));
				}else{
					this.v.println("指令输入有误！");
					break;
				}
				String ss = this.ui.getString("是否继续删除员工（y/n）");
				if("n".equals(ss)){
					this.v.println("退出成功！");
					break;
				}
			}

		}
	}
	//增加菜品
	private void addFood() {

		while(true){
			//查找已存在菜品最大id
			int fid=0;
			List<Food> list = this.service.selectAllFood();
			for (Food f : list) {
				if(f.getFid()>fid){
					fid=f.getFid();
				}
			}
			List<Menu> menulist = this.service.selectAllMenu();
			this.v.println("菜品分类信息如下：");
			for (Menu m : menulist) {
				System.out.println(m);
			}
			int menuno = this.ui.getInt("请输入菜品分类：");
			String fname = this.ui.getString("请输入要添加的菜品名：");
			Food fo = this.service.selectFoodByFname(fname);
			if(fo!=null){
				this.v.println("该菜品已存在！");
				break;
			}else if(fo==null){
				double price = this.ui.getDouble("请输入菜品价格：");
				String status = this.ui.getString("请决定是否为特价菜（y/n）：");
				//查找菜品分类
				Menu me1=new Menu();
				for (Menu me : menulist) {
					if(me.getMenuId()==menuno){
						me1=me;
					}

				}
				this.v.println(this.service.addFood(new Food(fid+1, me1, fname, price, status)));
				String ss = this.ui.getString("是否继续添加菜品（y/n）");
				if("n".equals(ss)){
					this.v.println("退出成功！");
					break;
				}
			}

		}



	}
	//修改菜品
	private void updateFood() {
		while(true){
			int fid = this.ui.getInt("请输入菜品id：");
			Food food = this.service.selectFoodById(fid);
			if(food==null){
				this.v.println("菜品id输入有误！");
				break;
			}
			this.v.println("菜品信息如下：");
			this.v.println("菜品编号\t菜品分类\t菜品名称\t菜品价格\t是否特价");
			System.out.println(food);
			String fname = this.ui.getString("请输入修改后菜品名称：");
			double price = this.ui.getDouble("请输入修改后菜品价格：");
			String status = this.ui.getString("请决定是否为特价菜（y/n）：");
			this.v.println(this.service.updateFood(new Food(fid, food.getM1(), fname, price, status)));
			String ss = this.ui.getString("是否继续修改菜品（y/n）");
			if("n".equals(ss)){
				this.v.println("退出成功！");
				break;
			}
		}


	}
	//删除菜品
	private void deleteFood() {
		while(true){
			int fid = this.ui.getInt("请输入菜品id：");
			Food food = this.service.selectFoodById(fid);
			if(food==null){
				this.v.println("菜品id输入有误！");
				break;
			}
			this.v.println("菜品信息如下：");
			this.v.println("菜品编号\t菜品分类\t菜品名称\t菜品价格\t是否特价");
			System.out.println(food);
			String s = this.ui.getString("是否确认删除该菜品（y/n）");
			if("n".equals(s)){
				this.v.println("取消操作成功！");
				break;
			}else if("y".equals(s)){
				this.v.println(this.service.deleteFood(fid));
			}else{
				this.v.println("指令输入有误！");
				break;
			}
			String ss = this.ui.getString("是否继续删除菜品（y/n）");
			if("n".equals(ss)){
				this.v.println("退出成功！");
				break;
			}
		}

	}
	//推出特价菜
	private void discountFood() {
		while(true){
			int fid = this.ui.getInt("请输入菜品id：");
			Food food = this.service.selectFoodById(fid);
			if(food==null){
				this.v.println("菜品id输入有误！");
				break;
			}
			this.v.println("菜品信息如下：");
			this.v.println("菜品编号\t菜品分类\t菜品名称\t菜品价格\t是否特价");
			System.out.println(food);
			if("y".equals(food.getStatus())){
				String sel = this.ui.getString("该菜品是特价菜，是否恢复原价（y/n）:");
				if("y".equals(sel)){
					food.setStatus("n");
				}else{
					this.v.println("取消操作成功！");
					break;
				}
			}else{
				String sel = this.ui.getString("该菜品不是特价菜，是否设置为特价菜（y/n）:");
				if("y".equals(sel)){
					food.setStatus("y");
				}else{
					this.v.println("取消操作成功！");
					break;
				}
			}
			this.v.println(this.service.discountFood(fid));
			String ss = this.ui.getString("是否继续设置特价菜（y/n）");
			if("n".equals(ss)){
				this.v.println("退出成功！");
				break;
			}
		}

	}
	//查看所有会员
	private void showUsers() {
		try {
			List<Users> list = this.service.selectAllUsers();
			this.v.println("会员信息如下：");
			System.out.println("用户编号\t用户名称\t电话号码\t出生日期");
			for (Users u : list) {
				System.out.println(u);
			}
		} catch (Exception e) {
			System.out.println("暂无会员信息！");
		}
	}
	//增加新会员
	private void addUsers(){
		while(true){
			int id=0;
			List<Users> list = this.service.selectAllUsers();
			for (Users u : list) {
				if(u.getUsid()>id){
					id=u.getUsid();
				}
			}
			String name = this.ui.getString("请输入会员姓名：");
			String tel = this.ui.getString("请输入电话号码：");
			for (Users u : list) {
				if(u.getTel()==tel){
					this.v.println("该用户已存在！");
					System.out.println(u);
					break;
				}
			}
			Date birth = this.ui.getDate("请输入出生日期（按照yyyy-mm-dd格式）：");
			double money = this.ui.getDouble("请输入会员卡充值金额：");
			this.v.println(this.service.addUsers(new Users((id+1), name, tel, birth)));
			//获取新的会员卡号
			int cno=0;
			List<Card> listCard = this.service.selectAllCard();
			for (Card c : listCard) {
				if(c.getCno()>cno){
					cno=c.getCno();
				}
			}
			this.service.addCard(new Card((cno+1), new Users((id+1), name, tel, birth), 0, money, 1, 0));
			this.v.println("用户会员卡号为："+(cno+1));

			String se = this.ui.getString("是否继续添加会员（y/n）");
			if("n".equals(se)){
				break;
			}
		}

	}
	//会员卡冻结
	private void dongjieCard() {
		int cno = this.ui.getInt("请输入会员卡卡号：");
		Card c1 = this.service.selectCardById(cno);
		if(c1==null){
			System.out.println("卡号输入有误！");
		}else{
			if(c1.getStatus()==2){
				this.v.println("该会员卡已处于冻结状态！");
			}else if(c1.getStatus()==1){
				String s = this.ui.getString("该会员卡处于挂失状态，是否进行冻结操作（y/n）");
				if("y".equals(s)){
					this.v.println(this.service.changeCardStatus(c1, 2));
				}
			}else{
				this.v.println("请核实用户信息：");
				this.v.println("用户名\t会员卡号");
				this.v.println(c1.getU1().getUname()+"\t"+cno);
				String ss = this.ui.getString("用户信息是否正确（y/n）");
				if("y".equals(ss)){
					this.v.println(this.service.changeCardStatus(c1, 2));
				}
			}

		}
	}

	//会员卡补办
	private void replaceCard() {
		int cno = this.ui.getInt("请输入会员卡号：");
		Card card = this.service.selectCardById(cno);
		this.v.println("请核实用户信息：");
		this.v.println("用户姓名\t会员卡号");
		System.out.println(card.getU1().getUname()+"\t"+cno);
		String sel = this.ui.getString("是否补办（y/n）");
		if("y".equals(sel)){
			//获得新的会员卡号
			int cno2=0;
			List<Card> list = this.service.selectAllCard();
			for (Card c : list) {
				if(c.getCno()>cno2){
					cno2=c.getCno();
				}
			}
			card.setStatus(0);
			this.service.replaceCard(card, cno2+1);
			this.v.println("补办成功，新卡信息如下：");
			this.v.println("会员卡号\t用户姓名\t积分\t余额\t优惠额度\t状态");
			System.out.println(this.service.selectCardById(cno2+1));
		}
	}
	//会员卡优惠额度修改
	private void changeDiscount() {
		double discount = this.ui.getDouble("请输入会员卡优惠额度（0~1）：");
		this.v.println(this.service.changeCardDiscount(discount));

	}
	//统计销售额
	private void countSale() {
		String beginTime = this.ui.getString("请输入起始时间（yyyy-mm-dd）：");
		String endTime = this.ui.getString("请输入结束时间（yyyy-mm-dd）：");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date utilDate1;
		Date utilDate2;
		try {
			utilDate1 = sdf.parse(beginTime);
			utilDate2 = sdf.parse(endTime);
			Date date1= new java.sql.Date(utilDate1.getTime());
			Date date2 = new java.sql.Date(utilDate2.getTime());
			List<Orders> list = this.service.selectOrdersByTime(date1, date2);
			if(list.size()==0){
				this.v.println("没有数据");
			}else{
				double sum=0;
				int num=0;
				System.out.println();
				System.out.println();
				this.v.println(beginTime+" ~ "+endTime+"期间销售订单信息如下：");
				this.v.println("序号\t\t\t订单号\t\t\t消费总额");
				for (Orders ord : list) {
					System.out.println((num+1)+"\t"+ord.getOid()+"\t"+ord.getTotal());
					num++;
					sum+=ord.getTotal();
				}
				this.v.println("------------------------------------------------");
				this.v.println("合计："+"\t\t\t"+num+"\t\t\t"+sum);
				System.out.println();
				System.out.println();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//统计菜品销量
	private void countFood() {
		String beginTime = this.ui.getString("请输入起始时间（yyyy-mm-dd）：");
		String endTime = this.ui.getString("请输入结束时间（yyyy-mm-dd）：");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date utilDate1;
		Date utilDate2;
		try {
			utilDate1 = sdf.parse(beginTime);
			utilDate2 = sdf.parse(endTime);
			Date date1= new java.sql.Date(utilDate1.getTime());
			Date date2 = new java.sql.Date(utilDate2.getTime());
			List<Orders> list = this.service.selectOrdersByTime(date1, date2);
			if(list.size()==0){
				this.v.println("没有数据");
			}else{
				int num=1;
				//建立新集合存储菜品信息
				Map<Food,Integer> map=new HashMap<Food,Integer>();
				System.out.println();
				System.out.println();
				this.v.println(beginTime+" ~ "+endTime+"期间菜品销售信息如下：");
				this.v.println("序号\t菜品编号\t菜品名称\t菜品单价\t销售数量");
				for (Orders ord : list) {
					//查找订单项
					List<Orderitem> listoi = this.service.selectOrderitemById(ord.getOid());
					for (Orderitem oi : listoi) {
						boolean b=false;
						Set<Food> key = map.keySet();
						//重复菜品数量相加
						for (Food food : key) {
							if(food.getFname().equals(oi.getF1().getFname())){
								map.replace(food,map.get(food)+oi.getNum());
								b=true;
							}
						}
						//不重复菜品，添加至map集合
						if(!b){
							map.put(oi.getF1(), oi.getNum());
						}	
					}
				}

				List<Map.Entry<Food, Integer>> listcount = new ArrayList<Map.Entry<Food, Integer>>(map.size()); 
				listcount.addAll(map.entrySet()); 
				// 通过Collections.sort()排序 
				Collections.sort(listcount, new Comparator<Map.Entry<Food, Integer>>() { 
					public int compare(Map.Entry<Food, Integer> o1, Map.Entry<Food, Integer> o2) { 
						// compareTo方法 (x < y) ? -1 : ((x == y) ? 0 : 1) 
						return o2.getValue().compareTo(o1.getValue()); 
					}; 
				}); 
				for (Entry<Food, Integer> entry : listcount){ 
					// 得到排序后的键值 
					this.v.println((num++)+"\t"+entry.getKey().getFid()+"\t"+entry.getKey().getFname()+"\t"+entry.getKey().getPrice()+"\t"+entry.getValue()); }
			}
			
			this.v.println("------------------------------------------------");

			System.out.println();
			System.out.println();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
