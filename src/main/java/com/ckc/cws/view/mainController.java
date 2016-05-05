package com.ckc.cws.view;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckc.cws.bean.Users;
import com.ckc.cws.mapper.UsersMapper;

@Controller
public class mainController {
	
	
	/**
	 * @RequestHeader �� HttpServletRequest ͷ��Ϣ�� Controller ��������
	 */
	@RequestMapping("testRequestHeader")
    public String testRequestHeader(@RequestHeader("Host") String hostAddr,
    		@RequestHeader String Host, @RequestHeader String host) {
      System.out.println(hostAddr + "-----" + Host + "-----" + host );  
      return "requestHeader";
    }
	/**
	 * @CookieValue �� cookie ��ֵ�� Controller ��������
	 */
	@RequestMapping("/testCookie")  
    public String testCookieValue(@CookieValue("hello")String cookieOne, 
    		@CookieValue String cookieTwo) {  //��һ��ȡ cookie �е�����Ϊ cookieTwo �� cookie ֵ
      return "cookieValue" ;  
    }
	/**
	 * @RequestParam ������ע���������Ĳ�����URI�в����Ķ�Ӧ
	 * @RequestMapping(value="testParams" , params={ "param1=value1" , "param2" , "!param3" })
	 * @RequestMapping (value= "testMethod" , method={RequestMethod.GET , RequestMethod.DELETE })
	 */
	@RequestMapping (value= "testMethod" , method={RequestMethod.GET , RequestMethod.DELETE })
    public String listBoardTopic(@RequestParam("id")int topicId, ModelMap model) {
		return "index";
	}

	/**
	 * @ModelAttribute �� @SessionAttributes ���ݺͱ�������
	 * SpringMVC ֧��ʹ�� @ModelAttribute �� @SessionAttributes �ڲ�ͬ��ģ�ͺͿ�����֮�乲�����ݡ�
	 * @ModelAttribute������ע��Controller�����ϣ���ʾ�ò����� value ��Դ�� model ��"queryBean"���������浽 model ��
	 *  �����ڷ����ϣ���ʾ�÷����ķ���ֵ�����浽model��÷������ڴ���������ִ��֮ǰִ�У�Ȼ��ѷ��صĶ������� session ��ģ�������У�
	 */
	public String handleInit(@ModelAttribute("queryBean") Users sUser,Model model) {
		return null;
	}
}
