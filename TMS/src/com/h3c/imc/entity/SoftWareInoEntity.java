package com.h3c.imc.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * /icc/deviceSoft/list
 * @author 71071
 *
 */
@XmlRootElement(name="deviceSoft")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoftWareInoEntity {

	/**
	 * 
	 * 备软件ID。设备软件库中的软件唯一标识。
	 * 
	 * 
	 */
	@XmlElement(name = "softId")
	private String  softId; 
	
	/**	设备软件名称。
			*/
	@XmlElement(name = "softwareName")
	private String  softwareName;
		/**
		 * 设备软件大小。
		 */
	@XmlElement(name = "softwareSize")
	private String  softwareSize;	
	
	/**
	 * 设备软件描述
	 */
	@XmlElement(name = "softwareType")
	private String  softwareType;	
	
	
	/**
	 * 设备软件版本描述。
	 */
	@XmlElement(name = "softwareVersion")
	private String  softwareVersion;	
	
	/**
	 * 设备软件创建时间。
	 */
	
	@XmlElement(name = "createAt")
	private String  createAt;	
	
	
	/**
	 * 配置文件适配的设备型号ID，多个ID用“；”隔开，若为空则默认为适配所有设备型号
	 */
	
	@XmlElement(name = "avaDev")
	private String  avaDev;	
	
	/**
	 * 设备软件说明。
	 */
	@XmlElement(name = "softwareMemo")
	private String  softwareMemo;	
	
	
	/**
	 * 软件是否从普通软件进入的常量,1是，2从Bootrom进入
	 */
	
	@XmlElement(name = "isNormal")
	private String  isNormal;	
	
	/**
	 * 同步类型。
	 */
	@XmlElement(name = "syncType")
	private String  syncType;

	public String getSoftId() {
		return softId;
	}

	public void setSoftId(String softId) {
		this.softId = softId;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getSoftwareSize() {
		return softwareSize;
	}

	public void setSoftwareSize(String softwareSize) {
		this.softwareSize = softwareSize;
	}

	public String getSoftwareType() {
		return softwareType;
	}

	public void setSoftwareType(String softwareType) {
		this.softwareType = softwareType;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getAvaDev() {
		return avaDev;
	}

	public void setAvaDev(String avaDev) {
		this.avaDev = avaDev;
	}

	public String getSoftwareMemo() {
		return softwareMemo;
	}

	public void setSoftwareMemo(String softwareMemo) {
		this.softwareMemo = softwareMemo;
	}

	public String getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(String isNormal) {
		this.isNormal = isNormal;
	}

	public String getSyncType() {
		return syncType;
	}

	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}	
	
	
	
/**
 * 
softwareType	设备软件描述。
字符串（String）类型。
softwareVersion	
字符串（String）类型。
createAt	
字符串（String）类型。
avaDev	。
字符串（String）类型。
softwareMemo	
字符串（String）类型。
isNormal	。
长整数（long）类型。
syncType	
长整数（long）类型。



	 */
	
	
}
