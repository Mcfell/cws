package com.ckc.cws.view;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckc.cws.bean.Carlocks;
import com.ckc.cws.bean.Orders;
import com.ckc.cws.entity.Message;
import com.ckc.cws.service.orders.IOrders;

/*
 * 订单模块控制器
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Resource(name="orderService")
	IOrders orderService;
	//产生新的及时订单
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, String> generateOrders(Orders orders, Carlocks carlocks, HttpSession session){
		return orderService.generateTimelyOrders(orders, carlocks, session);
	}
	//订单确认
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, String> ordersSverifyTimely(Date endTime, HttpSession session){
		return orderService.verifyTimelyOrders(endTime, session);
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, String> doCompletePay( HttpSession session){
		return orderService.completePay(session);
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody Message<Double, String> doGetTatolCost( HttpSession session){
		return orderService.getTatolCost(session);
	}
	
	
}
