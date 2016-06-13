package com.ckc.cws.mapper;

import java.util.List;

import com.ckc.cws.bean.Parks;

public interface ParksMapper {
    int deleteByPrimaryKey(Integer pId);

    int insert(Parks record);

    int insertSelective(Parks record);

    Parks selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(Parks record);

    int updateByPrimaryKey(Parks record);
    
    List<Parks> selectAll();
}