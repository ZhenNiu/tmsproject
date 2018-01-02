package com.h3c.imc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "devInfoViewList")
public class DeviceInfoEntityViewList {
	
	@XmlElement(name = "ne")
	private List<DeviceInfoEntityView> deviceInfoEntity = new ArrayList<DeviceInfoEntityView>();

	public List<DeviceInfoEntityView> getDeviceInfoEntity() {
		return deviceInfoEntity;
	}

	public void setDeviceInfoEntity(List<DeviceInfoEntityView> deviceInfoEntity) {
		this.deviceInfoEntity = deviceInfoEntity;
	}
	

}
