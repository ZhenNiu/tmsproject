package com.h3c.imc.utils;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sourceId;
	
	public ResourceNotFoundException(String sourceId) {
		// TODO Auto-generated constructor stub
		this.sourceId = sourceId;
	}

	public String getSourceId(){
		return sourceId;
	}
}
