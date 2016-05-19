package com.cws.mybaties.test; 

import javax.annotation.Resource;  
  

import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import com.ckc.cws.bean.Users;
import com.ckc.cws.mapper.UsersMapper;
import com.imooc.common.MyPropertyEditor;
 
  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring/applicationContext-spring-mybatis.xml"})  
  
public class TestMyBatis {  
	private static Logger logger = LoggerFactory.getLogger(MyPropertyEditor.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private UsersMapper usersMapper = null;  
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
        Users user = usersMapper.selectByPrimaryKey(1);  
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
        logger.info(user.toString());  
    }  
}  