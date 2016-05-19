package com.ckc.cws.view;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckc.cws.bean.Users;
import com.ckc.cws.entity.Message;
import com.ckc.cws.service.users.IUsers;

@Controller
@RequestMapping("/user")
public class userController {
	
	@Resource(name="userService")
	IUsers userService;
	/*
	 * 用户注册
	 */
	@RequestMapping(value="ajaxUserRegister",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, List<String>> userRegister(Users user){
		return userService.insert(user);
	}
	/*
	 * 用户登录
	 */
	@RequestMapping(value="ajaxUserLogin",method=RequestMethod.POST)
	public @ResponseBody Users selectByUserPhoneAndPwd(Users user){
		return userDao.selectByPrimaryKey();
	public @ResponseBody Message<Integer, List<String>> selectAllUser(Users user){
		return userService.selectByPhoneAndPwd(user);
	}
}
