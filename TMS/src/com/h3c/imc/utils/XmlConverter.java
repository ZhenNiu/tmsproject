package com.h3c.imc.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * 
 * XML文档转换器。
 *
 * @param <T> 转换对象的类型
 */
public class XmlConverter<T> {

	/** 日志记录对象。 */
	private Log runLog = LogFactory.getLog(XmlConverter.class);
	
	/** 实体类型 */
	private Class<T> entityClass = null;
	
	/**
	 * 构造函数。
	 * @param entityClass 实体类型
	 */
	public XmlConverter(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	/**
	 * 创建没有表头的xmlString
	 * @param t 需要转换的对象
	 * @return
	 */
	public String createJaxbXml(T t) {
		String xmlString = "";
		if (t == null) {
			runLog.info("createJaxbXml object is null.");
			return xmlString;
		}
		try {
			JAXBContext context = JAXBContext.newInstance(entityClass);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			// 去掉xml的头
			//marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
			// 格式化xml
			//marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// 把对象转化成xml字符串
			StringWriter writer = new StringWriter();
			marshaller.marshal(t, writer);
			String xmlDocument = writer.toString().trim();
			xmlString = xmlDocument;
			runLog.info("createJaxbXml xmlString: " + xmlString);
		} catch (JAXBException e) {
			runLog.error("createJaxbXml failure.", e);
		}
		return xmlString;
	}
	
	/**
	 * 将XML文件解析并封装为实体集合。
	 * 
	 * @param xmlContent
	 *            XML文档内容
	 * @param root
	 *            是否去除根节点
	 * @return 解析后的实体集合
	 */
	public List<T> unmarshal(String xmlContent, boolean root) {
		// 构建结果实体集合
		List<T> resultList = new ArrayList<T>();
		if (StringUtils.isBlank(xmlContent)) {
			return resultList;
		}
		// 创建工厂对象，用户进行XML数据解析
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 解析XML数据，生成DOM对象
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringReader sr = new StringReader(xmlContent);
			InputSource is = new InputSource(sr);
			Document document = builder.parse(is);

			// 将根节点下的所有元素解析为对象
			NodeList nodeList = null;
			if (root) {
				// 去除根节点元素
				Element list = document.getDocumentElement();
				nodeList = list.getChildNodes();
			} else {
				// 将根节点下的所有元素解析为对象
				nodeList = document.getChildNodes();
			}

			int size = nodeList.getLength();
			//add by chenya 0503
			/*if (nodeList.getLength()== 0){
				resultList.add(null);
			}*///end  chenya
			
			for (int i = 0; i < size; i++) {
				resultList.add(createEntity(nodeList.item(i)));
			}
			// 释放内存
			is = null;
			try {
				sr.close();
			} catch (Exception e) {
			}
			sr = null;
			builder = null;
		} catch (Exception e) {
			runLog.error(e);
		} finally {
			factory = null;
		}
		return resultList;
	}

	
	/**
	 * 将XML节点转换为对象(该对象必须配置@XmlRootElement属性)。
	 * 
	 * @param node
	 *            XML节点
	 * @return 转换后的对象
	 */
	@SuppressWarnings({ "unchecked" })
	public T unmarshal(String xml) {
		if(StringUtils.isBlank(xml)) return null;
		StringReader reader = null;
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(entityClass);
			Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
			reader = new StringReader(xml);
			return (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException ex) {
			runLog.error(ex);
		}
		return null;
	}
	
	/**
	 * 将xml文件转换为对象
	 * @param context 文件路径
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public T convertXmlString(String context) {
        try {
            JAXBContext jc = JAXBContext.newInstance(entityClass);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(new File(context));
        } catch (JAXBException e) {
        	runLog.error(e);
        }
        return null;
    }
    
    /**
     * 将xml文件转换为对象
     * @param fileConf  xml文件
     * @return
     */
    @SuppressWarnings("unchecked")
    public T convertXmlFile(File fileConf) {
    	InputStream inputStrem = null;
    	try {
            JAXBContext jc = JAXBContext.newInstance(entityClass);
            Unmarshaller u = jc.createUnmarshaller();
            inputStrem = new FileInputStream(fileConf);
            return (T) u.unmarshal(inputStrem);
        } catch (Exception e) {
        	runLog.error(e);
        } 
    	return null;
    }
    
    /**
     * 将xml文件转换为对象
     * @param fileConf  xml文件
     * @return
     */
    @SuppressWarnings("unchecked")
    public T convertXmlInputStream(String context) {
    	InputStream inputStrem = null;
    	try {
            JAXBContext jc = JAXBContext.newInstance(entityClass);
            Unmarshaller u = jc.createUnmarshaller();
            inputStrem = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(context);
            return (T) u.unmarshal(inputStrem);
        } catch (Exception e) {
        	runLog.error(e);
        } 
    	return null;
    }

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	/**
	 * 将数据实体装载为XML文档内容。
	 * 
	 * @param entities
	 *            数据实体
	 * @return 装载后的XML文档内容
	 */
	public String marshal(List<T> entities) {
		// 初始化返回值
		String xmlContent = null;
		// 入参判断
		if (null == entities || 0 >= entities.size()) {
			return xmlContent;
		}
		// 先将内存中的数据结构表示为DOM文档格式
		// 然后再通过XSLT直接转换为XML字符流
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		ByteArrayOutputStream baos = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();

			// 将数据实体转换为XML元素并加入到根节点中
			for (T entity : entities) {
				addEntityToElement(entity, document);
			}

			// 数据全部存入DOM树结构中后，使用JAXP提供的XSLT直接进行转换
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			// 设置目标XML文档的语言
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// 转换源为存放输入信息的DOM数据结构
			DOMSource source = new DOMSource(document);
			// 构造转换目的流
			baos = new ByteArrayOutputStream();
			// 生成表示转换结果的数据结构，并设置转换目的流的字符编码
			StreamResult result = new StreamResult(baos);
			// 调用XSLT进行数据转换
			transformer.transform(source, result);
			// 将转换结果保存到xmlData属性中，注意对XML字符编码的转换
			xmlContent = baos.toString("UTF-8");

			// 清空引用
			result = null;
			source = null;
			transformer = null;
			tFactory = null;
			builder = null;
		} catch (Exception e) {
			runLog.error(e);
		} finally {
			// 释放内存
			factory = null;
			if (null != baos) {
				try {
					baos.close();
				} catch (Exception e) {
					runLog.error(e);
				}
			}
		}
		return xmlContent;
	}

	
	/**
	 * 将对象转换为XML节点(需转换对象必须配置@XmlRootElement属性)。
	 * 
	 * @param t
	 *            需要转换的对象
	 * @param parentNode
	 *            转换后节点的父节点
	 */
	private void addEntityToElement(T t, Node parentNode) {
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(entityClass);
			Marshaller marshaller = jaxbCtx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(t, parentNode);
		} catch (JAXBException ex) {
			runLog.error(ex);
		}
	}

	/**
	 * 将XML节点转换为对象(该对象必须配置@XmlRootElement属性)。
	 * 
	 * @param node
	 *            XML节点
	 * @return 转换后的对象
	 */
	@SuppressWarnings("unchecked")
	private T createEntity(Node node) {
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(entityClass);
			Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
			return (T) unmarshaller.unmarshal(node);
		} catch (JAXBException ex) {
			runLog.error(ex);
		}
		return null;
	}
}
