package com.b3ds.ifarm.installation.models;

import java.io.Serializable;

public class Service implements Serializable{
	
	private String type;
	private String serviceName;
	private String runningStatus;
	private String installedStatus;
	private String requiredVersion;
	private String installedVersion;
	
	public Service(String type, String serviceName, String runningStatus, String installedStatus,
			String requiredVersion, String installedVersion) {
		super();
		this.type = type;
		this.serviceName = serviceName;
		this.runningStatus = runningStatus;
		this.installedStatus = installedStatus;
		this.requiredVersion = requiredVersion;
		this.installedVersion = installedVersion;
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

	public String getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(String runningStatus) {
		this.runningStatus = runningStatus;
	}

	public String getInstalledStatus() {
		return installedStatus;
	}

	public void setInstalledStatus(String installedStatus) {
		this.installedStatus = installedStatus;
	}

	public String getRequiredVersion() {
		return requiredVersion;
	}

	public void setRequiredVersion(String requiredVersion) {
		this.requiredVersion = requiredVersion;
	}

	public String getInstalledVersion() {
		return installedVersion;
	}

	public void setInstalledVersion(String installedVersion) {
		this.installedVersion = installedVersion;
	}

	@Override
	public String toString() {
		return "Service [type=" + type + ", serviceName=" + serviceName + ", runningStatus=" + runningStatus
				+ ", installedStatus=" + installedStatus + ", requiredVersion=" + requiredVersion
				+ ", installedVersion=" + installedVersion + "]";
	}
	
	
}
