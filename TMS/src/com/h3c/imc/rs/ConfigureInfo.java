package com.h3c.imc.rs;


import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h3c.imc.entity.DevInfoResponseResult;
import com.h3c.imc.entity.DeviceInfoEntity;
import com.h3c.imc.entity.DeviceInfoEntityView;
import com.h3c.imc.entity.ErrorEntity;
import com.h3c.imc.entity.SysInfoResponseResult;
import com.h3c.imc.entity.ResultSign;
import com.h3c.imc.entity.StateCode;
import com.h3c.imc.entity.SysInfoEntity;
import com.h3c.imc.server.ConfigureServer;
import com.h3c.imc.utils.ResourceNotFoundException;

/**
 * 
 * @author 71071
 * @version jre1.8,tomcat v7.0
 * 2017.4.12
 *	提供配置信息的接口包括:
 *	1.系统信息，2.设备信息，3.设备机框信息
 *	4.设备槽位信息，5.设备板卡信息，6.设备端口信息
 *	7.逻辑块与物理口关系，8.设备端口与ip关系
 *	9.设备间拓扑关系，10.VRF信息，11.VLAN信息
 *	12.配置文件信息
 *	
 */
@Controller
@RequestMapping("/")
//@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_XML)
public class ConfigureInfo {
	
	private Logger runLog = Logger.getLogger(ConfigureInfo.class);
	
	
	@Autowired(required=false)
	private ConfigureServer configureServer;
	
	
	/**
	 * 和获取系统信息的接口握手，返回成功或失败的结果
	 */
	
	@RequestMapping(value = "activeGetSysTms")
	public @ResponseBody ResultSign getResultSign(){
		int codeMessage = configureServer.getResultSign();
		
		ResultSign resultSign = new ResultSign();
		resultSign.setResultsign(String.valueOf(codeMessage));
		return resultSign;
	}
	/**
	 * 获取系统信息
	 * @return
	 * 
	 */
	//@GET()
	//http://localhost:8080/TMSAbutmentiMC/configureinfo/sysinfo
	@RequestMapping(value="passiveSetSysVendor",
				method = RequestMethod.GET,
				produces = "application/xml")//只会处理预期输出为xml，url为sysinfo的 get请求
	public @ResponseBody SysInfoResponseResult passiveSetSysVendor(){
		//调用imc接口获取数据
		//获取到系统信息
		SysInfoEntity sysInfo = configureServer.getSysInfo();
		//获取到设备信息
		//List<DeviceInfoEntity> devInfoList = configureServer.getDevInfo();
		if(sysInfo == null){
			runLog.info("sysInfo is  null");
			throw new ResourceNotFoundException("sysinfo");
		}
		runLog.info("restult"+sysInfo);
		SysInfoResponseResult responseResult = new SysInfoResponseResult();
		responseResult.setResultSign(getResultSign());
		responseResult.setSysInfoEntity(sysInfo);
		return responseResult;
	}
	
	/**
	 *	和获取设备信息的接口握手，返回失败与否的结果码
	 * @return
	 */
	
	@RequestMapping(value = "activeGetNeTms")
	public @ResponseBody ResultSign getResultSignForDevInfo(){
		int codeMessage = configureServer.getResultSign();
		
		ResultSign resultSign = new ResultSign();
		resultSign.setResultsign(String.valueOf(codeMessage));
		return resultSign;
	}
	
	
	/**
	 * 获取设备信息
	 * @return
	 * 
	 */
	//@GET()
	//http://localhost:8080/TMSAbutmentiMC/configureinfo/sysinfo
	@RequestMapping(value="passiveSetNeVendor/{id}",                                
				method = RequestMethod.GET,
				produces = "application/xml")//只会处理预期输出为xml，url为sysinfo的 get请求
	public @ResponseBody DevInfoResponseResult passiveSetNeVendor(@PathVariable String id){
		//调用imc接口获取数据
		//获取到设备信息
		//SysInfoEntity sysInfo = configureServer.get;
		List<DeviceInfoEntityView> devInfoViewList = configureServer.getDevFullInfo(id);
		//获取到设备信息
		//List<DeviceInfoEntity> devInfoList = configureServer.getDevInfo();
		DevInfoResponseResult responseResult = new DevInfoResponseResult();
		if(devInfoViewList == null || devInfoViewList.size()==0){
			runLog.info("deviceInfo is  empty");
			ResultSign resultSign = new ResultSign(); 
			resultSign.setResultsign("-1");
			resultSign.setResultmessage("The Device ID does not exist");
			responseResult.setResultSign(resultSign);
			return responseResult;
			//throw new ResourceNotFoundException("devInfo");
		}
		runLog.info("restult"+devInfoViewList);
		responseResult.setResultSign(getResultSign());
		responseResult.getDevInfoViewList().setDeviceInfoEntity(devInfoViewList);
		return responseResult;
	}
	
	
	@RequestMapping(value="test",
			method = RequestMethod.GET,
			produces = "application/json")//只会处理预期输出为xml，url为sysinfo的 get请求
	public @ResponseBody Response getSysTest(){
		SysInfoEntity sysInfoList = configureServer.getSysInfo();
		runLog.info("restult"+sysInfoList);
		return Response.ok().build();
}
	
	/**
	 * 处理特定异常的异常处理器
	 * 如果在控制器中的任意方法中抛出异常ResourceNotException，就会自动调用此方法。
	 * 此方法始终会返回ErrorEntity.
	 * 可以使用ResponseEntit(目的是设置相应状态码)，但是借助异常处理器以及@ResponseStatus，可以无需ResponseEntit
	 * @return
	 */          
	@ExceptionHandler(ResourceNotFoundException.class)
	//@ResponseStatus(HttpStatus.SC_NOT_FOUND)//此注解，可以设置状态吗或者无需此 注解 但返回ResponseEntity）
	public @ResponseBody ErrorEntity resourceNoteFound(ResourceNotFoundException e){                                                                                                                                                                                                                                                                                                                                                  
	/*		ErrorEntity errorEntity = new ErrorEntity();
			errorEntity.setErrorCode(4);
			errorEntity.setErrorMessege("");*/
		return new  ErrorEntity(StateCode.FAIL,"error message");
	}

	
}
