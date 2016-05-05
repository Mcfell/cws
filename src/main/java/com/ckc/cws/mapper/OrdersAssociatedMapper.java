package com.ckc.cws.mapper;

import com.ckc.cws.bean.OrdersAssociated;
import com.ckc.cws.bean.OrdersAssociatedKey;

public interface OrdersAssociatedMapper {
    int deleteByPrimaryKey(OrdersAssociatedKey key);

    int insert(OrdersAssociated record);

    int insertSelective(OrdersAssociated record);

    OrdersAssociated selectByPrimaryKey(OrdersAssociatedKey key);

    int updateByPrimaryKeySelective(OrdersAssociated record);

    int updateByPrimaryKey(OrdersAssociated record);
}