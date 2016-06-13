package com.ckc.cws.mapper;

import com.ckc.cws.bean.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(String oId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String oId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    
    //生成订单
    int generateOrders(Orders Orders);
    //未完成订单号查询
    String selectUnfinishedOrders(String uId);
  //完成未支付订单号查询
    String selectfinishedUnpayOrders(String uId);
}