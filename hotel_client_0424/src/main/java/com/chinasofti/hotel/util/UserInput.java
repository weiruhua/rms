package com.chinasofti.hotel.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserInput {
	//接收用户输入的内容String
	public String getString(String msg){
		System. out . println(msg);
		Scanner sc=new Scanner(System. in);
		return sc.next();

	}
	//接收整数
	public int getInt(String msg){
		while(true){
			try {
				System. out . println(msg);
				Scanner sc=new Scanner(System. in);
				return sc. nextInt();
			} catch (Exception e) {
				System. out. println("输入内容格式不正确，请输入整数类型! ");

			}
		}
	}
	//接收浮点数
	public double getDouble(String msg){
		while(true){
			try {
				System. out . println(msg);
				Scanner sc=new Scanner(System. in);
				return sc .nextDouble();
			} catch (Exception e) {
				System. out. println("输入内容格式不正确，请输入小数类型! ");

			}
		}
	}
	//接受日期型
	public Date getDate(String msg){
		while(true){
			System. out . println(msg);
			Scanner sc=new Scanner(System. in);
			String str = null ;
			Date date = null ;
			if(sc.hasNext("^\\d{4}-\\d{2}-\\d{2}$")){ // 判断
				str = sc.next("^\\d{4}-\\d{2}-\\d{2}$") ; // 接收
				try{
					date = new SimpleDateFormat("yyyy-MM-dd").parse(str) ;
					return date;
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("输入的日期格式错误！") ;
				}
			}else{
				System.out.println("输入的日期格式错误！") ;
			}
		}


	}
}


