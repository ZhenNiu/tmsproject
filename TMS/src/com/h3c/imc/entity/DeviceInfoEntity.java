package com.h3c.imc.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 设备信息模型 实体
 * @author 71071
 *	保存设备信息的实体
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "device")
@XmlType(propOrder = {
		"ID",
		"sysName",
		"userLabel",
		"runTime",
		"vendor",
		"model",
		"category",
		"ip",
		"type"
		
	})
public class DeviceInfoEntity {
	
	/**
	 * 设备唯一标示id
	 */
	@XmlElement(name = "id")
	private String  ID;
	
	/**
	 * 用户标签
	 */
	@XmlElement(name = "userlabel")
	private String  userLabel;
 
	/**
	 * 设备名称
	 */
	@XmlElement(name = "sysName")
	private String  sysName;
	
	/**
	 * 启动时间
	 */
	@XmlElement(name = "runTime")
	private String  runTime;
	
	/**
	 * 设备ip管理
	 */
	@XmlElement(name = "ip")
	private String  ip;
	
	/**
	 * 设备厂家描述
	 */
	@XmlElement(name = "vendor")
	private String  vendor;
	
	/**
	 * 设备类型
	 */
	@XmlElement(name = "netype")
	private String  type;
	
	/**
	 * 设备型号
	 */
	@XmlElement(name = "model")
	private String  model;
	
	/**
	 * 设备类型
	 */
	@XmlElement(name = "category")
	private String  category;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}


	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	



}
