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
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "resultmodel")
@XmlType(propOrder = {
		"resultSign",
		"sysInfoEntity"
	})
public class SysInfoResponseResult implements Serializable{
	
	
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "resultsign")
	private ResultSign resultSign = new ResultSign();
	
	@XmlElement(name = "sys")
	private SysInfoEntity sysInfoEntity = new SysInfoEntity();
	
	public SysInfoResponseResult() {
		super();
	}

	public SysInfoResponseResult(ResultSign resultSign, SysInfoEntity sysInfoEntity) {
		super();
		this.resultSign = resultSign;
		this.sysInfoEntity = sysInfoEntity;
	}

	public ResultSign getResultSign() {
		return resultSign;
	}

	public void setResultSign(ResultSign resultSign) {
		this.resultSign = resultSign;
	}

	public SysInfoEntity getSysInfoEntity() {
		return sysInfoEntity;
	}

	public void setSysInfoEntity(SysInfoEntity sysInfoEntity) {
		this.sysInfoEntity = sysInfoEntity;
	}



/*	public <T> ResponseResult forEntity(List<T> entityList,Class<T> entiyClass){
		for(T entity:entityList){
			responseList.add(entity);
		}
		return new ResponseResult(responseList);.0
	}*/

	


	
	
/*	public static ResponseResult buildResult(List<T> responseList,Class<T> responseEntity){
		responseEntity = responseEntity;
		responseList = responseList;
		return null;
	}*/
	
	
}
