package com.ckc.cws.view;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckc.cws.bean.Carlocks;
import com.ckc.cws.bean.Parks;
import com.ckc.cws.entity.Message;
import com.ckc.cws.service.locks.ILocks;

/*
 * 车位锁carlock控制器
 */

@Controller
@RequestMapping("/carlock")
public class CarlockController {
	
	@Resource(name="lockService")
	ILocks lockService;
	
	//查询可用蓝牙列表(用户已经到停车场),根据停车场查询车位信息
	@RequestMapping(value="checkCarlockInPark",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, List<Carlocks>> checkCarlocks(Parks parks){
		return lockService.checkCarlocks(parks);
	}
	//获取车位锁验证码（随机生成）
	@RequestMapping(value="ajaxLockGetAuth",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, String> getLockAuth(Carlocks carlock){
		return lockService.getLockAuth(carlock);
	}
	
}
