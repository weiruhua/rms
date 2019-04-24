package com.chinasofti.hotel.domain;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable{
	//订单实体类
	private String oid;
	private Employee e1;
	private Card c1;
	private Date odate;
	private double total;
	public Orders() {
		super();
	}
	public Orders(String oid, Employee e1, Card c1, Date odate, double total) {
		super();
		this.oid = oid;
		this.e1 = e1;
		this.c1 = c1;
		this.odate = odate;
		this.total = total;
	}
	/**
	 * @return the oid
	 */
	public final String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public final void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * @return the e1
	 */
	public final Employee getE1() {
		return e1;
	}
	/**
	 * @param e1 the e1 to set
	 */
	public final void setE1(Employee e1) {
		this.e1 = e1;
	}
	/**
	 * @return the c1
	 */
	public final Card getC1() {
		return c1;
	}
	/**
	 * @param c1 the c1 to set
	 */
	public final void setC1(Card c1) {
		this.c1 = c1;
	}
	/**
	 * @return the odate
	 */
	public final Date getOdate() {
		return odate;
	}
	/**
	 * @param odate the odate to set
	 */
	public final void setOdate(Date odate) {
		this.odate = odate;
	}
	/**
	 * @return the total
	 */
	public final double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public final void setTotal(double total) {
		this.total = total;
	}
	
}
