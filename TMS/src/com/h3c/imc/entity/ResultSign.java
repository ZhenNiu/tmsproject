package com.h3c.imc.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author 71071
 *	第一次握手，返回的成功与否的结果码
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="resultmodel")
@XmlType(propOrder={"resultsign","resultmessage"})
public class ResultSign {

	@XmlElement(name = "resultsign")
	private String resultsign;
	
	@XmlElement(name = "resultmessage")
	private  String resultmessage;

	public String getResultsign() {
		return resultsign;
	}

	public void setResultsign(String resultsign) {
		this.resultsign = resultsign;
	}

	public String getResultmessage() {
		return resultmessage;
	}

	public void setResultmessage(String resultmessage) {
		this.resultmessage = resultmessage;
	}
	
}
