package com.chinasofti.hotel.domain;

import java.io.Serializable;

public class Menu implements Serializable{
//菜品类别实体类
	private int menuId;
	private String menuName;
	public Menu() {
		super();
	}
	public Menu(int menuId, String menuName) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
	}
	/**
	 * @return the menuId
	 */
	public final int getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public final void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	/**
	 * @return the menuName
	 */
	public final String getMenuName() {
		return menuName;
	}
	/**
	 * @param menuName the menuName to set
	 */
	public final void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.menuId+"\t"+this.menuName;
		}
	
}
