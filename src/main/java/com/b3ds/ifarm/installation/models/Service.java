package com.b3ds.ifarm.installation.models;

import java.io.Serializable;

public class Service implements Serializable{
	
	private String type;
	private String serviceName;
	private String version;

	public Service(String type, String serviceName, String version) {
		this.type = type;
		this.serviceName = serviceName;
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Service [type=" + type + ", serviceName=" + serviceName + ", version=" + version + "]";
	}
	
	
}
