package com.b3ds.ifarm.installation.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ServiceInfo implements Serializable{

	@SerializedName("cluster_name")
	private String clusterName;
	
	@SerializedName("desired_stack")
	private DesiredStack desiredStack;
	
	@SerializedName("maintenance_state")
	private String maintenanceState;
	
	@SerializedName("service_name")
	private String serviceName;

	@SerializedName("state")
	private String state;

	public ServiceInfo(String clusterName, DesiredStack desiredStack, String maintenanceState, String serviceName,
			String state) {
		super();
		this.clusterName = clusterName;
		this.desiredStack = desiredStack;
		this.maintenanceState = maintenanceState;
		this.serviceName = serviceName;
		this.state = state;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public DesiredStack getDesiredStack() {
		return desiredStack;
	}

	public void setDesiredStack(DesiredStack desiredStack) {
		this.desiredStack = desiredStack;
	}

	public String getMaintenanceState() {
		return maintenanceState;
	}

	public void setMaintenanceState(String maintenanceState) {
		this.maintenanceState = maintenanceState;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ServiceInfo [clusterName=" + clusterName + ", desiredStack=" + desiredStack + ", maintenanceState="
				+ maintenanceState + ", serviceName=" + serviceName + ", state=" + state + "]";
	}


}

