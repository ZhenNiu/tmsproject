package com.h3c.imc.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "resultmodel")
@XmlType(propOrder = {
		"resultSign",
		"devInfoViewList"
	})
public class DevInfoResponseResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2250049750759736253L;
	@XmlElement(name = "resultsign")
	private ResultSign resultSign = new ResultSign();
	@XmlElement(name = "nes")
	DeviceInfoEntityViewList  devInfoViewList = new DeviceInfoEntityViewList();
	public ResultSign getResultSign() {
		return resultSign;
	}
	public void setResultSign(ResultSign resultSign) {
		this.resultSign = resultSign;
	}
	public DeviceInfoEntityViewList getDevInfoViewList() {
		return devInfoViewList;
	}
	public void setDevInfoViewList(DeviceInfoEntityViewList devInfoViewList) {
		this.devInfoViewList = devInfoViewList;
	}
	
	
	
}
