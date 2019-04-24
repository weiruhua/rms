package com.chinasofti.hotel.biz;


import java.util.List;

import com.chinasofti.hotel.domain.Card;

public interface CardBiz {
	//增加新会员
	public String addCard(Card card);
	//会员卡充值
	public String addMoney(Card card,double money);
	//会员卡挂失，解挂，冻结
	public String changeCardStatus(Card card,int status);
	//会员卡积分兑换
	public String subScore(Card card,int num);
	//会员卡补办
	public String replaceCard(Card card,int cno);
	//会员卡优惠额度修改
	public String changeCardDiscount(double discount);
	//根据id查询会员卡
	public Card selectCardById(int cno);
	//根据用户名查询会员卡
	public Card selectCardByName(String uname);
	//查询所有会员卡信息
	public List<Card> selectAllCard();
	
}
