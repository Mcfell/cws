package com.ckc.cws.service.users.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ckc.cws.bean.Users;
import com.ckc.cws.entity.Message;
import com.ckc.cws.global.FinalValue;
import com.ckc.cws.mapper.UsersMapper;
import com.ckc.cws.service.users.IUsers;
import com.ckc.cws.util.DataUtil;

@Service(value="userService")
public class UsersImpl implements IUsers {

	@Resource
	UsersMapper usersMapper;
	
	private static final Logger log = LoggerFactory.getLogger(UsersImpl.class);
	public int validation(String value,int type){
		if(value==null||value.trim().equals(""))
			return FinalValue.NULL_PARAMETERS;
		switch (type) {
		case FinalValue.PHONE:
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
			Matcher m = p.matcher(value);  
			if (!m.matches()) {
				return FinalValue.PHONE_ERROR;
			};
			break;
		case FinalValue.PWD:
			Pattern p1 = Pattern.compile("[A-Za-z0-9\\.\\@_\\-~#]+");
			Matcher m1 = p1.matcher(value);
			if(!m1.matches()) 
				return FinalValue.PWD_ERROR;
		default:
			break;
		}
		return FinalValue.SUCCESS;
	}
	
	/*
	 *通过手机注册 
	 */
	public Message<Integer, List<String>> insert(Users record) {
		Message<Integer,List<String>> message = new Message<Integer,List<String>>();
		List<String> list = new ArrayList<String>();
		//校验手机
		int phoneStatue = validation(record.getPhone(), FinalValue.PHONE);
		if(phoneStatue!=FinalValue.SUCCESS){
			message.setStatue(phoneStatue);
			return message;
		}
		//校验密码
		int pwdStatue = validation(record.getPwd(), FinalValue.PWD);
		if(pwdStatue!=FinalValue.SUCCESS){
			message.setStatue(pwdStatue);
			return message;
		}
		//判断手机是否注册
		int isRegister = usersMapper.selectByPhone(record.getPhone());
		if(isRegister>0){
			message.setStatue(FinalValue.PHONE_HASREGISGER);
			log.debug("该手机号已经注册");
			list.add("该手机号已经注册");
			message.setContenT(list);
			return message;
		}
		//注册用户
		record.setLevel((byte) 1);
		record.setRegTime(new Date());
		record.setLastloginTime(new Date());
		record.setPwd((String)DataUtil.md5(record.getPwd()));
		String phoneString = record.getPhone();
		phoneString = phoneString.substring(0, 4)+"****"+phoneString.substring(8, 11);
		record.setuName(phoneString);
		if(usersMapper.insert(record)>0){
			log.debug(record.getPhone()+"：注册成功！");
			message.setStatue(FinalValue.SUCCESS);
		}else {
			log.debug(record.getPhone()+"：注册失败！");
			message.setStatue(FinalValue.FAILED);
		}
		return message;
	}
	/*
	 * 通过手机密码登录
	 * @see com.ckc.cws.service.users.IUsers#selectByPhoneAndPwd(com.ckc.cws.bean.Users)
	 */
	public Message<Integer, List<String>> selectByPhoneAndPwd(Users record) {
		Message<Integer,List<String>> message = new Message<Integer,List<String>>();
		List<String> list = new ArrayList<String>();
		//校验手机
		int phoneStatue = validation(record.getPhone(), FinalValue.PHONE);
		if(phoneStatue!=FinalValue.SUCCESS){
			message.setStatue(phoneStatue);
			return message;
		}
		//校验密码
		int pwdStatue = validation(record.getPwd(), FinalValue.PWD);
		if(pwdStatue!=FinalValue.SUCCESS){
			message.setStatue(pwdStatue);
			return message;
		}
		record.setPwd((String)DataUtil.md5(record.getPwd()));
		Users user = usersMapper.selectByPhoneAndPwd(record);
		if(user!=null){
			log.debug("login success!");
			user.setLastloginTime(new Date());
			usersMapper.updateByPrimaryKeySelective(user); // 更新最新登录时间
			message.setStatue(FinalValue.SUCCESS);
			return message;
		}else{
			log.debug("login default!");
			message.setStatue(FinalValue.FAILED);
			list.add("用户名密码不匹配");
			message.setContenT(list);
			return message;
		}
	}


	public int insertSelective(Users record) {
		return usersMapper.insertSelective(record);
	}

	public Users selectByPrimaryKey(Integer uId) {
		// TODO Auto-generated method stub
		return usersMapper.selectByPrimaryKey(uId);
	}

	public int updateByPrimaryKeySelective(Users record) {
		return usersMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Users record) {
		return usersMapper.updateByPrimaryKey(record);
	}

	public int deleteByPrimaryKey(Integer uId) {
		return 0;
	}

}
