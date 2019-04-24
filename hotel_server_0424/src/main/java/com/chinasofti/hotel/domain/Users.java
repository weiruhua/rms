package com.chinasofti.hotel.domain;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable{
	//用户实体类
	private int usid;
	private String uname;
	private String tel;
	private Date birth;
	public Users() {
		super();
	}
	public Users(int usid, String uname, String tel, Date birth) {
		super();
		this.usid = usid;
		this.uname = uname;
		this.tel = tel;
		this.birth = birth;
	}
	/**
	 * @return the usid
	 */
	public final int getUsid() {
		return usid;
	}
	/**
	 * @param usid the usid to set
	 */
	public final void setUsid(int usid) {
		this.usid = usid;
	}
	/**
	 * @return the uname
	 */
	public final String getUname() {
		return uname;
	}
	/**
	 * @param uname the uname to set
	 */
	public final void setUname(String uname) {
		this.uname = uname;
	}
	/**
	 * @return the tel
	 */
	public final String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public final void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the birth
	 */
	public final Date getBirth() {
		return birth;
	}
	/**
	 * @param birth the birth to set
	 */
	public final void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.usid+"\t"+this.uname+"\t"+this.tel+"\t"+this.birth;
	}
}
