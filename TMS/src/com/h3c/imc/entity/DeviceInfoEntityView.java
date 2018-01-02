package com.h3c.imc.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author 71071
 * 响应到客户端的实体序列化为xml
 */
@XmlRootElement(name="ne")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"neid",
					"sysName",
					"userLabel",
					"runTime",
					"nanager_IP",
					"vendor",
					"soft_version",
					"model",
					"type",
					"memorySize",
					"flashSize",
					"cpuSize"
					})
public class DeviceInfoEntityView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 设备唯一标示id
	 */
	@XmlElement(name = "neid")
	private String  neid;
	
	/**
	 * 设备名称
	 */
	@XmlElement(name = "nename")
	private String  sysName;
	
	
	/**
	 * 网管用户定义设备名称
	 */
	@XmlElement(name = "userLabel")
	private String  userLabel;
	
	
	
	/**
	 * 设备启动时间
	 */
	@XmlElement(name = "runtime")
	private String  runTime;
	
	
	/**
	 * 设备ip管理
	 */
	@XmlElement(name = "manager_ip")
	private String  nanager_IP;
	
	
	/**
	 * 设备厂家描述
	 */
	@XmlElement(name = "vendor")
	private String  vendor;
	
	/**
	 * 设备软件版本
	 */
	@XmlElement(name = "soft_version")
	private String  soft_version;
	
	/**
	 * 设备型号
	 */
	@XmlElement(name = "nemodel")
	private String  model;
	
	
	/**
	 * 设备类型
	 */
	@XmlElement(name = "netype")
	private String  type;
	
	
	/**
	 * 设备内存大小
	 */
	@XmlElement(name = "memory_size")
	private String  memorySize;
	
	
	
	/**
	 * 设备闪存大小
	 */
	@XmlElement(name = "flash_size")
	private String  flashSize;
	
	
	/**
	 * 设备cpu个数
	 */
	@XmlElement(name = "cpu_size")
	private String  cpuSize;

	
	
	/**
	 * <manager_ip>设备管理IP</manager_ip>
<vendor>设备厂家描述</vendor>
<soft_version>设备软件版本</soft_version>
<nemodel>设备型号</nemodel>
<netype>设备类型(路由器，三层交换机，二层交换机，主机，防火墙，数据库服务器等)</netype>
<memory_size>设备内存大小</memory_size>
<flash_size>设备闪存大小</flash_size>
<cpu_size>设备cpu个数</cpu_size>

	 * @param neid
	 */
	
	

	

	public void setID(String neid) {
		this.neid = neid;
	}


	public void setSysName(String sysName) {
		this.sysName = sysName;
	}


	public String getNeid() {
		return neid;
	}


	public void setNeid(String neid) {
		this.neid = neid;
	}


	public String getUserLabel() {
		return userLabel;
	}


	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}


	public String getRunTime() {
		return runTime;
	}


	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}


	public String getNanager_IP() {
		return nanager_IP;
	}


	public void setNanager_IP(String nanager_IP) {
		this.nanager_IP = nanager_IP;
	}


	public String getVendor() {
		return vendor;
	}


	public void setVendor(String vendor) {
		this.vendor = vendor;
	}


	public String getSoft_version() {
		return soft_version;
	}


	public void setSoft_version(String soft_version) {
		this.soft_version = soft_version;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getMemorySize() {
		return memorySize;
	}


	public void setMemorySize(String memorySize) {
		this.memorySize = memorySize;
	}


	public String getFlashSize() {
		return flashSize;
	}


	public void setFlashSize(String flashSize) {
		this.flashSize = flashSize;
	}


	public String getCpuSize() {
		return cpuSize;
	}


	public void setCpuSize(String cpuSize) {
		this.cpuSize = cpuSize;
	}


	public String getSysName() {
		return sysName;
	}

	
	
	
}
