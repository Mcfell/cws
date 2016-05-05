package com.ckc.cws.mapper;

import com.ckc.cws.bean.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(String oId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String oId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}