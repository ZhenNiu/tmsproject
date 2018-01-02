package com.h3c.imc.resouce;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

import com.h3c.imc.entity.DeviceInfoEntity;
import com.h3c.imc.entity.SoftWareInoEntity;
import com.h3c.imc.entity.SysInfoEntity;
import com.h3c.imc.utils.XmlConverter;
import com.h3c.imc.utils.XmlUtils;

/**
 * source 层，调用接口获取数据
 * @author 71071
 *
 */
public class ConfigureResourceImpl implements ConfigureResource{

	private Log runLog = LogFactory.getLog(ConfigureResourceImpl.class);
	
	private static String imcIp = XmlUtils.getNodeText("IMCIp");
	private static int iMCPort = Integer.parseInt(XmlUtils.getNodeText("IMCPort"));
	
	private static String imcUserName  = XmlUtils.getNodeText("IMCUserName");
	
	private static String iMCPassword = XmlUtils.getNodeText("IMCPassword");
	
			static String url = "http://" + imcIp + ":" + iMCPort + "/imcrs/";
			
	/**
	 * 访问imc Rest接口的通用方法
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
			
	private HttpResponse getHttpEntity(String restUrl) throws ClientProtocolException, IOException{
		//通过spring mvc对REST的支持，RestTemplate可以简化客户端代码。
		//RestTemplate restTemplate = new RestTemplate();
		//          return restTemplate.getForEntity(restUrl, SysInfoEntity.class);
		DefaultHttpClient client = new DefaultHttpClient();
		client.getCredentialsProvider().setCredentials(new AuthScope(imcIp, iMCPort, "iMC RESTful Web Services"),
				new UsernamePasswordCredentials(imcUserName, iMCPassword));
		RestTemplate restTemplate = new RestTemplate();
		HttpGet httpGet = new HttpGet(url+restUrl);
		httpGet.addHeader("accept","application/xml");
		HttpResponse httpResponse;
			httpResponse = client.execute(httpGet);
			return httpResponse;

	}
	
	/**
	 * 查询系统信息，(系统名称,用户标签,网管版本)
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public  SysInfoEntity getSysInfo() throws ClientProtocolException, IOException{
		String sysRestUrl = "plat/licenseInfo/allLicenseMsg";
		HttpResponse sysInofResponse = getHttpEntity(sysRestUrl);//获取系统信息
		HttpEntity entity = sysInofResponse.getEntity();
		int sysInofStatusCode = sysInofResponse.getStatusLine().getStatusCode();
		
			if(sysInofStatusCode == HttpStatus.SC_OK){
				XmlConverter<SysInfoEntity> xmlConverter = new XmlConverter<SysInfoEntity>(SysInfoEntity.class);
				if(null != entity){
					String xmlSysInfoEntity = EntityUtils.toString(entity,"UTF-8");
					//将string类型的xml转换成对象
					//只返回第一个平台的version
					return (SysInfoEntity)xmlConverter.unmarshal(xmlSysInfoEntity,true).get(0);
				}
			}
			return null;

		
	}

	/**
	 * 查询设备信息
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Override
	public List<DeviceInfoEntity> getDevInfo() throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		String devRestUrl = "plat/res/device";
		HttpResponse devInofResponse = getHttpEntity(devRestUrl);//获取设备信息
		
		HttpEntity entity = devInofResponse.getEntity();
		int devInofStatusCode = devInofResponse.getStatusLine().getStatusCode();
		
			if(devInofStatusCode == HttpStatus.SC_OK){
				XmlConverter<DeviceInfoEntity> xmlConverter = new XmlConverter<DeviceInfoEntity>(DeviceInfoEntity.class);
				if(null != entity){
					String xmlDevInfoEntity = EntityUtils.toString(entity,"UTF-8");
					//将string类型的xml转换成对象
					return (List<DeviceInfoEntity>)xmlConverter.unmarshal(xmlDevInfoEntity,true);
				}
			}
			return null;

	}

	/**
	 * 根据设备id的集合查询设备信息+
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Override
	public DeviceInfoEntity getDevFullInfo(String id) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		//对id进行
		String devRestUrid = "plat/res/device/"+id;
		HttpResponse  response = getHttpEntity(devRestUrid);
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode == HttpStatus.SC_OK){
			//把对象序列化为xml
			XmlConverter<DeviceInfoEntity> xmlConverter = new XmlConverter<DeviceInfoEntity>(DeviceInfoEntity.class);
			if(entity != null ){
				String xmlDevInfoEntity = EntityUtils.toString(entity, "UTF-8");
				return (DeviceInfoEntity)xmlConverter.unmarshal(xmlDevInfoEntity);
			}
		}
		return null;
	}

	/**
	 * 查询软件信息
	 * /icc/deviceSoft/list
	 */
	@Override
	public List<SoftWareInoEntity> getSoftWareInfo() throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		String softWareRestUrl = "icc/deviceSoft/list";
		HttpResponse response = getHttpEntity(softWareRestUrl);
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode == HttpStatus.SC_OK){
			//把对象序列化为xml
			XmlConverter<SoftWareInoEntity> xmlConverter = new XmlConverter<SoftWareInoEntity>(SoftWareInoEntity.class);
			if(entity != null ){
				String xmlDevInfoEntity = EntityUtils.toString(entity, "UTF-8");
				return (List<SoftWareInoEntity>)xmlConverter.unmarshal(xmlDevInfoEntity, false);
			}
		}
		return null;
	}
	
	



}
