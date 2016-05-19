package com.ckc.cws.mapper;

import com.ckc.cws.bean.Carlocks;

public interface CarlocksMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Carlocks record);

    int insertSelective(Carlocks record);

    Carlocks selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(Carlocks record);

    int updateByPrimaryKey(Carlocks record);
}