package com.chinasofti.hotel.domain;

import java.io.Serializable;

public class Orderitem implements Serializable{
	private Orders o1;
	private Food f1;
	private int num;
	public Orderitem() {
		super();
	}
	public Orderitem(Orders o1, Food f1, int num) {
		super();
		this.o1 = o1;
		this.f1 = f1;
		this.num = num;
	}
	public Orders getO1() {
		return o1;
	}
	public void setO1(Orders o1) {
		this.o1 = o1;
	}
	public Food getF1() {
		return f1;
	}
	public void setF1(Food f1) {
		this.f1 = f1;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getO1().getOid()+"\t"+this.getF1().getFname()+"\t"+this.getNum();
	}
}
