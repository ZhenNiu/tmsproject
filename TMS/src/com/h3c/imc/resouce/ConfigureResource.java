package com.h3c.imc.resouce;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.h3c.imc.entity.DeviceInfoEntity;
import com.h3c.imc.entity.SoftWareInoEntity;
import com.h3c.imc.entity.SysInfoEntity;

/**
 * 
 * @author 71071
 *	调用iMC的接口获取配置信息的数据
 */
public interface ConfigureResource {
	

	/**
	 * 查询系统信息，(系统名称，网管版本)
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public SysInfoEntity getSysInfo() throws ClientProtocolException, IOException;
	
	/**
	 * 查询设备信息
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public List<DeviceInfoEntity> getDevInfo() throws ClientProtocolException, IOException;
	
	/**
	 * 根据设备id查询
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	
	public DeviceInfoEntity getDevFullInfo(String id) throws ClientProtocolException, IOException;
	
	 /**
	  * 查询软件信息
	  * @return
	  * @throws ClientProtocolException
	  * @throws IOException
	  */
	public List<SoftWareInoEntity> getSoftWareInfo() throws ClientProtocolException, IOException;
}
