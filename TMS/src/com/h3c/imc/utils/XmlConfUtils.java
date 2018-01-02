package com.h3c.imc.utils;


import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.h3c.imc.bootstrap.ServerStartup;


public final class XmlConfUtils {
	private static Log log=LogFactory.getLog(XmlConfUtils.class);
	//private static XmlConfigBean conf=getXmlConfigBean();
	public static void main(String[] args) throws JAXBException, IOException {
		System.out.println(getXmlConfigBean().toString());
		
		//System.out.println(getImcXmlConfigBean());
		
	}
	/**
	 * 取得配置对象,Config类的属性中保存有配置信息
	 * @return
	 */
	public static XmlConfigBean getXmlConfigBean(){
		InputStream input=Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.xml");
		XmlConfigBean conf = new XmlConfigBean();
		try {
			conf = JAXBUtils.xmlFileInputStream2Object(input,XmlConfigBean.class);
			//conf.setPath();
		} catch (JAXBException e) {
			log.error(null,e);
		} catch (IOException e) {
			log.error(null,e);
		}
		return conf;
	}
	/**
	 * 如果配置文件放在iMC的conf目录下,则使用这个方法取得配置文件
	 * @return
	 */
/*	public static XmlConfigBean getImcXmlConfigBean(){
		//String classesPath=Class.class.getClass().getResource("/").getPath();
		//InputStream input=Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml");
		File xmlConFile = new File(ServerStartup.getImcHome(), "conf/ycyh/ycyh.xml");
		XmlConfigBean conf = new XmlConfigBean();
		try {
			conf = JAXBUtils.xmlFile2Object(xmlConFile,XmlConfigBean.class);
			
		} catch (JAXBException e) {
			log.error(null,e);
		} catch (IOException e) {
			log.error(null,e);
		}
		return conf;
	}*/
	/*public static InputStream getExcel(){
		String excelName = conf.getExcelName();
		InputStream input=Thread.currentThread().getContextClassLoader().getResourceAsStream(excelName);
		return input;
	}*/
}
