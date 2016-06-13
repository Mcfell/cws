package com.ckc.cws.service.orders.impl;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ckc.cws.bean.Carlocks;
import com.ckc.cws.bean.Orders;
import com.ckc.cws.bean.OrdersAssociated;
import com.ckc.cws.bean.OrdersAssociatedKey;
import com.ckc.cws.bean.Parks;
import com.ckc.cws.bean.ParksSpace;
import com.ckc.cws.bean.Users;
import com.ckc.cws.entity.Message;
import com.ckc.cws.global.FinalValue;
import com.ckc.cws.mapper.CarlocksMapper;
import com.ckc.cws.mapper.OrdersAssociatedMapper;
import com.ckc.cws.mapper.OrdersMapper;
import com.ckc.cws.mapper.ParksMapper;
import com.ckc.cws.mapper.ParksSpaceMapper;
import com.ckc.cws.service.orders.IOrders;

/*
 * 订单接口的实现
 */
@Service(value="orderService")
public class OrdersImpl implements IOrders {

	@Resource
	OrdersMapper ordersMapper;
	OrdersAssociatedMapper ordersAssociatedMapper;
	ParksSpaceMapper parksSpaceMapper;
	CarlocksMapper carlocksMapper;
	ParksMapper parksMapper;
	private static final Logger log=LoggerFactory.getLogger(OrdersImpl.class);
	Session session;
	@Override
	/*
	 * 生成及时订单
	 */
	public Message<Integer, String> generateTimelyOrders(Orders orders, Carlocks carlocks, HttpSession session) {
		//通过session获得users对象
		//Users users=(Users) session.getAttribute("users");
		
		Users users=new Users();
		//模拟用户id，如何得到用户session,得到当前用户对象？
		users.setuId(10001);
		
		Orders orders2=orders;
		Carlocks carlocks2=carlocks;
		ParksSpace parkSapse=new ParksSpace();
		OrdersAssociated ordersAssociated=new OrdersAssociated();
		Message<Integer, String> message=new Message<Integer, String>();
	    if (orders2!=null&&carlocks!=null&&users!=null) {
	    	orders2.setStat((byte)0);//0未完成，1完成未支付，2支付完成
	    	
	    	ordersAssociated.setuId(users.getuId());
	    	ordersAssociated.setoId(orders2.getoId());
	    	ordersAssociated.setpId(carlocks.getpId());
	    	ordersAssociated.setsId(carlocks.getsId());
	    	//插入未完成订单，和订单关联数据
	    	int ordersRes=ordersMapper.generateOrders(orders2);
	    	int ordersAssRes=ordersAssociatedMapper.insert(ordersAssociated);
	    	//更新parkSapse，carlocks2
	    	parkSapse=parksSpaceMapper.selectByPrimaryKey(carlocks2.getpId());
    		parkSapse.setFreenum(parkSapse.getFreenum()-1);
    		int parkSapseFlunence =parksSpaceMapper.updateByPrimaryKeySelective(parkSapse);
    		int carlockFlunence =carlocksMapper.updateByPrimaryKey(carlocks2);
	    	if (ordersRes!=0&&ordersAssRes!=0&&parkSapseFlunence!=0&&carlockFlunence!=0) {
	    		message.setStatue(FinalValue.SUCCESS);
	    		message.setContenT("生成订单成功");
	    		log.debug("插入订单：订单号"+orders2.getoId()+"  开始时间"+orders2.getStartTime());
		    	log.debug("插入订单关联表：订单号 "+ordersAssociated.getoId()+"  用户名 "+ordersAssociated.getuId()+" 车锁id "+ordersAssociated.getsId());
			}else {
				message.setStatue(FinalValue.FAILED);
	    		message.setContenT("生成订单失败");
	    		log.debug("生成订单失败");
			}
		}else {
			message.setStatue(FinalValue.FAILED);
    		message.setContenT("生成订单失败");
    		log.debug("生成订单失败");
		}
	    
		return message;
	}
	@Override
	/*
	 * 确认及时订单
	 */
	public Message<Integer, String> verifyTimelyOrders(Date endTime, HttpSession session) {
		// TODO Auto-generated method stub
		Message<Integer, String> message=new Message<Integer, String>();
		Users users=new Users();
		//模拟用户id，如何得到用户session,得到当前用户对象？
		users.setuId(10001);
		//得到未完成订单
		String orderId=ordersMapper.selectUnfinishedOrders(users.getuId().toString());
		Orders orders=ordersMapper.selectByPrimaryKey(orderId);
		//计算费用(不区分白天和晚上)
		Date begainTime=orders.getStartTime();
		float countTime=(float)(endTime.getTime()-begainTime.getTime())/3600000;
		OrdersAssociated ordersAssociated=ordersAssociatedMapper.selectByPrimaryKey(orders.getoId());
		Parks parks=parksMapper.selectByPrimaryKey(ordersAssociated.getpId());
		double totalCost=countTime*parks.getDayPrice();
		//更新orders表
		orders.setEndTime(endTime);
		//orders.setTotalDayTime(countTime);
		orders.setTotalCost(totalCost);
		orders.setStat((byte)1);
		int orderUpdate= ordersMapper.updateByPrimaryKey(orders);
		//更新车锁表
		Carlocks carlocks2=carlocksMapper.selectByPrimaryKey(ordersAssociated.getsId());
		carlocks2.setUseStat(false);
		carlocks2.setuId(null);
		int carlockUpdate=carlocksMapper.updateByPrimaryKey(carlocks2);
		//更新parkspace表
		ParksSpace  parksSpace=parksSpaceMapper.selectByPrimaryKey(ordersAssociated.getpId());
		parksSpace.setFreenum(parksSpace.getFreenum()+1);
		int parkspaceupdate=parksSpaceMapper.updateByPrimaryKey(parksSpace);
		
		if (orderUpdate!=0&&carlockUpdate!=0&&parkspaceupdate!=0) {
			message.setStatue(FinalValue.SUCCESS);
    		message.setContenT("成功确认");
    		log.debug("成功确认");
		}else {
			message.setStatue(FinalValue.FAILED);
    		message.setContenT("确认失败");
    		log.debug("确认失败");
		}
		return message;
	}
	@Override
	public Message<Integer, String> completePay(HttpSession session) {
		// TODO Auto-generated method stub
		Message<Integer, String> message=new Message<Integer, String>();
		Users users=new Users();
		//模拟用户id，如何得到用户session,得到当前用户对象？
		users.setuId(10001);
		//得到完成未支付订单
		String orderId=ordersMapper.selectfinishedUnpayOrders(users.getuId().toString());
		Orders orders=ordersMapper.selectByPrimaryKey(orderId);
		orders.setStat((byte)2);
		int orderUpdate= ordersMapper.updateByPrimaryKey(orders);
		if (orderUpdate!=0) {
			message.setStatue(FinalValue.SUCCESS);
    		message.setContenT("订单状态修改成功");
    		log.debug("订单状态修改成功");
		}else {
			message.setStatue(FinalValue.FAILED);
    		message.setContenT("订单状态修改失败");
    		log.debug("订单状态修改失败");
		}
		return message;
	}
	@Override
	public Message<Double, String> getTatolCost(HttpSession session) {
		// TODO Auto-generated method stub
		Message<Double, String> message=new Message<Double, String>();
		Users users=new Users();
		//模拟用户id，如何得到用户session,得到当前用户对象？
		users.setuId(10001);
		//得到完成未支付订单
		String orderId=ordersMapper.selectfinishedUnpayOrders(users.getuId().toString());
		Orders orders=ordersMapper.selectByPrimaryKey(orderId);
		message.statue=orders.getTotalCost();
		message.contenT="pay";
		return message;
	}
	@Override
	public Message<Integer, String> generateBookOrders(Orders orders, Carlocks carlocks, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
