package com.chinasofti.hotel.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.chinasofti.hotel.domain.Card;
import com.chinasofti.hotel.domain.Cart;
import com.chinasofti.hotel.domain.Employee;
import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Menu;
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
				while(true){
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
						//支付
						this.v.applayU();
						int sef=this.ui.getInt("请选择：");
						if(sef==0){
							break;
						}else if(sef==1){
							//第三方支付
							this.applyByThree();
							System.exit(0);
						}else if(sef==2){
							//会员支付
							this.applyByCard();
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
								}else if(sef==2){
									//统计菜品销量
								}else if(sef==3){
									//统计会员消费量
								}}
						}
					}
				}
			}
		}


	}





	//客户自主点餐--会员卡支付
	private void applyByCard() {
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
		/*//获取用户生日和当前日期
		System.out.println(birth.getMonth()+1);
		System.out.println(new Date().getMonth()+1);
		System.out.println(birth.getDate());
		
		System.out.println(new Date().getDate());*/
		
		//判断会员卡状态
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
				//this.service.addOrders(new Orders(ono, new Employee(), card, new Date(), sum*0.9));
			}else{
				this.v.println("对不起，会员卡内余额不足，请更换支付方式！");
			}
		}
		


	}
	//第三方支付
	private void applyByThree() {
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

		}else{
			this.v.println("取消操作成功！");
		}

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
			System.out.println();
			System.out.println();
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
				System.out.println();
				//会员生日判断
				if((birth.getMonth())==(new Date().getMonth())&&(birth.getDate())==(new Date().getDate())){
					this.v.println("今天是您的生日，您将获得我们为您准备的精美礼品一份，祝您生日快乐！");
				}
				System.out.println();
			}else{
				this.v.println("对不起，会员卡内余额不足，请更换支付方式！");
			}

		}
		

	}
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
			System.out.println("菜品编号\t菜品名称\t菜品单价\t菜品数量\t总金额");
			for (Cart cart : list) {
				System.out.println(cart);
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
			}
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
	//增加会员信息
	private void addUsers(){
		int id=0;
		List<Users> list = this.service.selectAllUsers();
		for (Users u : list) {
			if(u.getUsid()>id){
				id=u.getUsid();
			}
		}
		String name = this.ui.getString("请输入会员姓名：");
		String tel = this.ui.getString("请输入电话号码：");
		Date birth = this.ui.getDate("请输入出生日期（按照2019-01-01格式）：");
		this.v.println(this.service.addUsers(new Users((id+1), name, tel, birth)));
	}



	//会员卡冻结
	private void dongjieCard() {
		int cno = this.ui.getInt("请输入会员卡卡号：");
		Card c1 = this.service.selectCardById(cno);
		if(c1==null){
			System.out.println("卡号输入有误！");
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
}
