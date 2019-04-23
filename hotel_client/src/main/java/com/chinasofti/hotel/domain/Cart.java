package com.chinasofti.hotel.domain;

import java.io.Serializable;

public class Cart implements Serializable{
	//购物车实体，暂时存储点菜信息
	private Food f;
	private int num;
	public Cart() {
		super();
	}
	public Cart(Food f, int num) {
		super();
		this.f = f;
		this.num = num;
	}
	/**
	 * @return the f
	 */
	public final Food getF() {
		return f;
	}
	/**
	 * @param f the f to set
	 */
	public final void setF(Food f) {
		this.f = f;
	}
	/**
	 * @return the num
	 */
	public final int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public final void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.f.getFid()+"\t"+this.f.getFname()+"\t"+this.f.getPrice()+"\t"+this.num+"\t"+this.f.getPrice()*this.num;
	}
	

}
