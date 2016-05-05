package com.ckc.cws.view;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckc.cws.bean.Users;
import com.ckc.cws.entity.Message;
import com.ckc.cws.mapper.UsersMapper;
import com.ckc.cws.service.users.IUsers;

@Controller
@RequestMapping("/user")
public class userController {
	
	@Resource(name="userDao")
	IUsers userDao;
	/*
	 * 用户注册
	 */
	@RequestMapping(value="ajaxUserRegister",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, List<String>> userRegister(Users user){
		return userDao.insert(user);
	}
	
	@RequestMapping(value="ajaxUserLogin",method=RequestMethod.POST)
	public @ResponseBody Users selectAllUser(){
		
		
		return userDao.selectByPrimaryKey(2);
	}
}
