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
	 * @RequestHeader 绑定 HttpServletRequest 头信息到 Controller 方法参数
	 */
	@RequestMapping("testRequestHeader")
    public String testRequestHeader(@RequestHeader("Host") String hostAddr,
    		@RequestHeader String Host, @RequestHeader String host) {
      System.out.println(hostAddr + "-----" + Host + "-----" + host );  
      return "requestHeader";
    }
	/**
	 * @CookieValue 绑定 cookie 的值到 Controller 方法参数
	 */
	@RequestMapping("/testCookie")  
    public String testCookieValue(@CookieValue("hello")String cookieOne, 
    		@CookieValue String cookieTwo) {  //后一个取 cookie 中的命名为 cookieTwo 的 cookie 值
      return "cookieValue" ;  
    }
	/**
	 * @RequestParam 用来标注请求处理方法的参数和URI中参数的对应
	 * @RequestMapping(value="testParams" , params={ "param1=value1" , "param2" , "!param3" })
	 * @RequestMapping (value= "testMethod" , method={RequestMethod.GET , RequestMethod.DELETE })
	 */
	@RequestMapping (value= "testMethod" , method={RequestMethod.GET , RequestMethod.DELETE })
    public String listBoardTopic(@RequestParam("id")int topicId, ModelMap model) {
		return "index";
	}

	/**
	 * @ModelAttribute 和 @SessionAttributes 传递和保存数据
	 * SpringMVC 支持使用 @ModelAttribute 和 @SessionAttributes 在不同的模型和控制器之间共享数据。
	 * @ModelAttribute声明标注在Controller参数上，表示该参数的 value 来源于 model 里"queryBean"，并被保存到 model 里
	 *  声明在方法上，表示该方法的返回值被保存到model里。该方法将在处理器方法执行之前执行，然后把返回的对象存放在 session 或模型属性中，
	 */
	public String handleInit(@ModelAttribute("queryBean") Users sUser,Model model) {
		return null;
	}
}
