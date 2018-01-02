package com.h3c.imc.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
/**
 * 1.java对象与xml字符串之间互转换   xmlStr2Ojbect 和 object2XmlStr
 * 2.java对象与xml文件之间相互转换  
 * 3.xml字符串与xml文件之间相互转换
 *
 */
public final class JAXBUtils {
	public static  String ECODING="UTF-8";

	private JAXBUtils(){}
	
	/**
	 * xmlStr2Ojbect:把xml字符串转换成java对象(内部使用unmarshal方法).
	 * @param xml  必须是xml格式的字符串
	 * @param clazz java类
	 * @return T   java对象
	 * @throws JAXBException
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlStr2Ojbect(String xml,Class<T> clazz) throws JAXBException, IOException{
		if(StringUtils.isBlank(xml)) return null;
		StringReader reader = null;
		T obj = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			reader = new StringReader(xml);
			obj =(T) unmarshaller.unmarshal(reader);
		}finally{
			if(reader!=null) reader.close();
		}
		return obj;
	}
	
	/**
	 * 把java对象转换成xml字符串
	 * @param obj 要转换成xml字符串的java对象
	 * @return xml字符串
	 */
	public static <T> String object2XmlStr(T obj) throws JAXBException, IOException{
		if(obj==null) return null;
		ByteArrayOutputStream outMesg = null;
		String xmlStr = "";   
		try {
			outMesg = new ByteArrayOutputStream();
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, JAXBUtils.ECODING);
			marshaller.marshal(obj, outMesg);
		} finally {
			if (outMesg != null) outMesg.close();
		}
			xmlStr = outMesg.toString(JAXBUtils.ECODING);
		return xmlStr;
	}
	/**
	 * 把java对象转换成xml文件,保存到磁盘上.
	 * @param obj
	 * @param xmlFile
	 * @throws JAXBException
	 * @throws IOException
	 */
	
	public static void object2XmlFile(Object obj,File xmlFile) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, JAXBUtils.ECODING);
		marshaller.marshal(obj, xmlFile);
	}
	/**
	 * 将xml文件转换成java类
	 * @param xmlFile
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlFile2Object(File xmlFile,Class<T> clazz) throws JAXBException, IOException{
		T obj = null;
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		obj =(T) unmarshaller.unmarshal(xmlFile);
		return obj;
	}
	/**
	 * 将xml文件转换成java类
	 * @param xmlFile InputStream
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlFileInputStream2Object(InputStream xmlFile,Class<T> clazz) throws JAXBException, IOException{
		T obj = null;
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		obj =(T) unmarshaller.unmarshal(xmlFile);
		return obj;
	}
}
