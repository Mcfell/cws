package com.ckc.cws.view;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.ckc.cws.bean.*;
import com.ckc.cws.entity.Admin;
import com.ckc.cws.entity.User;
import com.ckc.cws.entity.UserListForm;
import com.ckc.cws.entity.UserMapForm;
import com.ckc.cws.entity.UserSetForm;
import com.ckc.cws.entity.locksList;
import com.ckc.cws.mapper.CarlocksMapper;
import com.ckc.cws.mapper.ParksMapper;
import com.ckc.cws.mapper.UsersMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by geely on 2015/11/22.
 */
@Controller
public class TestController {

    //todo     http://localhost:8080/baseType.do?age=10
    //todo 500 http://localhost:8080/baseType.do
    //todo 400 http://localhost:8080/baseType.do?age=abc
    @RequestMapping(value = "baseType.do")
    @ResponseBody
    public String baseType(int age){
        return "age:"+age;
    }

    //todo     http://localhost:8080/baseType2.do?age=10
    //todo     http://localhost:8080/baseType2.do
    //todo 400 http://localhost:8080/baseType2.do?age=abc
    @RequestMapping(value = "baseType2.do")
    @ResponseBody
    public String baseType2(Integer age){
    	
        return "age:"+age;
    }

    //todo http://localhost:8080/array.do?name=Tom&name=Lucy&name=Jim
    @RequestMapping(value = "array.do")
    @ResponseBody
    public String array(String[] name){
        StringBuilder sbf = new StringBuilder();
        for(String item : name){
            sbf.append(item).append(" ");
        }
        return sbf.toString();
    }

    //todo http://localhost:8080/object.do?name=Tom&age=10
    //TODO http://localhost:8080/object.do?name=Tom&age=10&contactInfo.phone=10086
    //TODO http://localhost:8080/object.do?user.name=Tom&admin.name=Lucy&age=10
    @RequestMapping(value = "object.do")
    @ResponseBody
    public String object(User user,Admin admin){
        return user.toString()+" "+admin.toString();
    }

    @InitBinder("user")
    public void initUser(WebDataBinder binder){
        binder.setFieldDefaultPrefix("user.");
    }
    @InitBinder("admin")
    public void initAdmin(WebDataBinder binder){
        binder.setFieldDefaultPrefix("admin.");
    }

    //TODO http://localhost:8080/list.do?users[0].name=Tom&users[1].name=Lucy
    //TODO http://localhost:8080/list.do?users[0].name=Tom&users[1].name=Lucy&users[20].name=Jim
    @RequestMapping(value = "list.do")
    @ResponseBody
    public String list(UserListForm userListForm){
        return "listSize:"+userListForm.getUsers().size() + "  " + userListForm.toString();
    }

    //TODO http://localhost:8080/set.do?users[0].name=Tom&users[20].name=Lucy
    @RequestMapping(value = "set.do")
    @ResponseBody
    public String set(UserSetForm userSetForm){
        return userSetForm.toString();
    }

    //TODO http://localhost:8080/map.do?users['X'].name=Tom&users['X'].age=10&users['Y'].name=Lucy
    @RequestMapping(value = "map.do")
    @ResponseBody
    public String map(UserMapForm userMapForm){
        return userMapForm.toString();
    }


//    {
//        "name": "Jim",
//            "age": 16,
//            "contactInfo": {
//                "address": "beijing",
//                "phone": "10010"
//              }
//    }
    //application/json
    @RequestMapping(value = "json.do")
    @ResponseBody
    public String json(@RequestBody User user){
        return user.toString();
    }


//    <?xml version="1.0" encoding="UTF-8" ?>
//    <admin>
//      <name>Jim</name>
//      <age>16</age>
//    </admin>
    //application/xml
    @RequestMapping(value = "xml.do")
    @ResponseBody
    public String xml(@RequestBody Admin admin){
        return admin.toString();
    }

    @RequestMapping(value = "date1.do")
    @ResponseBody
    public String date1(Date date1){
        return date1.toString();
    }

    @InitBinder("date1")
    public void initDate1(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
    }

    @RequestMapping(value = "date2.do")
    @ResponseBody
     public String date2(Date date2){
        return date2.toString();
    }



    @RequestMapping(value = "/book",method = RequestMethod.GET)
    @ResponseBody
    public String book(HttpServletRequest request){
        String contentType = request.getContentType();
        if(contentType == null){
            return "book.default";
        }else if(contentType.equals("txt")){
            return "book.txt";
        }else if(contentType.equals("html")){
            return "book.html";
        }
        return "book.default";
    }

    @RequestMapping(value = "/subject/{subjectId}",method = RequestMethod.GET)
    @ResponseBody
    public String subjectGet(@PathVariable("subjectId") String subjectId){
        return "this is a get method,subjectId:"+subjectId;
    }

    @RequestMapping(value = "/subject/{subjectId}",method = RequestMethod.POST)
    @ResponseBody
    public String subjectPost(@PathVariable("subjectId") String subjectId){
        return "this is a post method,subjectId:"+subjectId;
    }

    @RequestMapping(value = "/subject/{subjectId}",method = RequestMethod.DELETE)
    @ResponseBody
    public String subjectDelete(@PathVariable("subjectId") String subjectId){
        return "this is a delete method,subjectId:"+subjectId;
    }

    @RequestMapping(value = "/subject/{subjectId}",method = RequestMethod.PUT)
    @ResponseBody
    public String subjectPut(@PathVariable("subjectId") String subjectId){
        return "this is a put method,subjectId:"+subjectId;
    }
    @RequestMapping(value = "converter.do")
    @ResponseBody
    public String converter(Boolean bool){
        return bool.toString();
    }
    
    @Resource
    ParksMapper parksMapper;
    @RequestMapping("insertParks")
	public @ResponseBody String insertParks(Parks parks,locksList locks){
    	parksMapper.insertSelective(parks);
    	
		return "";
	}
    @Resource
    CarlocksMapper lockMapper;
    @RequestMapping("insertLocks")
    public @ResponseBody locksList insertLocks(locksList locks){
    	List<Carlocks> locks2 = locks.getCarlocks();
    	for (Carlocks lock : locks2) {
    		lockMapper.insertSelective(lock);
    	}
		return locks;
	}
   
    @RequestMapping("toInsert")
	public String toInsert(){
		return "testInsert";
	}
    @Resource
    UsersMapper usersMapper;
    @RequestMapping("testListResult")
    public @ResponseBody List<Users> selectAllUsers(){
		return usersMapper.selectAll();
	}
    /*
    @RequestMapping("getUserById")
	public @ResponseBody Users getUser(
				@RequestParam Integer id
			){
		return usersMapper.selectByPrimaryKey(id);
	}
	
	 * 参数param1 的值必须等于value1 ，参数param2 必须存在，值无所谓，参数param3 必须不存在，
	 * 只有当请求/user/getUser.do,并且满足指定的三个参数条件的时候才能访问到该方法。
	 
	@RequestMapping(value="ajaxUserRegister",method=RequestMethod.POST,params={"param1=value1","param2","!param3"})
	public @ResponseBody String getUser(
				@ModelAttribute Users user
			){
		return "insert success:"+usersMapper.insert(user);
	}
	
	 * 只有当请求头包含Accept 信息，且请求的 host 为 localhost 的时候才能正确的访问到testHeaders 方法
	 
	@RequestMapping(value="toUserRegister", headers={"host=localhost", "Accept"})
	public String insertUser(
				HttpServletRequest req
			){
		return "userRegister";
	}*/
	
}
