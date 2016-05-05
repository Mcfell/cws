package com.ckc.cws.mapper;

import com.ckc.cws.bean.ParksComment;

public interface ParksCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParksComment record);

    int insertSelective(ParksComment record);

    ParksComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParksComment record);

    int updateByPrimaryKey(ParksComment record);
}