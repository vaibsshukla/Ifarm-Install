package com.b3ds.ifarm.installation.models;

public class ValidationResponse {
	
	private String fieldName;
	private String message;
		
	public ValidationResponse(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ValidationResponse [fieldName=" + fieldName + ", message=" + message + "]";
	}
	
	
}
