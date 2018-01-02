package com.h3c.imc.server;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.h3c.imc.entity.DeviceInfoEntity;
import com.h3c.imc.entity.DeviceInfoEntityView;
import com.h3c.imc.entity.ResultSign;
import com.h3c.imc.entity.SoftWareInoEntity;
import com.h3c.imc.entity.SysInfoEntity;

public interface ConfigureServer {

	
	
	public int getResultSign();
	/**
	 * 查询系统信息，(系统名称，网管版本)
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public SysInfoEntity getSysInfo();
	
	/**
	 * 查询设备信息
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public List<DeviceInfoEntity> getDevInfo() throws ClientProtocolException, IOException;
	
	
	/**
	 * 查询设备全部信息
	 * @return
	 */
	public List<DeviceInfoEntityView> getDevFullInfo(String id);
	
	 /**
	  * 查询软件信息
	  * @return
	  *
	  */
	public List<SoftWareInoEntity> getSoftWareInfo();
}
