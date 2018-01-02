package com.h3c.imc.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;

import com.h3c.imc.entity.DeviceInfoEntity;
import com.h3c.imc.entity.DeviceInfoEntityView;
import com.h3c.imc.entity.ResultSign;
import com.h3c.imc.entity.SoftWareInoEntity;
import com.h3c.imc.entity.StateCode;
import com.h3c.imc.entity.SysInfoResponseResult;
import com.h3c.imc.entity.SysInfoEntity;
import com.h3c.imc.resouce.ConfigureResource;

/**
 * server层 ，处理逻辑
 * @author 71071
 *
 */
public class ConfigureServerImpl implements ConfigureServer {
	
	private Log runLog =  LogFactory.getLog(ConfigureServerImpl.class);

	@Autowired
	private ConfigureResource configureResource;
	
	@Override
	public SysInfoEntity getSysInfo() {
		// TODO Auto-generated method stub

		try {
			//调用设备信息接口
			List<DeviceInfoEntity>  devInfoList = getDevInfo();
			if(devInfoList == null || devInfoList.size()==0){
				runLog.info("devInfoList is empty");
				return null;
			}
			//赋值到DeviceInfoEntityView，用于响应给客户端的格式
			List<DeviceInfoEntityView> devInfoEntityView = new ArrayList<DeviceInfoEntityView>();
			DeviceInfoEntityView deviceInfoEntityView = new DeviceInfoEntityView();
			for(DeviceInfoEntity devInfoEntity :devInfoList){
				deviceInfoEntityView.setID(devInfoEntity.getID());
				deviceInfoEntityView.setSysName(devInfoEntity.getSysName());
				devInfoEntityView.add(deviceInfoEntityView);
			}
			//调用系统信息的接口
			SysInfoEntity sysInfo =  configureResource.getSysInfo();
			//读取可自定义的配置文件用户赋值 userLable和sysName
			Configuration config= new XMLConfiguration("info.xml");
			String userLable = config.getString("userLable");
			String sysName = config.getString("sysName");
			sysInfo.setUserLable(userLable);
			sysInfo.setName(sysName);
			sysInfo.getDevInfoViewList().setDeviceInfoEntity(devInfoEntityView);
			return sysInfo;
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			//读取xml文件的异常
			e.printStackTrace();
			runLog.error(e.getMessage());
			return null;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			//sourse层调用imc接口 的异常
			e.printStackTrace();
			runLog.error(e.getMessage());
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			runLog.error(e.getMessage());
			return null;
		}
		
		
	}
	/**
	 * 调用 设备信息接口
	 */

	@Override
	public List<DeviceInfoEntity> getDevInfo() throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		List<DeviceInfoEntity> devInfoEntityList = configureResource.getDevInfo();
		
		return devInfoEntityList;
	}

	/**
	 * 获取接口的状态，
	 * 1为成功，-1调用厂家接口失败，-2处理返回系统信息失败，0其它异常。
	 */ 
	@Override
	public int getResultSign() {
		// TODO Auto-generated method stub
		try {
			configureResource.getSysInfo();
			configureResource.getDevInfo();
			//成功
			//resultSign.setResultsign("1");
			runLog.info("success:ResultSign return 1");
			return StateCode.SUCCESS;
		} catch (ClientProtocolException e) {
			//resultSign.setResultsign("-1");
			// TODO Auto-generated catch block
			e.printStackTrace();
			runLog.error(e.getMessage());
			return StateCode.INTER_FAIL;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//resultSign.setResultsign("-2");
			e.printStackTrace();
			runLog.error(e.getMessage());
			return StateCode.SYS_INFO_FAIL;
		}
		
	}
	/**
	 * 根据设备id的集合查询设备信息
	 */
	@Override
	public List<DeviceInfoEntityView> getDevFullInfo(String id) {
		// TODO Auto-generated method stub
		List<DeviceInfoEntityView> devInfoEntityView = new ArrayList<DeviceInfoEntityView>();
		DeviceInfoEntityView deviceInfoEntityView = new DeviceInfoEntityView();
		//查询软件信息
/*		 List<SoftWareInoEntity> softWareInfoList = getSoftWareInfo();
		 
		 if(softWareInfoList.size()>0){
			 for(SoftWareInoEntity softWareInfo :softWareInfoList){
				 deviceInfoEntityView.setSoft_version(softWareInfo.getSoftwareVersion());
				 devInfoEntityView.add(deviceInfoEntityView);
			 }
			
		 }*/
		try {
			if(null != id){
				//根据id查询
				String[] split = id.trim().split(",");
				for(String s_id:split){
					DeviceInfoEntity devInfoEntity = configureResource.getDevFullInfo(s_id);
					if(devInfoEntity == null){
						runLog.error("");
						return null;
					}
					deviceInfoEntityView.setModel(devInfoEntity.getModel());
					deviceInfoEntityView.setVendor(devInfoEntity.getVendor());
					deviceInfoEntityView.setRunTime(devInfoEntity.getRunTime());
					deviceInfoEntityView.setType(devInfoEntity.getCategory());
					deviceInfoEntityView.setFlashSize("");
					deviceInfoEntityView.setCpuSize("");
					deviceInfoEntityView.setMemorySize("");
					deviceInfoEntityView.setSysName(devInfoEntity.getSysName());
					deviceInfoEntityView.setID(devInfoEntity.getID());
					deviceInfoEntityView.setNanager_IP(devInfoEntity.getIp());
					devInfoEntityView.add(deviceInfoEntityView);
				}
				return devInfoEntityView;
		
				//List<DeviceInfoEntity> devInfoListforId = configureResource.getDevFullInfo(id);
			}
			//若id为null,查询所有
			List<DeviceInfoEntity> devInfoList= getDevInfo();
			for(DeviceInfoEntity devInfoEntity :devInfoList){
				deviceInfoEntityView.setModel(devInfoEntity.getModel());
				deviceInfoEntityView.setVendor(devInfoEntity.getVendor());
				deviceInfoEntityView.setRunTime(devInfoEntity.getRunTime());
				deviceInfoEntityView.setType(devInfoEntity.getCategory());
				deviceInfoEntityView.setFlashSize("");
				deviceInfoEntityView.setCpuSize("");
				deviceInfoEntityView.setMemorySize("");
				deviceInfoEntityView.setID(devInfoEntity.getID());
				deviceInfoEntityView.setNanager_IP(devInfoEntity.getIp());
				devInfoEntityView.add(deviceInfoEntityView);
			}
			return devInfoEntityView;

		/*	if(devInfoList == null || devInfoList.size()==0){
				runLog.info("devInfoList is empty");
				return null;
			}*/
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			runLog.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TOD O Auto-generated catch block
			runLog.error(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 查询软件信息
	 */
	@Override
	public List<SoftWareInoEntity> getSoftWareInfo() {
		// TODO Auto-generated method stub
		try {
			List<SoftWareInoEntity> softWareInfoList = configureResource.getSoftWareInfo();
			return softWareInfoList;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			runLog.error(e.getMessage());
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			runLog.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	
	

}
