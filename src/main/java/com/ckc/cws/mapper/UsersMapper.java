package com.ckc.cws.mapper;

import com.ckc.cws.bean.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    
    int selectByPhone(String phone);
    
    Users selectByPhoneAndPwd(Users record);
}