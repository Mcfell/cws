package com.ckc.cws.service.orders;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.ckc.cws.bean.Carlocks;
import com.ckc.cws.bean.Orders;
import com.ckc.cws.bean.Users;
import com.ckc.cws.entity.Message;

/*
 * 订单接口
 */

public interface IOrders {
	
	//生成及时订单
	public Message<Integer, String> generateTimelyOrders(Orders orders, Carlocks carlocks, HttpSession session);
	//确认及时订单
	public Message<Integer, String> verifyTimelyOrders(Date endTime, HttpSession session);
	//订单支付完成
	public Message<Integer, String> completePay( HttpSession session);
	//请求订单费用
	public Message<Double, String> getTatolCost( HttpSession session);
	//生成预约订单	
	public Message<Integer, String> generateBookOrders(Orders orders, Carlocks carlocks, HttpSession session);

}
