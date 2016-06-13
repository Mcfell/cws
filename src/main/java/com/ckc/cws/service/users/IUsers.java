package com.ckc.cws.service.users;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

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

    Message<Integer, List<String>> insert(Users record,String vcode,
    		HttpServletRequest request);
    
    Message<Integer, List<String>> selectByPhoneAndPwd(Users record,
    		HttpServletRequest request);
    
    Message<Integer, String> sendPhoneMsg(String phone,
			HttpServletRequest request);

	Message<Integer, String> checkPhoneVCode(String vCode,
			HttpServletRequest request);

	Message<Integer, String> userLogout(HttpServletRequest request);

	Message<Integer, String> userChangePwd(Users users,
			HttpServletRequest request);

	Message<Integer, String> userUpdateInfo(Users users,
			HttpServletRequest request);
	
	public Message<Integer, String> userUploadPic(MultipartFile imgFile1,
			HttpServletRequest request);
	
    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);


	
}
