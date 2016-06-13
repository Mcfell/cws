package com.ckc.cws.mapper;

import java.util.List;

import com.ckc.cws.bean.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    /*
     * 查询手机是否注册，返回id
     */
    int selectByPhone(String phone);
    
    int selectUidByPhone(String phone);
    
    Users selectByPhoneAndPwd(Users record);
    
    List<Users> selectAll();
}