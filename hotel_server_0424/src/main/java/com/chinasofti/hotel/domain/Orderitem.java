package com.chinasofti.hotel.domain;

import java.io.Serializable;

public class Orderitem implements Serializable{
	private Orders o1;
	private Food f1;
	private int num;
	
	public Orderitem() {
		super();
	}

	/**
	 * @return the o1
	 */
	public final Orders getO1() {
		return o1;
	}

	/**
	 * @param o1 the o1 to set
	 */
	public final void setO1(Orders o1) {
		this.o1 = o1;
	}

	/**
	 * @return the f1
	 */
	public final Food getF1() {
		return f1;
	}

	/**
	 * @param f1 the f1 to set
	 */
	public final void setF1(Food f1) {
		this.f1 = f1;
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

	public Orderitem(Orders o1, Food f1, int num) {
		super();
		this.o1 = o1;
		this.f1 = f1;
		this.num = num;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getO1().getOid()+"\t"+this.getF1().getFname()+"\t"+this.getNum();
	}
}
