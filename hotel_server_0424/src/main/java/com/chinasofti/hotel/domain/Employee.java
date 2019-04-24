package com.chinasofti.hotel.domain;

import java.io.Serializable;

public class Employee implements Serializable{
	//员工实体类
	private int empId;
	private String empName;
	private String empAccount;
	private String empPassword;
	private int pow;//员工权限
	public Employee() {
		super();
	}
	public Employee(int empId, String empName, String empAccount, String empPassword, int pow) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empAccount = empAccount;
		this.empPassword = empPassword;
		this.pow = pow;
	}
	/**
	 * @return the empId
	 */
	public final int getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public final void setEmpId(int empId) {
		this.empId = empId;
	}
	/**
	 * @return the empName
	 */
	public final String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public final void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the empAccount
	 */
	public final String getEmpAccount() {
		return empAccount;
	}
	/**
	 * @param empAccount the empAccount to set
	 */
	public final void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}
	/**
	 * @return the empPassword
	 */
	public final String getEmpPassword() {
		return empPassword;
	}
	/**
	 * @param emppassword the empPassword to set
	 */
	public final void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	/**
	 * @return the pow
	 */
	public final int getPow() {
		return pow;
	}
	/**
	 * @param pow the pow to set
	 */
	public final void setPow(int pow) {
		this.pow = pow;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.empId+"\t"+this.empName+"\t"+this.empAccount+"\t"+this.empPassword+"\t"+this.pow;
	}
	

}
