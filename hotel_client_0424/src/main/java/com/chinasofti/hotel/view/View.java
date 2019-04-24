package com.chinasofti.hotel.view;
public class View {
	/*--------------欢迎模块--------------------*/
	public void welcome(){
		System.out.println("******欢迎进入百味轩系统******" );
		System.out.println();
		System.out.println("1、我是尊贵客户");
		System.out.println("2、我是优秀员工");
		System.out.println("0、退出系统");
		System.out.println("-------------------");
	}
	/*--------------人工点餐服务模块--------------------*/
	//主页面
	public void welcomeE(){
		System.out.println("******人工点餐服务******" );
		System.out.println();
		System.out.println("1、用户点餐服务");
		System.out.println("2、查看点餐信息");
		System.out.println("3、更改点餐信息");
		System.out.println("4、订单支付操作");
		System.out.println("5、客户服务管理");
		System.out.println("6、超值积分兑换");
		System.out.println("0、退出系统");
		System.out.println("-------------------");
	}
	//更改点菜信息子页面
	public void updateF(){
		System.out.println("******更改点餐信息******" );
		System.out.println("1、继续添加菜品");
		System.out.println("2、修改菜品数量");
		System.out.println("3、取消指定菜品");
		System.out.println("0、返回上一级");
		System.out.println("-------------------");
	}
	//订单支付操作子页面
	public void applayF(){
		System.out.println("******订单支付方式******" );
		System.out.println("1、现金支付");
		System.out.println("2、会员支付");
		System.out.println("0、返回上一级");
		System.out.println("-------------------");
	}
	//客户服务管理子页面
	public void userF(){
		System.out.println("******客户服务管理******" );
		System.out.println("1、添加新会员");
		System.out.println("2、会员卡充值");
		System.out.println("3、会员卡挂失");
		System.out.println("4、会员卡解挂");
		System.out.println("0、返回上一级");
		System.out.println("-------------------");
	}
	//超值积分兑换子页面
	public void scoreF(){
		System.out.println("******超值积分兑换******" );
		System.out.println("编号\t所需积分\t礼品");
		System.out.println("1\t300\t紫菜蛋花汤一份");
		System.out.println("2\t500\t50元消费券");
		System.out.println("3\t1200\t100元充值券");
		System.out.println("4\t2000\t200元充值券");
		System.out.println();
		System.out.println("0、返回上一级");
		System.out.println("-------------------");
	}
	/*--------------后台管理模块--------------------*/
	//主页面
	public void welcomeM(){
		System.out.println("******后台管理系统******" );
		System.out.println();
		System.out.println("1、员工信息管理");
		System.out.println("2、菜品信息管理");
		System.out.println("3、会员信息管理");
		System.out.println("4、会员卡管理");
		System.out.println("5、销售信息统计");
		System.out.println("0、退出系统");
		System.out.println("-------------------");
	}
	//员工信息管理
	public void manEmp(){
		System.out.println("******员工信息管理******" );
		System.out.println();
		System.out.println("1、查看员工信息");
		System.out.println("2、增加员工信息");
		System.out.println("3、修改员工信息");
		System.out.println("4、删除员工信息");
		System.out.println("0、返回上一级");
		System.out.println("-------------------");
	}
	//菜品信息管理
	public void manFood(){
		System.out.println("******菜品信息管理******" );
		System.out.println();
		System.out.println("1、查看所有菜品");
		System.out.println("2、增加菜品信息");
		System.out.println("3、修改菜品信息");
		System.out.println("4、删除菜品信息");
		System.out.println("5、推出特价菜品");
		System.out.println("0、返回上一级");
		System.out.println("-------------------");
	}
	//会员信息管理
	public void manUser(){
		System.out.println("******会员信息管理******" );
		System.out.println();
		System.out.println("1、查看所有会员");
		System.out.println("2、增加会员信息");
		//System.out.println("3、修改会员信息");
		//System.out.println("4、删除会员信息");
		System.out.println("0、返回上一级");
		System.out.println("-------------------");
	}
	//会员卡管理
	public void manCard(){
		System.out.println("******会员卡管理******" );
		System.out.println();
		System.out.println("1、会员卡冻结");
		System.out.println("2、会员卡补办");
		System.out.println("3、优惠额度修改");
		System.out.println("0、返回上一级");
		System.out.println("-------------------");
	}
	//销售信息统计
		public void manMessage(){
			System.out.println("******销售信息统计******" );
			System.out.println();
			System.out.println("1、统计销售额");
			System.out.println("2、统计菜品销量");
			System.out.println("3、统计会员消费量");
			System.out.println("0、返回上一级");
			System.out.println("-------------------");
		}
		/*--------------客户自主点餐模块--------------------*/
		//主页面
		public void welcomeU(){
			System.out.println("******客户自主点餐******" );
			System.out.println();
			System.out.println("1、进行点菜");
			System.out.println("2、浏览点菜信息");
			System.out.println("3、修改点菜信息");//子页面：updateF()
			System.out.println("4、进行订单支付");//子页面：applayF()
			System.out.println("0、退出系统");
			System.out.println("-------------------");
		}
		public void applayU(){
			System.out.println("******订单支付方式******" );
			System.out.println("1、第三方支付");
			System.out.println("2、会员支付");
			System.out.println("0、返回上一级");
			System.out.println("-------------------");
		}
		
	public void println(String msg){

		System.out.println(msg);
	}



}

