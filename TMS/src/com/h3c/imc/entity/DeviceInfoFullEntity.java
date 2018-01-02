package com.h3c.imc.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 设备信息模型 实体
 * @author 71071
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "device")
@XmlType(propOrder = {
		"ID",
		"sysName"
	})
public class DeviceInfoFullEntity {

	
	/**
	 * 设备唯一标示id
	 */
	@XmlElement(name = "id")
	private String  ID;
 
	/**
	 * 系统名称
	 */
	@XmlElement(name = "sysName")
	private String  sysName;
}
