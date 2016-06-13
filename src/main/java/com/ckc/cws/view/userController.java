package com.ckc.cws.view;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ckc.cws.bean.Users;
import com.ckc.cws.entity.Message;
import com.ckc.cws.global.FinalValue;
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
	public @ResponseBody Message<Integer, List<String>> userRegister(Users user,String vcode,HttpServletRequest request){
		return userService.insert(user,vcode,request);
	}
	/*
	 * 用户登录
	 */
	@RequestMapping(value="ajaxUserLogin",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, List<String>> userLogin(Users user,HttpServletRequest request){
		return userService.selectByPhoneAndPwd(user,request);
	}
	/*
	 * 发送手机验证码
	 */
	@RequestMapping(value="ajaxUserSendPhoneMsg")
	public @ResponseBody Message<Integer, String> userSendPhoneMsg(String phone,HttpServletRequest request){
		return userService.sendPhoneMsg(phone,request);
	}
	/*
	 * 校验手机验证码
	 */
	@RequestMapping(value="ajaxCheckPhoneVCode",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, String> checkPhoneVCode(String vCode,HttpServletRequest request){
		return userService.checkPhoneVCode(vCode,request);
	}
	/*
	 * 退出登录
	 */
	@RequestMapping(value="ajaxUserLogout")
	public @ResponseBody Message<Integer, String> userLogout(HttpServletRequest request){
		return userService.userLogout(request);
	}
	/*
	 * 修改密码
	 */
	@RequestMapping(value="ajaxUserChangePwd",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, String> userChangePwd(Users users, HttpServletRequest request){
		return userService.userChangePwd(users, request);
	}
	/*
	 * 个人资料修改
	 */
	@RequestMapping(value="ajaxUserUpdateInfo",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, String> userUpdateInfo(Users users, HttpServletRequest request){
		return userService.userUpdateInfo(users, request);
	}
	/*
	 * 个人头像上传，登录后上传
	 */
	@RequestMapping(value="ajaxUserUploadPic",method=RequestMethod.POST)
	public @ResponseBody Message<Integer, String> userUploadPic(MultipartFile multipartFile, HttpServletRequest request){
		return userService.userUploadPic(multipartFile, request);
	}
}
