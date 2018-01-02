package com.h3c.imc.entity;

/**
 * 
 * @author 71071
 *	异常实体类
 */
public class ErrorEntity {

		private int errorCode;
		
		private String errorMessege;
		
		public ErrorEntity(int errorCode,String errorMessege) {
			// TODO Auto-generated constructor stub
			this.errorCode = errorCode;
			this.errorMessege= errorMessege;
		}

		public int getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorMessege() {
			return errorMessege;
		}

		public void setErrorMessege(String errorMessege) {
			this.errorMessege = errorMessege;
		}
		
		
}
