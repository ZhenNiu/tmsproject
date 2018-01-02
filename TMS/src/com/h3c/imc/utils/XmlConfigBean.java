package com.h3c.imc.utils;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "list")
public class XmlConfigBean {
	/**配置文件所在路径*/
	@XmlElement(name = "path")
	private String path;
@XmlElement(name = "IMCIp")
	
	/*
	 * 分部式部署imc平台ip
	 * */
    private String  IMCIp;
	
	/*
	 * 分部式部署imc平台端口
	 * */
    private String IMCPort;
	
    
	/*
	 * 分部式部署imc EIA port
	 * */
    private String ShareKey;
    
    
    /*
	 * imc登录账号
	 * */
    private String IMCUserName;
    
    /*
	 * imc登录密码
	 * */
    private String IMCPassword;

    /*
	 * imc接入用户服务名称
	 * */
    private String serviceTemplateName;
    
    private String suffix;
   
	public String getIMCIp() {
		return IMCIp;
	}



	public void setIMCIp(String iMCIp) {
		IMCIp = iMCIp;
	}



	public String getIMCPort() {
		return IMCPort;
	}



	public void setIMCPort(String iMCPort) {
		IMCPort = iMCPort;
	}



	public String getShareKey() {
		return ShareKey;
	}



	public void setShareKey(String shareKey) {
		ShareKey = shareKey;
	}



	public String getIMCUserName() {
		return IMCUserName;
	}



	public void setIMCUserName(String iMCUserName) {
		IMCUserName = iMCUserName;
	}



	public String getIMCPassword() {
		return IMCPassword;
	}



	public void setIMCPassword(String iMCPassword) {
		IMCPassword = iMCPassword;
	}



	public String getServiceTemplateName() {
		return serviceTemplateName;
	}



	public void setServiceTemplateName(String serviceTemplateName) {
		this.serviceTemplateName = serviceTemplateName;
	}



	public String getSuffix() {
		return suffix;
	}



	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}



	@Override
	public String toString() {
		return "XmlConfigBean [IMCIp=" + IMCIp + ", IMCPort=" + IMCPort + ", ShareKey=" + ShareKey + ", IMCUserName="
				+ IMCUserName + ", IMCPassword=" + IMCPassword + ", serviceTemplateName=" + serviceTemplateName + "]";
	}
	
	
}
