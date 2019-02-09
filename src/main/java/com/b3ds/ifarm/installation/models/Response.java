package com.b3ds.ifarm.installation.models;

import java.io.Serializable;

public class Response implements Serializable{
	
	private static final long serialVersionUID = 2449612376913157221L;
	
	private Integer status;
	private Object data;
	private String message;
	
	public Response(){}
		
	public Response(Integer status, Object data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", data=" + data + ", message=" + message + "]";
	}
	
	
}
