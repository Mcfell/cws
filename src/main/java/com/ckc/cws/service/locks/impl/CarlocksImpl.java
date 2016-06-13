package com.ckc.cws.service.locks.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ckc.cws.bean.Carlocks;
import com.ckc.cws.bean.Parks;
import com.ckc.cws.entity.Message;
import com.ckc.cws.global.FinalValue;
import com.ckc.cws.mapper.CarlocksMapper;
import com.ckc.cws.service.locks.ILocks;
import com.ckc.cws.service.users.impl.UsersImpl;

@Service(value="lockService")
public class CarlocksImpl implements ILocks {
	
	@Resource
	CarlocksMapper carlocksMapper;
	
	private static final Logger log = LoggerFactory.getLogger(UsersImpl.class);
	
	//查询可用蓝牙列表(用户已经到停车场),根据停车场查询车位信息,接口
	public Message<Integer, List<Carlocks>> checkCarlocks(Parks parks){
		
		
		Message<Integer,List<Carlocks>> message = new Message<Integer,List<Carlocks>>();
		List<Carlocks> carlockslist = new ArrayList<Carlocks>();
		//获取停车场Parks信息
		if (parks.getpId()!=null) {
			int pId=parks.getpId();
			carlockslist = carlocksMapper.selectByParksId(pId);
			if (!carlockslist.isEmpty()) {
				message.setStatue(FinalValue.SUCCESS);
				message.setContenT(carlockslist);
				log.debug("查询可用蓝牙列表成功！");
			}else{
				message.setStatue(FinalValue.FAILED);
				message.setContenT(null);
				log.debug("-----查询可用蓝牙列表失败：无车位锁");
			}
		}else {
			message.setStatue(FinalValue.FAILED);
			message.setContenT(null);
			log.debug("-----查询可用蓝牙列表失败：停车场不存在");
		}
		return message;
	}
	//获取车位锁验证码（随机生成）,根据carlocks.id生成验证码,接口
	public Message<Integer, String> getLockAuth(Carlocks carlock){
		
		
		Message<Integer,String> message = new Message<Integer,String>();
		int bulePwd=000;
		//生成验证码
		if (carlock.getsId()!=null) {
			int sId=carlock.getsId();
			//随机产生6位验证码
			bulePwd =(int)(100+Math.random()*899);
			log.debug("------生成验证码:"+sId+"##"+bulePwd);
			//将验证码写入数据库（更新蓝牙密码）
			carlock.setBulePwd(""+bulePwd);
//				int issuccess = carlocksMapper.updateLockAuth(carlock);
//				if (issuccess!=0) {
				//返还结果
			message.setStatue(FinalValue.SUCCESS);
			message.setContenT(""+bulePwd);
			log.debug("------随机生成验证码成功！");
//				}else {
//					//返还结果
//					message.setStatue(FinalValue.FAILED);
//					message.setContenT(null);
//					log.debug("------随机生成验证码失败：没有导入数据库");
//				}
			
		}else {
			message.setStatue(FinalValue.FAILED);
			message.setContenT(null);
			log.debug("------随机生成验证码失败：车位锁不存在");
		}
		return message;
	}
}
