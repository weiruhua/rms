package com.chinasofti.hotel.biz.impl;

import java.util.List;

import com.chinasofti.hotel.biz.CardBiz;
import com.chinasofti.hotel.dao.CardDao;
import com.chinasofti.hotel.dao.impl.CardDaoImpl;
import com.chinasofti.hotel.domain.Card;

public class CardBizImpl implements CardBiz {
	//创建dao对象
	private CardDao caDao;

	public CardBizImpl() {
		super();
		this.caDao =new CardDaoImpl();
	}
	//增加新会员
	public String addCard(Card card) {
		boolean msg = this.caDao.addCard(card);
		return msg?"添加成功":"添加失败";
	}
	//会员卡充值
	public String addMoney(Card card,double money) {
		Card c = this.caDao.selectCardById(card.getCno());
		c.setMoney(c.getMoney()+money);
		boolean msg = this.caDao.updateCard(c);
		return msg?"充值成功":"充值失败";
	}
	//会员卡冻结；挂失，解挂
	public String changeCardStatus(Card card,int status) {
		Card c = this.caDao.selectCardById(card.getCno());
		c.setStatus(status);
		boolean msg = this.caDao.updateCard(c);
		return msg?"操作成功":"操作失败";
	}
	//会员卡积分兑换
	public String subScore(Card card,int num) {
		Card c = this.caDao.selectCardById(card.getCno());
		c.setScore(c.getScore()-num);
		boolean msg = this.caDao.updateCard(c);
		return msg?"兑换成功":"兑换失败";
	}
	//会员卡补办
	public String replaceCard(Card card,int cno) {
		//使会员卡变成冻结状态
		changeCardStatus(card,2);
		card.setCno(cno);
		return addCard(card);
	}
	//设置会员卡优惠额度
	public String changeCardDiscount(double discount) {
		List<Card> list = this.caDao.selectAllCard();
		for (Card cc : list) {
			cc.setDiscount(discount);
			this.caDao.updateCard(cc);
		}
		return "设置成功，当前会员卡优惠额度为"+discount*10+"折！";
	}
	public Card selectCardById(int cno) {
		// TODO Auto-generated method stub
		return this.caDao.selectCardById(cno);
	}
	public Card selectCardByName(String uname) {
		// TODO Auto-generated method stub
		return this.caDao.selectCardByName(uname);
	}
	public List<Card> selectAllCard() {
		// TODO Auto-generated method stub
		return this.caDao.selectAllCard();
	}


}
