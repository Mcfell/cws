package com.ckc.cws.service.users;

import java.util.List;

import com.ckc.cws.bean.Users;
import com.ckc.cws.entity.Message;
/**
 * 
 * <p>Title: IUsers</p>
 * <p>Description: TODO</p>
 * @author mcfell
 * @date 2016年5月19日
 */
public interface IUsers {

    int deleteByPrimaryKey(Integer uId);

    Message<Integer, List<String>> insert(Users record);
    
    Message<Integer, List<String>> selectByPhoneAndPwd(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}
