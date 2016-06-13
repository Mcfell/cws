package com;
import static junit.framework.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemcachedSpringTest {

	private ApplicationContext app;
	private MemcachedClient memcachedClient;

	@Before
	public void init() {
		app = new ClassPathXmlApplicationContext("spring/applicationContext-spring-memcached.xml");
		memcachedClient = (MemcachedClient) app.getBean("memcachedClient");
	}

	@Test
	public void test() {
		try {
			// 设置/获取
			memcachedClient.set("zlex", 36000, "set/get");
			assertEquals("set/get", memcachedClient.get("zlex"));
			
			// 替换
			memcachedClient.replace("zlex", 36000, "replace");
			assertEquals("replace", memcachedClient.get("zlex"));

			// 移除
			/*memcachedClient.delete("zlex");
			assertNull(memcachedClient.get("zlex"));*/
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
            if(null != memcachedClient){
                try {
                    memcachedClient.shutdown();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
	}
}
