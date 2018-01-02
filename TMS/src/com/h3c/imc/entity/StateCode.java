package com.h3c.imc.entity;

public class StateCode {

	public static final int SUCCESS = 1;
	
	/**
	 * 调用厂家 接口失败	
	 */
	public static final int INTER_FAIL = -1	;
	
	/**
	 * -2处理返回系统信息失败
	 */
	public static final int SYS_INFO_FAIL = -2;
	
	/**
	 * 其他失败
	 */
	public static final int FAIL = 200;
}
