package com.ckc.cws.mapper;

import com.ckc.cws.bean.ParksSpace;

public interface ParksSpaceMapper {
    int deleteByPrimaryKey(Integer pId);

    int insert(ParksSpace record);

    int insertSelective(ParksSpace record);

    ParksSpace selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(ParksSpace record);

    int updateByPrimaryKey(ParksSpace record);
}