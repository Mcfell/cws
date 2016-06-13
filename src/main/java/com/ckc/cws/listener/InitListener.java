package com.ckc.cws.listener;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ckc.cws.util.ServerConfig;


/**
 * Application Lifecycle Listener implementation class InitListener
 * 
 */
@WebListener
public class InitListener implements ServletContextListener
{

	private static final Log logger = LogFactory.getLog(InitListener.class);
	private static ScheduledThreadPoolExecutor stpe = null;

	/**
	 * Default constructor.
	 */
	public InitListener()
	{
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event)
	{
		ServerConfig.getServerConfig().initialize(event);
		logger.debug(ServerConfig.getServerConfig().getRealPath() + "\n"
				+ ServerConfig.getServerConfig().getContextpath());
		logger.debug("RealPath:" + event.getServletContext().getRealPath("/"));
		logger.debug("Contextpath:"+ event.getServletContext().getContextPath());
		/*ApplicationContext app = new ClassPathXmlApplicationContext("spring/applicationContext-spring-memcached.xml");
		MemcachedClient memcachedClient = (MemcachedClient) app.getBean("memcachedClient");
		*/
		/*ApplicationContext ac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(event.getServletContext());
		IdGenerator.getInstance().initialize(ac.getBean("uniqueIdDAO"));
		stpe = new ScheduledThreadPoolExecutor(5); 
		TaxRateExecutor taxRateThread =  new TaxRateExecutor(ac.getBean("TaxRateDAO"));
		//隔3秒后开始执行任务，并且在上一次任务开始后隔3秒再执行3次；
		stpe.scheduleWithFixedDelay(taxRateThread,3, 3, TimeUnit.SECONDS);*/
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0)
	{
		/*IdGenerator.getInstance().destroy();
		stpe.shutdown();*/
	}

}
