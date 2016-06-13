package com.ckc.cws.mapper;

import com.ckc.cws.bean.OrdersAssociated;
import com.ckc.cws.bean.OrdersAssociatedKey;

public interface OrdersAssociatedMapper {
    int deleteByPrimaryKey(OrdersAssociatedKey key);

    //插入订单关联表
    int insert(OrdersAssociated record);

    int insertSelective(OrdersAssociated record);

    OrdersAssociated selectByPrimaryKey(String recordId );

    int updateByPrimaryKeySelective(OrdersAssociated record);

    int updateByPrimaryKey(OrdersAssociated record);
    
}