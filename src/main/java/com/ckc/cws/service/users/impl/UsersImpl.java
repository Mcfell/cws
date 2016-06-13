package com.ckc.cws.service.users.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ckc.cws.bean.Users;
import com.ckc.cws.entity.Message;
import com.ckc.cws.global.FinalValue;
import com.ckc.cws.mapper.UsersMapper;
import com.ckc.cws.service.users.IUsers;
import com.ckc.cws.util.DataUtil;
import com.ckc.cws.util.SmsUtil;
import com.ckc.cws.util.UploadUtil;

@Service(value = "userService")
public class UsersImpl implements IUsers {

	@Resource
	UsersMapper usersMapper;

	private static final Logger log = LoggerFactory.getLogger(UsersImpl.class);

	public int validation(String value, int type) {
		if (value == null || value.trim().equals(""))
			return FinalValue.NULL_PARAMETERS;
		switch (type) {
		case FinalValue.PHONE:
			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(value);
			if (!m.matches()) {
				return FinalValue.PHONE_ERROR;
			}
			;
			break;
		case FinalValue.PWD:
			Pattern p1 = Pattern.compile("[A-Za-z0-9\\.\\@_\\-~#]+");
			Matcher m1 = p1.matcher(value);
			if (!m1.matches())
				return FinalValue.PWD_ERROR;
		default:
			break;
		}
		return FinalValue.SUCCESS;
	}

	/*
	 * 通过手机注册
	 */

	public Message<Integer, List<String>> insert(Users record,String vcode,
			HttpServletRequest request) {
		Message<Integer, List<String>> message = new Message<Integer, List<String>>();
		List<String> list = new ArrayList<String>();
		// 校验手机
		int phoneStatue = validation(record.getPhone(), FinalValue.PHONE);
		if (phoneStatue != FinalValue.SUCCESS) {
			message.setStatue(phoneStatue);
			return message;
		}
		// 校验密码
		int pwdStatue = validation(record.getPwd(), FinalValue.PWD);
		if (pwdStatue != FinalValue.SUCCESS) {
			message.setStatue(pwdStatue);
			return message;
		}
		//校验验证码
		HttpSession ss = request.getSession();
		String vvcode = (String) ss.getAttribute("vcode");
		if(vvcode==null||vvcode.equals("")||vcode==null||vcode.equals("")||!vcode.equals(vvcode)){
			message.setStatue(FinalValue.VCODE_ERROR);
			return message;
		}
		// 判断手机是否注册
		int isRegister = usersMapper.selectByPhone(record.getPhone());
		if (isRegister > 0) {
			message.setStatue(FinalValue.PHONE_HASREGISGER);
			log.debug("该手机号已经注册");
			list.add("该手机号已经注册");
			message.setContenT(list);
			return message;
		}
		// 注册用户
		record.setLevel((byte) 1);
		record.setRegTime(new Date());
		record.setLastloginTime(new Date());
		record.setPwd((String) DataUtil.md5(record.getPwd()));
		String phoneString = record.getPhone();
		phoneString = phoneString.substring(0, 4) + "****"
				+ phoneString.substring(8, 11);
		record.setuName(phoneString);
		if (usersMapper.insert(record) > 0) {
			int uId = usersMapper.selectByPhone(record.getPhone());
			record.setuId(uId);
			log.debug("new user: uId is" + uId);
			request.getSession().setAttribute("user", record);
			log.debug(record.getPhone() + "：注册成功！");
			message.setStatue(FinalValue.SUCCESS);
		} else {
			log.debug(record.getPhone() + "：注册失败！");
			message.setStatue(FinalValue.FAILED);
		}
		return message;
	}

	/*
	 * 通过手机密码登录
	 * 
	 * @see
	 * com.ckc.cws.service.users.IUsers#selectByPhoneAndPwd(com.ckc.cws.bean
	 * .Users)
	 */
	public Message<Integer, List<String>> selectByPhoneAndPwd(Users record,
			HttpServletRequest request) {
		Message<Integer, List<String>> message = new Message<Integer, List<String>>();
		List<String> list = new ArrayList<String>();
		// 校验手机
		int phoneStatue = validation(record.getPhone(), FinalValue.PHONE);
		if (phoneStatue != FinalValue.SUCCESS) {
			message.setStatue(phoneStatue);
			return message;
		}
		// 校验密码
		int pwdStatue = validation(record.getPwd(), FinalValue.PWD);
		if (pwdStatue != FinalValue.SUCCESS) {
			message.setStatue(pwdStatue);
			return message;
		}
		record.setPwd((String) DataUtil.md5(record.getPwd()));
		Users user = usersMapper.selectByPhoneAndPwd(record);
		if (user != null) {
			log.debug("login success!");
			user.setLastloginTime(new Date());
			usersMapper.updateByPrimaryKeySelective(user); // 更新最新登录时间
			request.getSession().setAttribute("user", user);
			message.setStatue(FinalValue.SUCCESS);
			return message;
		} else {
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

	@Override
	public Message<Integer, String> sendPhoneMsg(String phone,
			HttpServletRequest request) {
		Message<Integer, String> message = new Message<Integer, String>();
		Random rd = new Random();
		int vcode = rd.nextInt(899999) + 100000;
		log.debug("生成随机手机验证码：" + vcode);
		String Phone = phone.trim();
		if (SmsUtil.sendSMS(Phone, String.valueOf(vcode), "29063")) {
			HttpSession ss = request.getSession();
			ss.setAttribute("vcode", vcode);
			message.setStatue(FinalValue.SUCCESS);
			message.setContenT("success");
		} else {
			message.setStatue(FinalValue.FAILED);
			message.setContenT("sms is busy");
		}
		return message;
	}

	@Override
	public Message<Integer, String> checkPhoneVCode(String vCode,
			HttpServletRequest request) {
		Message<Integer, String> message = new Message<Integer, String>();
		String vcode = String.valueOf(request.getSession()
				.getAttribute("vcode"));
		if (vCode != null && vCode.trim().equals(vcode)) {
			message.setStatue(FinalValue.SUCCESS);
			message.setContenT("success");
		} else {
			message.setStatue(FinalValue.FAILED);
			message.setContenT("vcode false");
			log.debug("vcode false");
		}
		return message;
	}

	@Override
	public Message<Integer, String> userLogout(HttpServletRequest request) {
		Message<Integer, String> message = new Message<Integer, String>();
		Users users = null;
		users = (Users) request.getSession().getAttribute("user");
		if (users != null) {
			request.getSession().removeAttribute("user");
		}
		log.debug("logout success");
		message.setStatue(FinalValue.SUCCESS);
		message.setContenT("logout success");
		return message;
	}

	@Override
	public Message<Integer, String> userChangePwd(Users users,
			HttpServletRequest request) {
		Message<Integer, String> message = new Message<Integer, String>();
		int uId = this.GetSessionUserId(request);

		// 校验密码
		int pwdStatue = validation(users.getPwd(), FinalValue.PWD);
		if (pwdStatue != FinalValue.SUCCESS) {
			message.setStatue(pwdStatue);
			message.setContenT("validation error!");
			return message;
		}

		// 校验密码是否一致
		Users users2 = usersMapper.selectByPrimaryKey(uId);
		if (DataUtil.md5(users.getPwd()).equals(users2.getPwd())) {
			message.setStatue(FinalValue.FAILED);
			message.setContenT("pwd is same");
			return message;
		}
		// 修改密码
		usersMapper.updateByPrimaryKeySelective(users);
		message.setStatue(FinalValue.SUCCESS);
		message.setContenT("change pwd success");
		return message;
	}

	@Override
	public Message<Integer, String> userUpdateInfo(Users users,
			HttpServletRequest request) {
		Message<Integer, String> message = new Message<Integer, String>();
		// 修改个人信息，不允许修改个人安全信息
		Users update = new Users();
		update.setuId(GetSessionUserId(request));
		update.setPhone(users.getPhone());
		update.setCity(users.getCity());
		update.setWeiboId(users.getWeiboId());
		update.setWeixinId(users.getWeixinId());
		update.setZhifubaoId(users.getZhifubaoId());
		update.setCarId(users.getCarId());
		if (usersMapper.updateByPrimaryKeySelective(update) > 0)
			message.setStatue(FinalValue.SUCCESS);
		else
			message.setStatue(FinalValue.FAILED);
		return message;
	}

	private int GetSessionUserId(HttpServletRequest request) {
		Users users = (Users) request.getSession().getAttribute("user");
		return users.getuId();
	}
	/*
	 * 图片上传
	 * @see com.ckc.cws.service.users.IUsers#userUploadPic(org.springframework.web.multipart.MultipartFile, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Message<Integer, String> userUploadPic(MultipartFile imgFile1,
			HttpServletRequest request) {
		Message<Integer, String> message = new Message<Integer, String>();
		/*MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得第1张图片（根据前台的name名称得到上传的文件）
		MultipartFile imgFile1 = multipartRequest.getFile("imgFile");*/
		UploadUtil uploadutil = new UploadUtil();
		String fileName = imgFile1.getOriginalFilename();
		try {
			String picPath = uploadutil.uploadImage1(request, imgFile1,
			imgFile1.getContentType(), fileName);
			
			//更新头像
			Users users = new Users();
			users.setuId(GetSessionUserId(request));
			users.setPic(picPath);
			if(usersMapper.updateByPrimaryKeySelective(users)>1){
				message.setStatue(FinalValue.SUCCESS);
				message.setContenT("update photo success");
			}else {
				message.setStatue(FinalValue.FAILED);
				message.setContenT("update photo failed");
			}
			
		} catch (IOException e) {
			log.error("update user pic error");
			message.setStatue(FinalValue.FAILED);
			message.setContenT("update error");
			e.printStackTrace();
		}
		return message;
	}

}
