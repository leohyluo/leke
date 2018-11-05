package com.sugar.leke.config;

import com.sugar.leke.framework.spring.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class DiagnosisContextListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

	/*@Override
    public void contextInitialized(ServletContextEvent event) {
		
	}*/
    

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("DiagnosisContextListener contextInitialized starting...");
        //获取spring上下文，初始化全局SpringContextHolder
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        SpringContextHolder.setApplicationContext(applicationContext);
//        logger.info("创建公众号菜单");
//        officalAccountService.createMenu();
        logger.info("DiagnosisContextListener contextInitialized completed");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

}
