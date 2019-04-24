package com.chinasofti.hotel.domain;

import java.io.Serializable;

public class Card implements Serializable{
	//会员卡实体类
	private int cno;
	private Users u1;//关联用户编号
	private int score;
	private double money;
	private double discount;
	private int status;
	public Card() {
		super();
	}
	public Card(int cno, Users u1, int score, double money, double discount, int status) {
		super();
		this.cno = cno;
		this.u1 = u1;
		this.score = score;
		this.money = money;
		this.discount = discount;
		this.status = status;
	}
	/**
	 * @return the cno
	 */
	public final int getCno() {
		return cno;
	}
	/**
	 * @param cno the cno to set
	 */
	public final void setCno(int cno) {
		this.cno = cno;
	}
	/**
	 * @return the u1
	 */
	public final Users getU1() {
		return u1;
	}
	/**
	 * @param u1 the u1 to set
	 */
	public final void setU1(Users u1) {
		this.u1 = u1;
	}
	/**
	 * @return the score
	 */
	public final int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public final void setScore(int score) {
		this.score = score;
	}
	/**
	 * @return the money
	 */
	public final double getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public final void setMoney(double money) {
		this.money = money;
	}
	/**
	 * @return the discount
	 */
	public final double getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public final void setDiscount(double discount) {
		this.discount = discount;
	}
	/**
	 * @return the status
	 */
	public final int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public final void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.cno+"\t"+this.u1.getUname()+"\t"+this.score+"\t"+this.money+"\t"+this.discount+"\t"+this.status;
	}
	
}
