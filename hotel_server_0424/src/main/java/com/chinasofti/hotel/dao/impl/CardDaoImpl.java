package com.chinasofti.hotel.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.hotel.dao.CardDao;
import com.chinasofti.hotel.domain.Card;
import com.chinasofti.hotel.domain.Users;
import com.chinasofti.hotel.util.DBUtil;

public class CardDaoImpl implements CardDao {
	private DBUtil db;
	public Card selectCardById(int id) {
		this.db=new DBUtil();
		String sql="select c.*,u.* from card c,users u where c.usid=u.usid and cno="+id;
		try {
			Card c=new Card();
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				c.setCno(rs.getInt("cno"));
				c.setU1(new Users(rs.getInt("usid"),
						rs.getString("uname"),
						rs.getString("tel"),
						rs.getDate("birth")
						));
				c.setScore(rs.getInt("score"));
				c.setMoney(rs.getDouble("money"));
				c.setDiscount(rs.getInt("discount"));
				c.setStatus(rs.getInt("status"));
				return c;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.db.closed();
		}
		return null;
	}
	
	public boolean updateCard(Card card) {
		this.db=new DBUtil();
		String sql="update card set score=?,money=?,discount=?,status=? where cno=?";
		try {
			int i = this.db.update(sql, card.getScore(),card.getMoney(),card.getDiscount(),card.getStatus(),card.getCno());
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		
	}
	
	public boolean addCard(Card card) {
		this.db=new DBUtil();
		String sql="insert into card values(?,?,?,?,?,?)"; 
		try {
			int i = this.db.update(sql, card.getCno(),card.getU1().getUsid(),card.getScore(),card.getMoney(),card.getDiscount(),card.getStatus());
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		
	}

	public List<Card> selectAllCard() {
		this.db=new DBUtil();
		List<Card> list=new ArrayList<Card>();
		String sql="select c.*,u.* from card c,users u where c.usid(+)=u.usid";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				
				list.add(new Card(rs.getInt("cno"),
						new Users(rs.getInt("usid"),
								rs.getString("uname"),
								rs.getString("tel"),
								rs.getDate("birth")),
						rs.getInt("score"),
						rs.getDouble("money"),
						rs.getDouble("discount"),
						rs.getInt("status")));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
		
	}
	public Card selectCardByName(String name) {
		this.db=new DBUtil();
		String sql="select c.*,u.* from card c,users u where c.usid=u.usid and uname='"+name+"'";
		try {
			Card c=new Card();
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				c.setCno(rs.getInt("cno"));
				c.setU1(new Users(rs.getInt("usid"),
						rs.getString("uname"),
						rs.getString("tel"),
						rs.getDate("birth")
						));
				c.setScore(rs.getInt("score"));
				c.setMoney(rs.getDouble("money"));
				c.setDiscount(rs.getInt("discount"));
				c.setStatus(rs.getInt("status"));
				return c;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
		return null;
	}
}
