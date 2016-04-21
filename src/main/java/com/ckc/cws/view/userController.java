package com.ckc.cws.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckc.cws.bean.Users;
import com.ckc.cws.mapper.UsersMapper;

@Controller
@RequestMapping("/user")
public class userController {
	@Resource
	UsersMapper usersMapper;
	
	@RequestMapping("getUserById")
	public @ResponseBody Users getUser(
				@RequestParam Integer id
			){
		return usersMapper.selectByPrimaryKey(id);
	}
	@RequestMapping("ajaxUserRegister")
	public @ResponseBody String getUser(
				@ModelAttribute Users user
			){
		return "{insert success:"+usersMapper.insert(user);
	}
	@RequestMapping("toUserRegister")
	public String insertUser(
				HttpServletRequest req
			){
		return "userRegister";
	}
	
	public UsersMapper getUserMapper() {
		return usersMapper;
	}

	public void setUserMapper(UsersMapper usersMapper) {
		this.usersMapper = usersMapper;
	}
	
}
