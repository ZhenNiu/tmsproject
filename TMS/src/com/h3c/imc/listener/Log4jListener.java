package com.h3c.imc.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jListener implements ServletContextListener {
	private Logger log=Logger.getLogger(Log4jListener.class); ; 
	
	public Log4jListener(){
		
	}  

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		 String name = arg0.getServletContext().getServletContextName();  
	     log.info("servletContext shut down " + (name == null ? "" : name));
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();  
		String realpath = context.getRealPath("/WEB-INF/log4j.properties");  
        //String fileSep = System.getProperty("file.separator");  
         
        System.setProperty("webApp.root", "/WEB-INF");
        if(realpath != null) {
        	PropertyConfigurator.configure(realpath);                    
            String name = context.getServletContextName();  
            
            log.info("servlet ready:" + (name == null ? "" : name));  
              
        }  
      
	}

}
