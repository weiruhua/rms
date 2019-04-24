package com.chinasofti.hotel.domain;

import java.io.Serializable;

public class Food implements Serializable{
	//菜品类
	private int fid;
	private Menu m1;
	private String fname;
	private double price;
	private String status;
	public Food() {
		super();
	}
	public Food(int fid, Menu m1, String fname, double price, String status) {
		super();
		this.fid = fid;
		this.m1 = m1;
		this.fname = fname;
		this.price = price;
		this.status = status;
	}
	/**
	 * @return the fid
	 */
	public final int getFid() {
		return fid;
	}
	/**
	 * @param fid the fid to set
	 */
	public final void setFid(int fid) {
		this.fid = fid;
	}
	/**
	 * @return the m1
	 */
	public final Menu getM1() {
		return m1;
	}
	/**
	 * @param m1 the m1 to set
	 */
	public final void setM1(Menu m1) {
		this.m1 = m1;
	}
	/**
	 * @return the fname
	 */
	public final String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public final void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the price
	 */
	public final double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public final void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the status
	 */
	public final String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public final void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.fid+"\t"+this.m1.getMenuName()+"\t"+this.fname+"\t"+this.price+"\t"+this.status;
	}
}
