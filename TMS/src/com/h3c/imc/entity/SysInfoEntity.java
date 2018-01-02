package com.h3c.imc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author 71071
 * 系统信息实体
 * 
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licenseInfo")
//控制JAXB绑定类中属性和字段排序(默认情况下，jaxb编出来的是随机的顺序)
@XmlType(propOrder = {
		"name",
		"userLable",
		"version",
		"devInfoViewList"
	})
public class SysInfoEntity implements Serializable{
	
	/**
	 * 序列化
	 */
	@XmlTransient
	private static final long serialVersionUID = 6346015015365460307L;
	

	//将java对象的属性映射为节点
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "userlable")
	private String userLable;
	
	@XmlElement(name = "version")
	private String version;
	
	@XmlElement(name = "nes")
	DeviceInfoEntityViewList devInfoViewList = new DeviceInfoEntityViewList();
	
	public SysInfoEntity() {
		// TODO Auto-generated constructor stub
	}
	


	public SysInfoEntity(String name, String userLable, String version) {
		super();
		this.name = name;
		this.userLable = userLable;
		this.version = version;
	}



	public String getUserLable() {
		return userLable;
	}

	public void setUserLable(String userLable) {
		this.userLable = userLable;
	}



	public String getVersion() {
		return version;
	}






	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setVersion(String version) {
		this.version = version;
	}



	public DeviceInfoEntityViewList getDevInfoViewList() {
		return devInfoViewList;
	}



	public void setDevInfoViewList(DeviceInfoEntityViewList devInfoViewList) {
		this.devInfoViewList = devInfoViewList;
	}



}
