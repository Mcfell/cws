package com.ckc.cws.mapper;

import java.util.List;

import com.ckc.cws.bean.Carlocks;

public interface CarlocksMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Carlocks record);

    int insertSelective(Carlocks record);

    Carlocks selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(Carlocks record);
   
    int updateByPrimaryKey(Carlocks record);
    //根据停车场ID获取该停车场的停车位信息
	List<Carlocks> selectByParksId(int pId);
	//根据车位锁ID，写入车位锁验证码(数据库里叫蓝牙密码)
	int updateLockAuth(Carlocks carlock);
	
}