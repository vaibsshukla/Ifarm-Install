package com.b3ds.ifarm.installation.models;

import com.google.gson.annotations.SerializedName;

public class RootServiceComponents {
	
	@SerializedName("component_name")
	private String componentName;
	
	@SerializedName("component_version")
	private String componentVersion;

	@SerializedName("service_name")
	private String serviceName;

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentVersion() {
		return componentVersion;
	}

	public void setComponentVersion(String componentVersion) {
		this.componentVersion = componentVersion;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	
}
