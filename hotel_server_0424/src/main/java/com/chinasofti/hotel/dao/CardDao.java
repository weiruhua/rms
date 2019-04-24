package com.chinasofti.hotel.dao;

import java.util.List;

import com.chinasofti.hotel.domain.Card;

public interface CardDao {
	//查询会员卡信息
	public Card selectCardById(int id);
	//更新会员卡信息
	public boolean updateCard(Card card);
	//添加会员卡
	public boolean addCard(Card card);
	//查询所有会员信息
	public List<Card> selectAllCard();
	//根据用户名查询会员卡信息
	public Card selectCardByName(String name);

	
}
